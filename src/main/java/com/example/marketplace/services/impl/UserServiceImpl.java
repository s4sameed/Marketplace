package com.example.marketplace.services.impl;

import com.example.marketplace.dto.UserDTO;
import com.example.marketplace.entities.User;
import com.example.marketplace.exception.UserNotFoundException;
import com.example.marketplace.repositories.UserRepository;
import com.example.marketplace.services.UserService;
import com.example.marketplace.utility.CommonUtils;
import com.example.marketplace.utility.PageableResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        String userId = UUID.randomUUID().toString();
        userDTO.setId(userId);

        User user = mapper.map(userDTO, User.class);

        User newUser = userRepository.save(user);

        UserDTO newUserDTO = mapper.map(newUser, UserDTO.class);

        return newUserDTO;
    }

    @Override
    public UserDTO updateUser(String userId, UserDTO userDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("No user found with this Id"));

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setGender(userDTO.getGender());
        user.setProfileImage(userDTO.getProfileImage());

        User updatedUser = userRepository.save(user);

        UserDTO updatedUserDTO = mapper.map(updatedUser, UserDTO.class);
        return updatedUserDTO;
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("No user found with this Id"));
        userRepository.delete(user);
    }

    @Override
    public UserDTO getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("No user found with this Id"));
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        return userDTO;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("No user found with this email"));
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        return userDTO;
    }

    @Override
    public PageableResponse<UserDTO> getAllUser(int pageNumber, int pageSize, String sortBy, String sortOrder) {

        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending() ;

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<User> page =  userRepository.findAll(pageable);

        PageableResponse<UserDTO> response = CommonUtils.getPageableResponse(page, UserDTO.class);

        return response;

    }

    @Override
    public List<UserDTO> searchUser(String keyword) {
        List<User> users = userRepository.findByUsernameContaining(keyword);
        List<UserDTO> userDTOList = users.stream().map(user -> mapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return userDTOList;
    }
}
