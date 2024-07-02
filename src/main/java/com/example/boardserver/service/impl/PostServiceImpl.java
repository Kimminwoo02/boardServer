package com.example.boardserver.service.impl;

import com.example.boardserver.dto.PostDTO;
import com.example.boardserver.dto.UserDTO;
import com.example.boardserver.mapper.PostMapper;
import com.example.boardserver.mapper.UserProfileMapper;
import com.example.boardserver.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
@Slf4j
public class PostServiceImpl implements PostService {

    private static final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);
    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Override
    public void register(String id, PostDTO postDTO) {
        UserDTO memberInfo = userProfileMapper.getUserProfile(id);
        postDTO.setUserId(memberInfo.getId());
        postDTO.setCreateTime(new Date());

        if(memberInfo != null){
            postMapper.register(postDTO);
        } else {
            log.error( "update Error! {}", postDTO);
            throw new RuntimeException("update ERROR! Please Check the register Method "+ postDTO );

        }
    }

    @Override
    public List<PostDTO> getMyPosts(int accoundId) {
        return postMapper.selectMyPosts(accoundId);
    }

    @Override
    public void updatePosts(PostDTO postDTO) {
        if(postDTO != null && postDTO.getId() != 0){
            postMapper.updatePost(postDTO);
        } else {
            log.error( "update Error! {}", postDTO);
            throw new RuntimeException("update ERROR! Please Check the update Method "+ postDTO );
        }
    }

    @Override
    public void deletePosts(int userId, int postId) {
        if (userId != 0 && postId != 0){
            postMapper.deletePost(postId);
        } else {
            log.error( "delete Error! {}  {}", userId, postId);
            throw new RuntimeException("delete ERROR! Please Check the delete Method "+ postDTO );
        }
    }
}
