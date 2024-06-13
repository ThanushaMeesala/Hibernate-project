package org.jsp.merchantproductapp.controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.jsp.merchantproductapp.dao.Merchantdao;
import org.jsp.merchantproductapp.dao.Productdao;
import org.jsp.merchantproductapp.dto.Merchant;
import org.jsp.merchantproductapp.dto.Product;

public class MerchantProductcontroller {
	static Merchantdao merchantdao=new Merchantdao();
	static Productdao productdao=new Productdao();
	static Scanner s=new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("1.save merchant");
		System.out.println("2.update merchant");
		System.out.println("3.find merchant by id");
		System.out.println("4.verify merchant by phone and password");
		System.out.println("5.verify merchant by email and password");
		System.out.println("6.verify merchant by id and password");
		System.out.println("7.add product");
		System.out.println("8.update product");
		System.out.println("9.find product by id");
		System.out.println("10.find product by merchant phone and password");
		System.out.println("11.find products by brand");
		System.out.println("12.find products by category");
		System.out.println("13.filter products by cost");
		switch(s.nextInt()) {
		case 1:{save();
			break;}
		case 2:{update();
			break;}
		case 3:{findbyid();
			break;}
		case 4:{verifybyphone();
			break;}
		case 5:{verifybyemail();
			break;}
		case 7:{saveProduct();
			break;}
		case 8:{updateProduct();
			break;}
		case 9:{findproductsbyId();
			break;}
		case 10:{findproductsbymerchantPhone();
			break;}
		case 11:{findproductsbymerchantEmail();
			break;}
		}
	}

	public static void save() {
		System.out.println("enter the name,phone,email,password to save merchant");
		Merchant merchant=new Merchant();
		merchant.setName(s.next());
		merchant.setPhone(s.nextLong());
		merchant.setEmail(s.next());
		merchant.setGst_number(s.next());
		merchant.setPassword(s.next());
		merchant = merchantdao.saveMerchant(merchant);
		System.out.println("Merchant saved with Id:" + merchant.getId());
	}

	public static void update() {
		System.out.println("Enter the Merchant Id to update");
		int id = s.nextInt();
		System.out.println("enter the name,phone,email,password to update merchant");
		Merchant merchant=new Merchant();
		merchant.setId(id);
		merchant.setName(s.next());
		merchant.setPhone(s.nextLong());
		merchant.setEmail(s.next());
		merchant.setGst_number(s.next());
		merchant.setPassword(s.next());
		merchant = merchantdao.updateMerchant(merchant);
		if(merchant!=null)
		System.out.println("Merchant updated with Id:" + merchant.getId());
		else
			System.err.println("invalid id to update");
	}

	public static void findbyid() {
		System.out.println("enter the id to find the merchant");
		int id=s.nextInt();
		Merchant a=merchantdao.findbyid(id);
		if(a!=null) {
			System.out.println("merchant id: "+a.getId());
			System.out.println("merchant name: "+a.getName());
			System.out.println("phone number: "+a.getPhone());
			System.out.println("email: "+a.getEmail());
			System.out.println("gst number: "+a.getGst_number());
		}else
			System.err.println("invalid id");
		
	}

	public static void verifybyphone() {
		System.out.println("enter the phone and password to verify the merchant");
		long phone=s.nextLong();
		String password=s.next();
		Merchant a=merchantdao.verifybyphone(phone,password);
		if(a!=null) {
			System.out.println("merchant id: "+a.getId());
			System.out.println("merchant name: "+a.getName());
			System.out.println("phone number: "+a.getPhone());
			System.out.println("email: "+a.getEmail());
			System.out.println("gst number: "+a.getGst_number());

		}else
			System.err.println("invalid phone and password to verify");
		
	}

	public static void verifybyemail() {
		System.out.println("enter the email and password to verify the merchant");
		String email=s.next();
		String password=s.next();
		Merchant a=merchantdao.verifybyemail(email,password);
		if(a!=null) {
			System.out.println("merchant id: "+a.getId());
			System.out.println("merchant name: "+a.getName());
			System.out.println("phone number: "+a.getPhone());
			System.out.println("email: "+a.getEmail());
			System.out.println("gst number: "+a.getGst_number());

		}else
			System.err.println("invaild email and password to verify the merchant");
	}


	public static void saveProduct() {
		System.out.println("enter the Merchant id to save hopital");
		int merchant_id=s.nextInt();
		System.out.println("enter the name,brand category,description,cost to save product");
		Product h=new Product();
		h.setName(s.next());
		h.setBrand(s.next());
		h.setCategory(s.next());
		h.setDescription(s.next());
		h.setCost(s.nextDouble());
		h=productdao.saveProduct(h, merchant_id);
		if(h!=null) {
		System.out.println("product saved with Id:" + h.getId());
		}
		else
			System.err.println("cannot save the product invalid merchant id");
	}
	public static void updateProduct() {
		System.out.println("enter the product id,name,brand,category,cost,description to update");
		Product h=new Product();
		if(h!=null) {
		h.setId(s.nextInt());
		h.setName(s.next());
		h.setBrand(s.next());
		h.setCategory(s.next());
		h.setCost(s.nextDouble());
		h.setDescription(s.next());
		h=productdao.updateProduct(h);
		System.out.println("product updated id: "+h.getId());
		}
		else
			System.err.println("cannot updated product invalid id");
	}

	public static void findproductsbyId() {
		System.out.println("enter the product id to find products");
		int id=s.nextInt();
		List<Product> product=productdao.findproductsbyid(id);
		if(product.size()>0) {
			for(Product h:product) {
				System.out.println("Product id: "+h.getId());
				System.out.println("product name: "+h.getName());
				System.out.println("product founder: "+h.getBrand());
				System.out.println("category: "+h.getCategory());
				System.out.println("description: "+h.getDescription());
				System.out.println("-----------------------");
			}
		}else
			System.out.println("invalid id");
	}

	public static void findproductsbymerchantPhone() {
		System.out.println("enter the merchant phone and password to find products");
		long phone=s.nextLong();
		String password=s.next();
		List<Product> product=productdao.findproductsbyphone(phone,password);
		if(product.size()>0) {
			for(Product h:product) {
				System.out.println("Product id: "+h.getId());
				System.out.println("product name: "+h.getName());
				System.out.println("product founder: "+h.getBrand());
				System.out.println("category: "+h.getCategory());
				System.out.println("description: "+h.getDescription());
				System.out.println("-----------------------");
			}
		}else
			System.out.println("invalid id");
	}

	public static void findproductsbymerchantEmail() {
		System.out.println("enter the merchant email and password to find products");
		String email=s.next();
		String password=s.next();
		List<Product> products=productdao.findproductsbyemail(email,password);
		if(products.size()>0) {
			for(Product h:products) {
				System.out.println("Product id: "+h.getId());
				System.out.println("product name: "+h.getName());
				System.out.println("product founder: "+h.getBrand());
				System.out.println("category: "+h.getCategory());
				System.out.println("description: "+h.getDescription());
				System.out.println("-----------------------");
			}
		}else
			System.out.println("invalid id");
	}

	}


