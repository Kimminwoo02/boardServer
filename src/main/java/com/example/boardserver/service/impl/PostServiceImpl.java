package com.example.boardserver.service.impl;

import com.example.boardserver.dto.PostDTO;
import com.example.boardserver.dto.UserDTO;
import com.example.boardserver.mapper.PostMapper;
import com.example.boardserver.mapper.UserProfileMapper;
import com.example.boardserver.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Override
    public void register(String id, PostDTO postDTO) {
        UserDTO memberInfo = userProfileMapper.getUserProfile(id);
        postDTO.setUserId(memberInfo.getId)
    }

    @Override
    public List<PostDTO> getMyPosts(int accoundId) {
        return List.of();
    }

    @Override
    public void updatePosts(PostDTO postDTO) {

    }

    @Override
    public void deletePosts(int userId, int postId) {

    }
}
