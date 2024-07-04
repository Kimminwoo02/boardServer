package com.example.boardserver.mapper;

import com.example.boardserver.dto.CommentDTO;
import com.example.boardserver.dto.TagDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper {
    public int register(TagDTO tagDTO);
    public void updateTags(TagDTO tagDTO);
    public void deleteTags(int tagId );
    public void createPostTag(Integer tagId, Integer postId);
}
