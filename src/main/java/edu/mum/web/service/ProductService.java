package edu.mum.web.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.mum.web.domain.Product;

public interface ProductService {
	
	List <Product> getAllProducts();
	Product getProductById(String productID);
	
	List<Product> getProductsByCategory(String category);
	List<Product> getProductsByManufacturer(String manufacturer);
	Set<Product> getProductsByFilter(Map<String,List<String>> filterParams);
	void addProduct(Product product);
	
}
