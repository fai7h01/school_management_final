package com.school.service.impl;

import com.school.dto.UserDTO;
import com.school.entity.User;
import com.school.repository.UserRepository;
import com.school.service.UserService;
import com.school.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperUtil mapper;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        return userRepository.retrieveAllUsersOrderByDesc().stream()
                .map(user -> mapper.convert(user, new UserDTO()))
                .toList();
    }

    @Override
    public List<UserDTO> listAllByRole(String roleDescription) {
        return userRepository.findAllByRoleDescriptionIgnoreCase(roleDescription).stream()
                .map(user -> mapper.convert(user, new UserDTO())).toList();
    }

    @Override
    public UserDTO findById(Long id) {
        User foundUser = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found."));
        return mapper.convert(foundUser, new UserDTO());
    }

    @Override
    public UserDTO save(UserDTO user) {
        User savedUser = userRepository.save(mapper.convert(user, new User()));
        return mapper.convert(savedUser, new UserDTO());
    }

    @Override
    public UserDTO update(UserDTO user) {
        User foundUser = userRepository.findByUserName(user.getUserName());
        User convertedUser = mapper.convert(user, new User());
        convertedUser.setId(foundUser.getId());
        User savedUser = userRepository.save(convertedUser);
        return mapper.convert(savedUser, new UserDTO());
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found."));
        user.setIsDeleted(true);
        user.setUserName(user.getUserName() + "-" + user.getId());
        userRepository.save(user);
    }

    @Override
    public boolean isPasswordMatched(String password, String confirmedPassword) {
        return password.equals(confirmedPassword);
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return userRepository.findAll().stream().anyMatch(user -> user.getUserName().equals(email));
    }

    @Override
    public String isEligibleToDelete(Long id) {
        String roleDescription = findById(id).getRole().getDescription();
        switch (roleDescription){
            case "Admin":
                long adminCount = listAllUsers().stream().filter(userDTO -> userDTO.getRole().getDescription().equals("Admin")).count();
                if (adminCount == 1){
                    return "This Admin is unique in system and can not be deleted.";
                }
                return "";
            case "Manager":
                return "";
            case "Instructor":
                return "";
            default:
                return "";
        }
    }

}
