package com.example.boardserver.service;

import com.example.boardserver.dto.CommentDTO;
import com.example.boardserver.dto.PostDTO;
import com.example.boardserver.dto.TagDTO;

import java.util.List;

public interface PostService {
    void register(String id, PostDTO postDTO);
    List<PostDTO> getMyPosts(int accoundId);
    void updatePosts(PostDTO postDTO);
    void deletePosts(int userId, int postId);

    void registerComment(CommentDTO commentDTO);
    void updateCommentd(CommentDTO commentDTO);
    void deletePostComment(int userId, int commentId);
    void registerTag(TagDTO tagDTO);
    void updateTag(TagDTO tagDTO);
    void deletePostTag(int userId, int tagId);

}
