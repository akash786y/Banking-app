package com.example.demo.Service;

import com.example.demo.DTO.AccountDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Model.Account;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public User createUser(UserDTO userDTO){

        User user = User.builder()       // converting UDerDTO to User(entity)
                        .id(userDTO.getId())
                        .name(userDTO.getName())
                        .email(userDTO.getEmail())
                        .password(userDTO.getPassword())
                        .role(userDTO.getRole())
                        .build();

        userRepository.save(user);
        return user;
    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> list = userRepository.findAll();

        return list.stream()
                .map(user -> UserDTO.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .password(user.getPassword())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public User deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
        return user;
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }

    public AccountDTO toAccountDTO(Account account){
        return AccountDTO.builder()
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .id(account.getId())
                .name(account.getName())
                .userId(account.getUser().getId())
                .build();
    }

    public Account toAccount(AccountDTO accountDTO){
        return Account.builder()
                .id(accountDTO.getId())
                .accountNumber(accountDTO.getAccountNumber())
                .balance(accountDTO.getBalance())
                .name(accountDTO.getName())
                .user(userRepository.findById(accountDTO.getUserId()).orElseThrow(RuntimeException::new))
                .build();
    }
}
