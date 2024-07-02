package com.example.boardserver.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    public CategoryDTO(int categoryId, String name, SortStatus sortStatus, int searchCount, int pagingStartOffset) {
    }

    public enum SortStatus {
        CATEGORIES, NEWEST, OLDEST
    }

    private int id;
    private String name;
    private SortStatus sortStatus;
    private int searchCount;
    private int pagingStartOffset;
}
