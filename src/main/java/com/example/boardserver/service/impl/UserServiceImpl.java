package com.example.boardserver.service.impl;

import com.example.boardserver.dto.UserDTO;
import com.example.boardserver.exception.DuplicateIdException;
import com.example.boardserver.mapper.UserProfileMapper;
import com.example.boardserver.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.example.boardserver.util.SHA256Util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private UserProfileMapper userProfileMapper;


    @Override
    public void register(UserDTO userProfile) {
        boolean duplIdResult = isDuplicated(String.valueOf(userProfile.getUserId()));
        if( duplIdResult){
            throw new DuplicateIdException("Duplicated Id Error");
        }
        userProfile.setCreateTime(new Date());
        userProfile.setPassword(encryptionSHA256(userProfile.getPassword()));
        int insertCount = userProfileMapper.register(userProfile);

        if (insertCount != 1){
            log.error( "insertMember ERROR {}", userProfile);
            throw new RuntimeException( "insertUser ERROR! Please Check the Method \n " + "Params : " + userProfile );
        }
    }

    @Override
    public UserDTO login(String id, String password) {
        String cryptoPassword = encryptionSHA256(password);
        return userProfileMapper.findByIdAndPassword(id, cryptoPassword);

    }

    @Override
    public boolean isDuplicated(String id) {
        return userProfileMapper.idCheck(id)== 1;
    }

    @Override
    public UserDTO getUserInfo(String userId) {
        return null;
    }

    @Override
    public void updatePassword(String id, String beforePassword, String afterPassword) {
        String cryptoPassword = encryptionSHA256(beforePassword);
        UserDTO memberInfo = userProfileMapper.findByIdAndPassword(id, cryptoPassword);


        if(memberInfo == null){
            memberInfo.setPassword(encryptionSHA256(afterPassword));
            int insertCount = userProfileMapper.updatePassword(memberInfo);
        }else{
            log.error("update Password ERROR {} !",memberInfo);
            throw new RuntimeException("Incorrect Password");
        }
    }

    @Override
    public void deleteId(String id, String password) {
        String cryptoPassword = encryptionSHA256(password);
        UserDTO memberInfo = userProfileMapper.findByIdAndPassword(id, cryptoPassword);

        if(memberInfo != null){
            int deleteCount = userProfileMapper.deleteUserProfile(id);
        } else{
            log.error( "deleteId ERROR {}!", memberInfo);
            throw new RuntimeException(" Incorrect Password");
        }
    }
}
