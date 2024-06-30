package com.example.boardserver.mapper;

import com.example.boardserver.dto.CategoryDTO;

public interface CategoryMapper {
    public int register(CategoryDTO categoryDTO);
    public void updateCategory(CategoryDTO categoryDTO);
    public void deleteCategory(int categoryId);
}
