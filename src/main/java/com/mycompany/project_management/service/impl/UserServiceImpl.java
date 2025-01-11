package com.mycompany.project_management.service.impl;

import com.mycompany.project_management.converter.UserConverter;
import com.mycompany.project_management.dto.UserDTO;
import com.mycompany.project_management.entity.UserEntity;
import com.mycompany.project_management.repository.UserRepository;
import com.mycompany.project_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO userDTO) {
        UserEntity userEntity=userConverter.convertDTOtoEntity(userDTO);

        userEntity=userRepository.save(userEntity);
        userDTO=userConverter.convertEntityToDTO(userEntity);
        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        return null;
    }
}
