package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.model.Category;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByDescription(String description);
}