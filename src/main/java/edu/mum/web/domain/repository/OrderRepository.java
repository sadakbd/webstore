package edu.mum.web.domain.repository;

import edu.mum.web.domain.Order;

public interface OrderRepository {
	Long saveOrder(Order order);
}
