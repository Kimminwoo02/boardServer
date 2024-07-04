package com.example.boardserver.controller;

import com.example.boardserver.aop.LoginCheck;
import com.example.boardserver.dto.PostDTO;
import com.example.boardserver.dto.UserDTO;
import com.example.boardserver.dto.response.CommonResponse;
import com.example.boardserver.service.PostService;
import com.example.boardserver.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/posts")
@Slf4j
public class PostController {
    private final UserService userService;
    private final PostService postService;

    public PostController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @LoginCheck(type = LoginCheck.UserType.ADMIN)
    public ResponseEntity<CommonResponse<PostDTO>> registerPost(String accountId, @RequestBody PostDTO postDTO){
        postService.register(accountId,postDTO);
        CommonResponse commonResponse = new CommonResponse<>(HttpStatus.OK,"SUCCESS","registerPost",postDTO);
        return ResponseEntity.ok(commonResponse);
    }

    @GetMapping("my-posts")
    @LoginCheck(type = LoginCheck.UserType.USER)
    public ResponseEntity<CommonResponse<List<PostDTO>>> myPostInfo(String accountId){
        UserDTO memberinfo = userService.getUserInfo(accountId);
        List<PostDTO> postDTOList = postService.getMyPosts(memberinfo.getId());
        CommonResponse commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "myPostInfo", postDTOList);
        return ResponseEntity.ok(commonResponse);

    }

    @PatchMapping("{postId}")
    @LoginCheck(type = LoginCheck.UserType.USER)
    public ResponseEntity<CommonResponse<PostResponse>> updatePosts(String accoundId, @PathVariable(name = "postId") int postId,
                                                                                        @RequestBody PostRequst postRequest ){

        UserDTO userInfo = userService.getUserInfo(accoundId);
        PostDTO postdto = PostDTO.builder()
                .id(postId)
                .name(postRequest.getName())
                .contents(postRequest.getContents())
                .views(postRequest.getViews())
                .categoryId(postRequest.getCategoryId())
                .userId(postRequest.getUserId())
                .fileId(postRequest.getFileId())
                .updateTime(new Date())
                .build();
        postService.updatePosts(postdto);
        CommonResponse commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "updatePosts", postdto);
        return ResponseEntity.ok(commonResponse);

    }

    @DeleteMapping("{postId}")
    @LoginCheck(type = LoginCheck.UserType.USER)
    public ResponseEntity<CommonResponse<PostDeleteRequest>> deletePosts(String accountId, @PathVariable(name = "postId") int postId,
                                                                         @RequestBody PostDeleteRequest postDeleteRequest){
        UserDTO userInfo = userService.getUserInfo(accountId);
        postService.deletePosts(userInfo.getUserId(),postId);
        CommonResponse commonResponse = new CommonResponse<>(HttpStatus.OK,"SUCCESS","deletePosts",userInfo);
        return ResponseEntity.ok(commonResponse);

    }




    // -- response 객체 Inner로 선언
    @Getter
    @AllArgsConstructor
    private static class PostResponse{
        private List<PostDTO> postDTOs;
    }

    @Getter
    @Setter
    private static class PostRequst{
        private String name;
        private String contents;
        private int views;
        private int categoryId;
        private int userId;
        private int fileId;
        private Date updateTime;

    }

    @Getter
    @Setter
    private static class PostDeleteRequest{
        private int id;
        private int accountId;

    }

}
