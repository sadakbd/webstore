package edu.mum.web.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.web.domain.Product;
import edu.mum.web.domain.repository.ProductRepository;
import edu.mum.web.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;	
	
	public List<Product> getAllProducts() {		
		return productRepository.getAllProducts();
	}

	public Product getProductById(String productID) {		
		return productRepository.getProductById(productID);
	}

	public List<Product> getProductsByCategory(String category) {		
		return productRepository.getProductsByCategory(category);
	}

	public Set<Product> getProductsByFilter(
			Map<String, List<String>> filterParams) {		
		return productRepository.getProductsByFilter(filterParams);
	}

	public List<Product> getProductsByManufacturer(String manufacturer) {
		return productRepository.getProductsByManufacturer(manufacturer);
	}

	public void addProduct(Product product) {
		productRepository.addProduct(product);		
	}

}
