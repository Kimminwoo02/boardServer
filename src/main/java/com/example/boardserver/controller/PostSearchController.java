package com.example.boardserver.controller;

import com.example.boardserver.dto.PostDTO;
import com.example.boardserver.dto.request.PostSearchRequest;
import com.example.boardserver.service.PostSearchService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@Slf4j
@RequiredArgsConstructor
public class PostSearchController {

    private final PostSearchService postSearchService;

    @PostMapping // 조회에 조건이 많을 때 Posts로 사용하기도 한다.
    public PostSearchResponse search(@RequestBody PostSearchRequest postSearchRequest){
        List<PostDTO> postDTOList = postSearchService.getPosts(postSearchRequest);
        return new PostSearchResponse(postDTOList);

    }

    @GetMapping
    public PostSearchResponse searchByTagName(String tagName){
        List<PostDTO> postDTOList  = postSearchService.getPostByTag(tagName);
        return new PostSearchResponse(postDTOList);
    }


    // -- response 객체;
    @Getter
    @AllArgsConstructor
    private static class PostSearchResponse{
        private List<PostDTO> postDTOList;
    }


}
