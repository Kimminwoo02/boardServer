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


    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void register(String accountId, CategoryDTO categoryDTO) {
        if(accountId != null){
            try{
                categoryMapper.register(categoryDTO);
            }catch (RuntimeException e){
                log.error("register ERROR! {}", categoryDTO);
                throw new RuntimeException("register ERROR! Check the Category"+categoryDTO);
            }

        } else {
            log.error("register ERROR! {}", categoryDTO);
            throw new RuntimeException("register ERROR! Check the Category"+categoryDTO);
        }

    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        if(categoryDTO != null){
            try{
                categoryMapper.updateCategory(categoryDTO);
            }catch (RuntimeException e){
                log.error("update ERROR! {}", categoryDTO);
                throw new RuntimeException("update ERROR! Check the Category"+categoryDTO);
            }
        } else {
            log.error( "update ERROR ! {} ", categoryDTO);
            throw new RuntimeException("update ERROR! Check the Category"+categoryDTO);
        }
    }

    @Override
    public void delete(int categoryId) {
        if(categoryId != 0){
            try{
                categoryMapper.deleteCategory(categoryId);
            }catch (RuntimeException e){
                log.error("delete ERROR! {}", categoryId);
                throw new RuntimeException("delete ERROR! Check the Category "+categoryId);
            }

        } else {
            log.error( "delete ERROR ! {} ", categoryId);
            throw new RuntimeException("delete ERROR! Check the Category"+categoryId);
        }

    }
}
