package com.school.converter;

import com.school.dto.UserDTO;
import com.school.service.UserService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter implements Converter<String, UserDTO> {

    private final UserService userService;

    public UserDtoConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDTO convert(String source) {
        if (source == null || source.isEmpty()){
            return null;
        }
        return userService.findById(Long.parseLong(source));
    }
}
