package com.example.demo.Service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    User deleteUser(Long id);
    UserDTO getUserById(Long id);

}
