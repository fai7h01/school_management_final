package com.school.converter;

import com.school.dto.CourseDTO;
import com.school.service.CourseService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CourseDtoConverter implements Converter<String, CourseDTO> {

    private final CourseService courseService;

    public CourseDtoConverter(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public CourseDTO convert(String source) {

        if (source == null || source.isEmpty()){
            return null;
        }

        return courseService.findById(Long.parseLong(source));
    }
}
