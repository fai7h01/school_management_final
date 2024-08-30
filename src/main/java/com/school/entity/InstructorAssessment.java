package com.school.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "assessments")
public class InstructorAssessment extends BaseEntity{

    private LocalDate gradeDate;
    private Long grade;
    private String instructorImpressionOfStudent;

    @ManyToOne(fetch = FetchType.LAZY)
    private StudentLesson studentLesson;

}
