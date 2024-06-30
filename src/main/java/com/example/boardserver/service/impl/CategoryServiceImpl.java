package com.example.boardserver.service.impl;

import com.example.boardserver.dto.CategoryDTO;
import com.example.boardserver.mapper.CategoryMapper;
import com.example.boardserver.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void register(String accountId, CategoryDTO categoryDTO) {
        if(accountId != null){
            categoryMapper.register(categoryDTO);
        } else {
            log.error("register ERROR! {}", categoryDTO);
            throw new RuntimeException("register ERROR! Check the Category"+categoryDTO);
        }

    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        if(categoryDTO != null){
            categoryMapper.updateCategory(categoryDTO);
        } else {
            log.error( "update ERROR ! {} ", categoryDTO);
            throw new RuntimeException("update ERROR! Check the Category"+categoryDTO);
        }
    }

    @Override
    public void delete(int categoryId) {
        if(categoryId != 0){
            categoryMapper.deleteCategory(categoryId);
        } else {
            log.error( "delete ERROR ! {} ", categoryId);
            throw new RuntimeException("delete ERROR! Check the Category"+categoryId);
        }

    }
}
