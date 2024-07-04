package com.example.boardserver.mapper;

import com.example.boardserver.dto.CategoryDTO;
import com.example.boardserver.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    public int register(CommentDTO commentDTO);
    public void updateCategory(CommentDTO commentDTO);
    public void deleteCategory(int commentId);
}
