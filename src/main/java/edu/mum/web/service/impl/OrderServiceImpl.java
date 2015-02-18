package edu.mum.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.web.domain.Order;
import edu.mum.web.domain.Product;
import edu.mum.web.domain.repository.OrderRepository;
import edu.mum.web.domain.repository.ProductRepository;
import edu.mum.web.service.CartService;
import edu.mum.web.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CartService cartService;

	public void processOrder(String productId, int quantity) {
		Product productById = productRepository.getProductById(productId);
		if (productById.getUnitsInStock() < quantity) {
			throw new IllegalArgumentException(
					"Out of Stock. Available	Units in stock"
							+ productById.getUnitsInStock());
		}
		productById.setUnitsInStock(productById.getUnitsInStock() -	quantity);
	}

	public Long saveOrder(Order order) {
		Long orderId = orderRepository.saveOrder(order);
		cartService.delete(order.getCart().getCartId());
		return orderId;
	}	
}