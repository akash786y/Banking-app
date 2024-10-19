package com.example.demo.Controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Model.Quote;
import com.example.demo.Model.User;
import com.example.demo.Model.mongo.ExchangeRate;
import com.example.demo.Service.ExchangeRateService;
import com.example.demo.Service.QuoteService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    QuoteService quoteService;
    @Autowired
    ExchangeRateService exchangeRateService;

    @PostMapping("/user")
    public User createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @GetMapping("/user")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/user/del/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
         return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public UserDTO getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/user/getQuote")
    public ResponseEntity<?> getQuote(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Quote quote = quoteService.getQuote();
        return new ResponseEntity<>("Hi "+authentication.getName() +" "+ quote.content +" Quote by "+quote.author, HttpStatus.OK);
    }

    @GetMapping("/user/getExchangeRate")
    public ResponseEntity<?> getExchangeRate(){
        return new ResponseEntity<>(exchangeRateService.getExchangeRate(), HttpStatus.OK);
    }
}
