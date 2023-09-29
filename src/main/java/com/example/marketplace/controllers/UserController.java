package com.example.marketplace.controllers;

import com.example.marketplace.dto.UserDTO;
import com.example.marketplace.services.UserService;
import com.example.marketplace.utility.PageableResponse;
import com.example.marketplace.utility.ResponseMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //create
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO createdUserDTO = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable String userId,
            @Valid @RequestBody UserDTO userDTO
    ){
        UserDTO updatedUserDTO = userService.updateUser(userId, userDTO);
        return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseMessage> deleteUser(
            @PathVariable String userId
    ){
        userService.deleteUser(userId);

        ResponseMessage msg
                = ResponseMessage.builder()
                .message("User deleted successfully")
                .success(true)
                .status(HttpStatus.OK)
                .build();

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    //getuserById
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(
            @PathVariable String userId
    ){
        UserDTO userDTO = userService.getUserById(userId);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    //getUserByEmail
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(
            @PathVariable String email
    ){
        UserDTO userDTO = userService.getUserByEmail(email);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    //getAllUsers
    @GetMapping
    public ResponseEntity<PageableResponse<UserDTO>> getAllUsers(
            @RequestParam(value = "pageNumber", defaultValue = "1", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "username", required = false) String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = "asc", required = false) String sortOrder
    ){
        PageableResponse<UserDTO> allUsers = userService.getAllUser(pageNumber-1, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    //searchUser
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDTO>> searchUser(
            @PathVariable String keyword
    ){
        List<UserDTO> userDTOList = userService.searchUser(keyword);
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }
}
