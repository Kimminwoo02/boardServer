package com.example.boardserver.service;

import com.example.boardserver.dto.UserDTO;

public interface UserService {
    void register(UserDTO userProfile);
    UserDTO login(String id, String password);

    boolean isDuplicated(String id);

    UserDTO getUserInfo(String userId);

    void updatePassword(String id, String beforePassword, String afterPassword);

    void deleteId(String id, String password);

}
