package com.school.service;

import com.school.dto.RoleDTO;

import java.util.List;

public interface RoleService {

    List<RoleDTO> findAll();
    RoleDTO findById(Long id);
}
