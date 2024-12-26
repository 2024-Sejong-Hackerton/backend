package com.buddydim.subject;

import com.buddydim.subject.dto.CreateSubjectRequest;
import com.buddydim.subject.dto.CreateSubjectResponse;
import com.buddydim.subject.dto.TargetDaySubjectResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.buddydim.jwt.JwtUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    private final JwtUtil jwtUtil;
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService, JwtUtil jwtUtil) {
        this.subjectService = subjectService;
        this.jwtUtil = jwtUtil;

    }

    @PostMapping
    public ResponseEntity<List<CreateSubjectResponse>> addSubjects(
            @RequestBody List<CreateSubjectRequest> subjectDtos,
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtUtil.getUserIdFromToken(token);

        // Subject 생성 및 저장
        List<Subject> subjects = subjectDtos.stream()
                .map(dto -> {
                    Subject subject = new Subject();
                    subject.setSubjectName(dto.getSubjectName());
                    subject.setDays(dto.getDays());
                    subject.setProfessorName(dto.getProfessorName());
                    subject.setStartTime(LocalTime.parse(dto.getStartTime().replace("\"", "")));
                    subject.setEndTime(LocalTime.parse(dto.getEndTime().replace("\"", "")));
                    return subject;
                })
                .collect(Collectors.toList());

        // 저장
        List<Subject> savedSubjects = subjectService.saveAllSubjects(subjects, userId);

        // 저장된 데이터를 SubjectResponseDto로 변환
        List<CreateSubjectResponse> responseDtos = savedSubjects.stream()
                .map(subject -> new CreateSubjectResponse(
                        subject.getId(),
                        subject.getSubjectName(),
                        subject.getProfessorName(),
                        subject.getDays(),
                        subject.getStartTime(),
                        subject.getEndTime(),
                        userId
                ))
                .collect(Collectors.toList());

        // 저장된 정보를 반환
        return ResponseEntity.ok(responseDtos);
    }

    // 특정 날짜 과목 조회
    @GetMapping("/checkToday")
    public ResponseEntity<List<TargetDaySubjectResponse>> getSubjectsByUserIdAndDate(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtUtil.getUserIdFromToken(token);

        LocalDate date = LocalDate.now();
        List<TargetDaySubjectResponse> response = subjectService.getSubjectsByUserIdAndDate(userId,date);
        return ResponseEntity.ok(response);
    }

}

