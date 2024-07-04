package com.example.boardserver.service.impl;

import com.example.boardserver.dto.CommentDTO;
import com.example.boardserver.dto.PostDTO;
import com.example.boardserver.dto.TagDTO;
import com.example.boardserver.dto.UserDTO;
import com.example.boardserver.mapper.CommentMapper;
import com.example.boardserver.mapper.PostMapper;
import com.example.boardserver.mapper.TagMapper;
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

public class PostServiceImpl implements PostService {

    private static final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);
    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public void register(String id, PostDTO postDTO) {
        UserDTO memberInfo = userProfileMapper.getUserProfile(id);
        postDTO.setUserId(memberInfo.getId());
        postDTO.setCreateTime(new Date());

        if(memberInfo != null){
            postMapper.register(postDTO);
            Integer postId = postDTO.getId();
            for(int i =0; i< postDTO.getTags().size(); i++){
                TagDTO tagDTO = postDTO.getTags().get(i);
                tagMapper.register(tagDTO);
                int tagId = tagDTO.getId();
                tagMapper.createPostTag(tagId, postId);
            }
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
            throw new RuntimeException("delete ERROR! Please Check the delete Method "+ postId );
        }
    }

    @Override
    public void registerComment(CommentDTO commentDTO) {
        if(commentDTO.getPostId() != 0){
            commentMapper.register(commentDTO);
        }else{
            log.error( "registerComment {}", commentDTO);
            throw new RuntimeException("RegisterComment : "+ commentDTO);
        }

    }

    @Override
    public void updateCommentd(CommentDTO commentDTO) {
        if(commentDTO.getPostId() != 0){
            commentMapper.updateCategory(commentDTO);
        }else{
            log.error( "updateComment {}", commentDTO);
            throw new RuntimeException("updateComment : "+ commentDTO);
        }
    }

    @Override
    public void deletePostComment(int userId, int commentId) {
        if( userId != 0 && commentId != 0){
            commentMapper.deleteCategory(commentId);
        } else {
            log.error( "delete error" + commentId);
            throw new RuntimeException("deleteComment" + commentId);
        }
    }

    @Override
    public void registerTag(TagDTO tagDTO) {
        if( tagDTO != null){
            tagMapper.register(tagDTO);
        } else{
            log.error( "register error" + tagDTO);
            throw new RuntimeException("registerTag " + tagDTO);
        }

    }

    @Override
    public void updateTag(TagDTO tagDTO) {
        if( tagDTO != null){
            tagMapper.updateTags(tagDTO);
        } else{
            log.error( "update error" + tagDTO);
            throw new RuntimeException("update " + tagDTO);
        }
    }

    @Override
    public void deletePostTag(int userId, int tagId) {
        if( userId != 0 && tagId != 0){
            tagMapper.deleteTags(tagId);
        } else{
            log.error( "delete error" + tagId);
            throw new RuntimeException("registerTag " + tagId);
        }

    }
}
