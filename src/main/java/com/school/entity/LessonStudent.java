package com.school.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
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
@SQLRestriction("is_deleted is false")
public class LessonStudent extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    public Lesson lesson;
    @ManyToOne(fetch = FetchType.LAZY)
    public Student student;
}
