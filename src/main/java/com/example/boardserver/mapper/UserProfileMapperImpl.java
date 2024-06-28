package com.example.boardserver.mapper;

import com.example.boardserver.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public class UserProfileMapperImpl {
    public UserDTO getUserProfile(@Param("userId") String userId){
        return null;
    }

}
