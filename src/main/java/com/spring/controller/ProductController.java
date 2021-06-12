package com.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.Product;
import com.spring.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductRepository productRepo;

	@PostMapping
	public ResponseEntity<Product> addNewProduct(@RequestBody Product product) {
		productRepo.save(product);
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}
	
	//http://localhost:8081/product/1
	@GetMapping("/{no}")
	public ResponseEntity<Product> getProductByNo (@PathVariable Integer no) {
		Optional<Product> isAvailable = productRepo.findById(no);
		if(isAvailable.isPresent()) {
			return new ResponseEntity<Product>(isAvailable.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProducts() {
		return new ResponseEntity<List<Product>>(productRepo.findAll(), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Void> updateProduct(@RequestBody Product product) {
		Optional<Product> p = productRepo.findById(product.getProductNo());
		if(p.get() != null) {
			productRepo.save(product);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{no}")
	public ResponseEntity<Void> deleteProduct(@PathVariable int no) {
		Optional<Product> p = productRepo.findById(no);
		if(p.get() != null) {
			productRepo.deleteById(no);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
	}
}
