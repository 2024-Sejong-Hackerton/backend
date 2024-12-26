package com.buddydim.subject.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class TargetDaySubjectResponse {
    private Long subjectId;
    private String subjectName;
    private String days;
    private String professorName;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long userId;

    public Long getUserId() {
        return userId;
    }
    public TargetDaySubjectResponse(Long subjectId, String subjectName,
                                    String professorName, String days, LocalTime startTime, LocalTime endTime, Long userId) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.professorName = professorName;
        this.days = days;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userId=userId;
    }

    // Getters
    public Long getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getProfessorName() {
        return professorName;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getDays() {
        return days;
    }
}
