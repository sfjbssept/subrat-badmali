package com.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.Entity.Product;
import com.product.Services.IProductService;

@RestController
public class ProductController {
	//CRUD c-> create,R->read,U->update and D->Delete
	
	@Autowired
	IProductService productService;
	
	@PostMapping("/addproduct")
	Integer addProduct(@RequestBody Product product) {
		Integer id = productService.addProduct(product);
		System.out.println(id);
		return id;
		
	}
	@GetMapping("/allproducts")
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	@GetMapping("/product/{id}")
	Optional<Product> getProduct(@PathVariable Integer id){
	Optional<Product> product =productService.getProduct(id);
	return product;
	}

}
