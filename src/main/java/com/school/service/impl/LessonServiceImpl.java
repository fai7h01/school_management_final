package com.school.service.impl;

import com.school.dto.CourseDTO;
import com.school.dto.CourseStudentDTO;
import com.school.dto.LessonDTO;
import com.school.dto.StudentLessonDto;
import com.school.entity.Lesson;
import com.school.repository.LessonRepository;
import com.school.service.CourseService;
import com.school.service.CourseStudentService;
import com.school.service.LessonService;
import com.school.service.StudentLessonService;
import com.school.util.MapperUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final MapperUtil mapper;
    private final CourseService courseService;
    private final CourseStudentService courseStudentService;
    private final StudentLessonService studentLessonService;

    public LessonServiceImpl(LessonRepository lessonRepository, MapperUtil mapper, @Lazy CourseService courseService, CourseStudentService courseStudentService, StudentLessonService studentLessonService) {
        this.lessonRepository = lessonRepository;
        this.mapper = mapper;
        this.courseService = courseService;
        this.courseStudentService = courseStudentService;
        this.studentLessonService = studentLessonService;
    }

    @Override
    public List<LessonDTO> findAll() {
        return lessonRepository.findAll().stream().map(lesson -> mapper.convert(lesson, new LessonDTO())).toList();
    }

    @Override
    public LessonDTO findById(Long id) {
        Lesson foundLesson = lessonRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Lesson not found."));
        return mapper.convert(foundLesson, new LessonDTO());
    }

    @Override
    public LessonDTO save(LessonDTO lesson) {
        Lesson saved = lessonRepository.save(mapper.convert(lesson, new Lesson()));
        CourseDTO course = courseService.findById(lesson.getCourse().getId());
        List<CourseStudentDTO> courseStudentDTOS = courseStudentService.findAllByCourseIdAndIsEnrolled(course.getId(), true);
        courseStudentDTOS.forEach(courseStudentDTO -> {
            StudentLessonDto studentLessonDto = new StudentLessonDto();
            studentLessonDto.setLesson(mapper.convert(saved, new LessonDTO()));
            studentLessonDto.setStudent(courseStudentDTO.getStudent());
            studentLessonService.save(studentLessonDto);
        });
        return mapper.convert(saved, new LessonDTO());
    }

    @Override
    public LessonDTO update(LessonDTO lesson) {
        return mapper.convert(lessonRepository.save(mapper.convert(lesson, new Lesson())), new LessonDTO());
    }

    @Override
    public void delete(Long id) {
        Lesson foundLesson = lessonRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Lesson not found."));
        foundLesson.setIsDeleted(true);
        lessonRepository.save(foundLesson);
        studentLessonService.findAllByLessonId(id).forEach(studentLessonDto -> {
            studentLessonDto.setLesson(null);
            studentLessonService.save(studentLessonDto);
        });
    }

    @Override
    public List<LessonDTO> findAllByCourseId(Long id) {
        return lessonRepository.findAllByCourseId(id).stream()
                .map(lesson -> mapper.convert(lesson, new LessonDTO())).toList();
    }
}
