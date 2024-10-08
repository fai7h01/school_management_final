package com.school.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "lessons")
@SQLRestriction("is_deleted is false")
public class Lesson extends BaseEntity{

    private Long id;

    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    private User instructor;
}
