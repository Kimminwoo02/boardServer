package com.example.boardserver.mapper;

import com.example.boardserver.dto.CategoryDTO;
import com.example.boardserver.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    public int register(PostDTO postDTO);
    public List<PostDTO> selectMyPosts(int accountId);
    public void updateCategory(PostDTO PostDTO);
    public void deleteCategory(int postId);
}
