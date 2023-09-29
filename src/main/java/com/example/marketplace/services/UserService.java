package com.example.marketplace.services;

import com.example.marketplace.dto.UserDTO;
import com.example.marketplace.utility.PageableResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    //create
    UserDTO createUser(UserDTO userDTO);

    //update
    UserDTO updateUser(String userId, UserDTO userDTO);

    //delete
    void deleteUser(String userId);

    //get user by id
    UserDTO getUserById(String userId);

    //get user by email
    UserDTO getUserByEmail(String email);

    //get all user
    PageableResponse<UserDTO> getAllUser(int pageNumber, int pageSize, String sortBy, String sortOrder);

    //serach user
    List<UserDTO> searchUser(String keyword);
}
