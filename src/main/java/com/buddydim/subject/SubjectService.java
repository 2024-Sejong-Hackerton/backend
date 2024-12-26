// SubjectService 클래스
package com.buddydim.subject;

import com.buddydim.subject.dto.TargetDaySubjectResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> saveAllSubjects(List<Subject> subjects, Long userId) {
        subjects.forEach(subject -> subject.setUserId(userId)); // 프로필 ID 설정
        return subjectRepository.saveAll(subjects); // 저장 후 저장된 리스트 반환
    }

    //오늘의 과목 조회(userId를 사용하여)
    @Transactional
    public List<TargetDaySubjectResponse> getSubjectsByUserIdAndDate(Long userId, LocalDate localDate) {

        // 유저의 특정 요일 과목 조회
        DayOfWeek targetDayEnglish = localDate.getDayOfWeek();
        List<Subject> allSubjects = subjectRepository.findAllByUserId(userId);
        String targetDayKorean = convertDayOfWeekToKorean(targetDayEnglish);
        List<Subject> targetDaySubjects = new ArrayList<>();

        for (Subject subject : allSubjects) {
            if (subject.getDays().contains(targetDayKorean)) {
                targetDaySubjects.add(subject);
            }
        }

        if (targetDaySubjects.isEmpty()) {
            throw new RuntimeException("No subjects found for user " +userId + " on " + targetDayKorean);
        }

        //특정 요일 과목 정렬
        List<Subject> sortedSubjects = targetDaySubjects.stream()
                .sorted((s1, s2) -> s1.getStartTime().compareTo(s2.getStartTime()))
                .collect(Collectors.toList());


        List<TargetDaySubjectResponse> response = sortedSubjects.stream()
                .map(subject -> {
                    return new TargetDaySubjectResponse(
                            subject.getId(),
                            subject.getSubjectName(),
                            subject.getProfessorName(),
                            subject.getDays(),
                            subject.getStartTime(),
                            subject.getEndTime(),
                            subject.getUserId()
                    );
                }).collect(Collectors.toList());

        return response;
    }

    public static String convertDayOfWeekToKorean(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY:
                return "월";
            case TUESDAY:
                return "화";
            case WEDNESDAY:
                return "수";
            case THURSDAY:
                return "목";
            case FRIDAY:
                return "금";
            case SATURDAY:
                return "토";
            case SUNDAY:
                return "일";
            default:
                throw new IllegalArgumentException("잘못된 요일 값입니다: " + dayOfWeek);
        }
    }

}
