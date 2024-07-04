package com.example.boardserver.dto.request;

import com.example.boardserver.dto.CategoryDTO;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostSearchRequest {
    private int id;
    private String name;
    private String content;
    private int views;
    private int categoryId;
    private int userId;
    private CategoryDTO.SortStatus sortStatus;
}
