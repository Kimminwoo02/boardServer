package com.example.boardserver.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdatePasswordRequest {

    private String beforePassword;
    private String afterPassword;

    public String getBeforePassword() {
        return beforePassword;
    }

    public void setBeforePassword(String beforePassword) {
        this.beforePassword = beforePassword;
    }

    public String getAfterPassword() {
        return afterPassword;
    }

    public void setAfterPassword(String afterPassword) {
        this.afterPassword = afterPassword;
    }
}
