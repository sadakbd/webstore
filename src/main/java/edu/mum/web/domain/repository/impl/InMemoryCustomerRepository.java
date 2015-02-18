package edu.mum.web.domain.repository.impl;


import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import edu.mum.web.domain.Customer;
import edu.mum.web.domain.repository.CustomerRepository;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

	private List<Customer> listOfCustomers = new ArrayList<Customer>();

	public InMemoryCustomerRepository() {
		
		/*Customer cust1 = new Customer("C1234", "Sadakul", "Iowa");
		cust1.setNoOfOrdersMade(100);

		Customer cust2 = new Customer("C1235", "Shohrab", "LA");
		

		Customer cust3 = new Customer("C1236", "Zahid", "CA");
		cust3.setNoOfOrdersMade(50);
		
		
		
		listOfCustomers.add(cust1);
		listOfCustomers.add(cust2);
		listOfCustomers.add(cust3);*/

	}

	public List<Customer> getAllCustomers() {		
		return listOfCustomers;
	}
	/*public Product getProductById(String productId){
		Product productById = null;
		
		for(Product product : listOfProducts){
			if(product != null && product.getProductId() != null && product.getProductId().equals(productId)){
				productById = product;
				break;
			}
			if(productById != null){
				throw new IllegalArgumentException("No products found with the product id: "+ productId);
			}
		}		
		return productById;							
	}*/
}