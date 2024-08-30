package com.school.service.impl;

import com.school.dto.RoleDTO;
import com.school.entity.Role;
import com.school.repository.RoleRepository;
import com.school.service.RoleService;
import com.school.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final MapperUtil mapper;

    public RoleServiceImpl(RoleRepository roleRepository, MapperUtil mapper) {
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @Override
    public List<RoleDTO> findAll() {
        return roleRepository.findAll().stream()
                .map(role -> mapper.convert(role, new RoleDTO()))
                .toList();
    }

    @Override
    public RoleDTO findById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found."));
        return mapper.convert(role, new RoleDTO());
    }
}
