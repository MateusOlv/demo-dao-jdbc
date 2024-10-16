package application;

import java.util.List;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TEST 01: Seller findById ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n=== TEST 02: Seller findByDepartment ===");
		Department dep = new Department(2,null);
		List<Seller> list = sellerDao.findByDepartment(dep);
		
		for(Seller obj : list) System.out.println(obj);
		
		System.out.println("\n=== TEST 03: Seller findAll ===");
		list = sellerDao.findAll();
		
		for(Seller obj : list) System.out.println(obj);
		
		DB.closeConnection();
	}

}
