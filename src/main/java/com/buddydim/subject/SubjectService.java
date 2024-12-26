// SubjectService 클래스
package com.buddydim.subject;

import org.springframework.stereotype.Service;

import java.util.List;

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

}
