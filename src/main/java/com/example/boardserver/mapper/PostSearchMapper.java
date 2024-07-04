package com.example.boardserver.mapper;

import com.example.boardserver.dto.PostDTO;
import com.example.boardserver.dto.request.PostSearchRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostSearchMapper {
    public List<PostDTO> selectPosts(PostSearchRequest postSearchRequest);

}
