package com.aea.redis.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.aea.redis.entity.Product;

@Repository
public class ProductDao {
	
	public static final String HASH_KEY = "Product";
	
	@Autowired
	private RedisTemplate template;
	
	public Product save(Product product) {
		template.opsForHash().put(HASH_KEY, product.getId(), product);
		return product;
	}
	
	public List<Product> findAll() {
		return template.opsForHash().values(HASH_KEY);
	}
	
	public Object findProductById(int id) {
		System.out.println("************Called findProductById() from DB***********");
		Object object = template.opsForHash().get(HASH_KEY, id);
		return object;
	}
	
	public String deleteProduct(int id) {
		System.out.println("************Called deleteProduct() from DB***********");
		template.opsForHash().delete(HASH_KEY, id);
		return "Product removed !!";
	}

	public Product updateProduct(Product product) {
		System.out.println("************Called updateProduct() from DB***********");
		template.opsForHash().put(HASH_KEY, product.getId(), product);
		return product;
	}

}
