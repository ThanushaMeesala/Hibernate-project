package org.jsp.merchantproductapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


import org.jsp.merchantproductapp.dto.Merchant;
import org.jsp.merchantproductapp.dto.Product;

public class Productdao {
    EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
    public Product saveProduct(Product product,int merchant_id) {
    	Merchant m=manager.find(Merchant.class,merchant_id);
    	if(m!=null) {
    		m.getProducts().add(product);
    		product.setMerchant(m);
    		EntityTransaction transaction=manager.getTransaction();
    		manager.persist(product);
    		transaction.begin();
    		transaction.commit();
    		return product;
    	}
    	return null;
    }
    public Product updateProduct(Product product) {
    	Product dbproduct=manager.find(Product.class,product.getId());
    	if(dbproduct!=null) {
    		EntityTransaction transaction=manager.getTransaction();
    		dbproduct.setName(product.getName());
    		dbproduct.setBrand(product.getBrand());
    		dbproduct.setCategory(product.getCategory());
    		dbproduct.setDescription(product.getDescription());
    		dbproduct.setCost(product.getCost());
    		transaction.begin();
    		transaction.commit();
    		return dbproduct;
    	}
    	return null;
    }
    public List<Product> findproductsbyid(int id) {
		Query q=manager.createQuery("select a.products from Merchant a where a.id=?1");
		q.setParameter(1, id);
		return q.getResultList();		
	}
    public List<Product> findproductsbyphone(long phone,String password) {
		Query q=manager.createQuery("select a.products from Merchant a where a.phone=?1 and a.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		return q.getResultList();		
	}
    public List<Product> findproductsbyemail(String email,String password) {
		Query q=manager.createQuery("select a.products from Product a where a.email=?1 and a.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		return q.getResultList();		
	}

}
