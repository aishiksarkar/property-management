package com.mycompany.project_management.service;

import com.mycompany.project_management.dto.UserDTO;

public interface UserService {

    UserDTO register(UserDTO userDTO);
    UserDTO login(String email,String password);
}
