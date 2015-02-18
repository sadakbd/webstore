package edu.mum.web.domain.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.mum.web.domain.Cart;
import edu.mum.web.domain.repository.CartRepository;

@Repository
public class InMemoryCartRepository implements CartRepository {
	private Map<String, Cart> listOfCarts = new HashMap<String, Cart>();

	public Cart create(Cart cart) {
		
		//System.out.println("-----"+cart.getCartId());
		//System.out.println("1................");		
		//System.out.println(listOfCarts);
		//if(listOfCarts != null)
		if (listOfCarts.keySet().contains(cart.getCartId())) {
			//System.out.println("2...................");
			throw new IllegalArgumentException(
					String.format("Can not create a cart. A cart with the give id (%) aldrady exist",cart.getCartId()));
		}
		//System.out.println("3...................");
		listOfCarts.put(cart.getCartId(), cart);
		return cart;
	}

	public Cart read(String cartId) {
		return listOfCarts.get(cartId);
	}

	public void update(String cartId, Cart cart) {
		if (!listOfCarts.keySet().contains(cartId)) {
			throw new IllegalArgumentException(
					String.format(
							"Can not update cart. The cart with the give id (%) does not does not exist",
							cartId));
		}

		listOfCarts.put(cartId, cart);
	}

	public void delete(String cartId) {
		if (!listOfCarts.keySet().contains(cartId)) {
			throw new IllegalArgumentException(
					String.format(
							"Can not delete cart. The cart with the give id (%) does not does not exist",
							cartId));
		}
		listOfCarts.remove(cartId);
	}
}
