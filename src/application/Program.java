package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
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
		
		System.out.println("\n=== TEST 04: Seller insert ===");
		Seller newSeller = new Seller(null, "Mathews", "mathews@gmail.com", new Date(), 4000.00, dep);
		sellerDao.insert(newSeller);
		System.out.println("Inserted! New id: " + newSeller.getId());
		
		System.out.println("\n=== TEST 05: Seller update ===");
		seller = sellerDao.findById(1);
		seller.setName("Martha Brown");
		sellerDao.update(seller);
		System.out.println("Update completed!");
		
		System.out.println("\n=== TEST 06: Seller delete ===");
		System.out.print("Enter the id fo delete test: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Delete completed!");
		
		sc.close();
		DB.closeConnection();
	}

}
