package com.example.boardserver.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdatePasswordRequest {

    private String beforePassword;
    private String afterPassword;
}
