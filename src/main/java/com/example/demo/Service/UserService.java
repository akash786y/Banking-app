package com.example.demo.Service;

import com.example.demo.DTO.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    String createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    String deleteUser(Long id);
    UserDTO getUserById(Long id);

}
