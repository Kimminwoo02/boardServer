package com.example.boardserver.controller;

import com.example.boardserver.aop.LoginCheck;
import com.example.boardserver.dto.LoginResponse;
import com.example.boardserver.dto.UserDTO;
import com.example.boardserver.dto.UserInfoResponse;
import com.example.boardserver.dto.request.UserDeleteId;
import com.example.boardserver.dto.request.UserLoginRequest;
import com.example.boardserver.dto.request.UserUpdatePasswordRequest;
import com.example.boardserver.service.UserService;
import com.example.boardserver.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Log4j2
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private static LoginResponse loginResponse;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody UserDTO userDTO){
        if(UserDTO.hasNullDataBeforeRegister(userDTO)){
            throw new RuntimeException("Please check information");
        }
        userService.register(userDTO);
    }

    @PostMapping("/sign-in")
    public HttpStatus login (@RequestBody UserLoginRequest userLoginRequest, HttpSession session){
        ResponseEntity<LoginResponse> responseEntity = null;
        String id = userLoginRequest.getUserId();
        String password = userLoginRequest.getPassword();

        UserDTO userInfo = userService.login(id,password);

        if (userInfo ==  null){
            return HttpStatus.NOT_FOUND;
        }else if (userInfo != null){
            loginResponse = LoginResponse.success(userInfo);
            if(userInfo.getStatus() == (UserDTO.Status.ADMIN))
                SessionUtil.setLoginAdminId(session,id);
            else
                SessionUtil.setLoginMemberId(session,id);

            responseEntity = new ResponseEntity<LoginResponse>(loginResponse,HttpStatus.OK);
        } else{
            throw new RuntimeException("Login Error! ");
        }

        return HttpStatus.OK;

    }

    @GetMapping("my-info")
    public UserInfoResponse memberInfo(HttpSession session){
        String id = SessionUtil.getLoginMemberId(session);
        if( id == null) id = SessionUtil.getLoginAdminId(session);
        UserDTO memberInfo = userService.getUserInfo(id);
        return new UserInfoResponse(memberInfo);
    }

    @PutMapping("logout")
    public void logout( HttpSession session ){
        SessionUtil.clear(session);
    }

    @PatchMapping("password")
    @LoginCheck(type = LoginCheck.UserType.USER)
    public ResponseEntity<LoginResponse> updateUserPassword(String accountId, @RequestBody UserUpdatePasswordRequest userUpdatePasswordRequest, HttpSession session) {
        ResponseEntity<LoginResponse> responseEntity = null;
        String id = accountId;
        String beforePassword = userUpdatePasswordRequest.getBeforePassword();
        String afterPassword = userUpdatePasswordRequest.getAfterPassword();


        try {
            userService.updatePassword(id, beforePassword,afterPassword);
            ResponseEntity.ok(new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK));
        } catch(IllegalArgumentException e){
            log.error("updatePassword Failed", e);
            responseEntity = new ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST);
        }

        return responseEntity;

    }

    @DeleteMapping
    public ResponseEntity<LoginResponse> deleteId(@RequestBody UserDeleteId userDeleteId, HttpSession session){
        ResponseEntity<LoginResponse> responseEntity = null;
        String id = SessionUtil.getLoginMemberId(session);

        try{
            userService.deleteId(id, userDeleteId.getPassword());
            responseEntity = new ResponseEntity<LoginResponse>(loginResponse,HttpStatus.OK);
        }catch(RuntimeException e){
            log.error("deleteId 실패");
            responseEntity = new ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST);
        }
    }

}
