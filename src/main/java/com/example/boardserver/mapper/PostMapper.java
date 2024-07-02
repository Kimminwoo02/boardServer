package com.example.boardserver.mapper;

import com.example.boardserver.dto.CategoryDTO;
import com.example.boardserver.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    public int register(PostDTO postDTO);
    public List<PostDTO> selectMyPosts(int accountId);
    public void updatePost(PostDTO PostDTO);
    public void deletePost(int postId);
}
