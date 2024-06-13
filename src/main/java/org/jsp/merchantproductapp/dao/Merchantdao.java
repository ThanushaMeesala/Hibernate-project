package org.jsp.merchantproductapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.merchantproductapp.dto.Merchant;

public class Merchantdao {
	EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();

	public Merchant saveMerchant(Merchant merchant) {
		EntityTransaction transaction=manager.getTransaction();
		manager.persist(merchant);
		transaction.begin();
		transaction.commit();
		return merchant;
	}
	public Merchant updateMerchant(Merchant merchant) {
		Merchant dbmerchant=manager.find(Merchant.class,merchant.getId());
		if(dbmerchant!=null) {
			EntityTransaction transaction=manager.getTransaction();
			dbmerchant.setName(merchant.getName());
			dbmerchant.setPhone(merchant.getPhone());
			dbmerchant.setEmail(merchant.getEmail());
			dbmerchant.setGst_number(merchant.getGst_number());
			dbmerchant.setPassword(merchant.getPassword());
			transaction.begin();
			transaction.commit();
			return dbmerchant;
		}
		return dbmerchant;
	}
	public Merchant findbyid(int id) {
		return manager.find(Merchant.class,id);
	}
	public Merchant verifybyphone(long phone,String password) {
		Query q=manager.createQuery("select m from Merchant m where m.phone=?1 and m.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (Merchant)q.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
	public Merchant verifybyemail(String email,String password) {
		Query q=manager.createQuery("select m from Merchant m where m.email=?1 and m.password=?2");
		q.setParameter(1, email);
		q.setParameter(2,password);
		try {
			return (Merchant)q.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
}
