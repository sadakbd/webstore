package edu.mum.web.domain.repository;

import edu.mum.web.domain.Cart;

public interface CartRepository {
	Cart create(Cart cart);
	Cart read(String cartId);
	void update(String cartId, Cart cart);
	void delete(String cartId);
}
