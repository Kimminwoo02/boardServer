package com.example.boardserver.service.impl;

import com.example.boardserver.dto.PostDTO;
import com.example.boardserver.dto.request.PostSearchRequest;
import com.example.boardserver.mapper.PostSearchMapper;
import com.example.boardserver.service.PostSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostSearchServiceImpl implements PostSearchService {

    private final PostSearchMapper mapper;


    @Async
    @Cacheable(value = "getPosts", key = "'getProduct' #postSearchRequest.getName() + #postSearchRequest.getCategoryId() ")
    @Override
    public List<PostDTO> getPosts(PostSearchRequest postSearchRequest) {
        List<PostDTO> postDTOList = null;
        try{
            postDTOList = mapper.selectPosts(postSearchRequest);

        }
        catch (RuntimeException e){
            log.error("Failed SelectPosts {}", e.getMessage());
        }
        return postDTOList;
    }

    @Override
    public List<PostDTO> getPostByTag(String tagName) {
        List<PostDTO> postByTag = null;
        try{
            postByTag = mapper.getPostByTag(tagName);

        }
        catch (RuntimeException e){
            log.error("Failed SelectPosts {}", e.getMessage());
        }

        return postByTag;

    }


}
