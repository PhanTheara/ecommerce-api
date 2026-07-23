package com.istad.theara.ecommerce_api;

import com.istad.theara.ecommerce_api.entity.CategoryEntity;
import com.istad.theara.ecommerce_api.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest

class EcommerceApiApplicationTests {
	@Autowired
	private  CategoryRepository categoryRepository;
	// test save data
	@Test
	void  test_category(){
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setName("Ipaid 19");
		categoryEntity.setIcon("i.png");
		categoryEntity.setIsDeleted(false);

		categoryRepository.save(categoryEntity);
	}
	@Test
	void selectCategory(){
		List<CategoryEntity> categoryEntities = categoryRepository.findAll();
		IO.println(" ================== category ================== ");

		for (CategoryEntity categoryEntity : categoryEntities) {
			IO.println(categoryEntity.getId());
			IO.println(categoryEntity.getName());
			IO.println(categoryEntity.getIcon());
			IO.println(categoryEntity.getIsDeleted());
			IO.println("================================");
		}
	}
}
