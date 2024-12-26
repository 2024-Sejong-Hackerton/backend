package com.buddydim.subject;

import com.buddydim.subject.dto.SubjectRequestDto;
import com.buddydim.subject.dto.SubjectResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.buddydim.jwt.JwtUtil;

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
    public ResponseEntity<List<SubjectResponseDto>> addSubjects(
            @RequestBody List<SubjectRequestDto> subjectDtos,
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
        List<SubjectResponseDto> responseDtos = savedSubjects.stream()
                .map(subject -> new SubjectResponseDto(
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


}

