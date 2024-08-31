package com.school.converter;

import org.springframework.core.convert.converter.Converter;
import com.school.dto.RoleDTO;
import com.school.service.RoleService;
import org.springframework.stereotype.Component;

@Component
public class RoleDtoConverter implements Converter<String, RoleDTO> {

    private final RoleService roleService;

    public RoleDtoConverter(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public RoleDTO convert(String source) {

        if (source == null || source.isEmpty())
            return null;

        return roleService.findById(Long.parseLong(source));
    }

}
