package com.school.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course_student")
@SQLRestriction("is_deleted is false")
public class CourseStudent extends BaseEntity {

    private Boolean isEnrolled;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Student student;
}