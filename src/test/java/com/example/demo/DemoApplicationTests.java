package com.example.demo;

import java.util.Optional;

import com.example.demo.model.Category;
import com.example.demo.model.UnitOfMeasure;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.UnitOfMeasureRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UnitOfMeasureRepository unitOfMeasureReporitory;

	@Test
	void contextLoads() {
		String str = "%s ID is %d for %s";
		Optional<Category> category = categoryRepository.findByDescription("Italian");
		System.out.println(String.format(str, "Category", category.get().getId(), category.get().getDescription()));

		Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureReporitory.findByDescription("Cup");
		System.out.println(
				String.format(str, "UnitOfMeasure", unitOfMeasure.get().getId(), unitOfMeasure.get().getDescription()));
	}

}
