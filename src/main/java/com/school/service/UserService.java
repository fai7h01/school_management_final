package com.school.service;


import com.school.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();

    List<UserDTO> listAllByRole(String roleDescription);

    UserDTO findById(Long id);

    UserDTO save(UserDTO user);

    UserDTO update(UserDTO user);

    void delete(Long id);

    boolean isPasswordMatched(String password, String confirmedPassword);

    boolean isEmailRegistered(String email);

    String isEligibleToDelete(Long id);


}
