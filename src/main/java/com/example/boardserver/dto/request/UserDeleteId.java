package com.example.boardserver.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class UserDeleteId {
    private String userId;
    private String password;
}
