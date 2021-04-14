package com.silvercoinbank.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.silvercoinbank.domain.Branch;
import com.silvercoinbank.repository.BranchRepository;


@Component
public class BranchDaoImpl implements BranchDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	BranchRepository branchRepository;
	
	@Override
	public void saveBranchUsingHibernate(Branch b) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(b);
			session.getTransaction().commit();
			System.out.println("Branch id " + b.getBranchId() + " has been saved to database");
		}catch(Exception ex) {
			System.out.println("Problem in saving branch " + b.getBranchId()+ " to database... Error:  " + ex);
		}finally {
			session.close();
		}
	}

	@Override
	public List<Branch> findAllBranchUsingHibernate() {
		Session session = sessionFactory.openSession();
		List<Branch> branches = new ArrayList<>();
		try {
			session.beginTransaction();
			branches = session.createQuery("from Branch").list();
			session.getTransaction().commit();
		}catch(Exception ex) {
			System.out.println("Problem in getting all branch.... Error: "+ ex);
		}finally {
			session.close();
		}
		return branches;
	} 


	@Override
	public Branch findBranchByIdUsingHibernate(long id) {
		Branch b = new Branch();
		try(Session session = sessionFactory.openSession()){
			session.beginTransaction();
			b = session.get(Branch.class, id);
			session.getTransaction().commit();
		}
		return b;
	}

	@Override
	public void deleteBranchUsingHibernate(long id) {
		try(Session session = sessionFactory.openSession()){
			Branch b = new Branch();
			session.beginTransaction();
			b = session.get(Branch.class, id);
			session.delete(b);
			session.getTransaction().commit();
		}
		
	}

	@Override
	public void updateBranchUsingHibernate(Branch b) {
		try(Session session = sessionFactory.openSession()){
			session.beginTransaction();
			Branch br = session.get(Branch.class, b.getBranchId());
			if(br != null) {
				br.setBranchName(b.getBranchName());
				br.setBranchCity(b.getBranchCity());
				br.setBranchState(b.getBranchState());
				br.setBranchCountry(b.getBranchCountry());
				session.save(br);
				session.getTransaction().commit();
			}else {
				System.out.println("Cannot find the account!");
			}
		}
	}	

}
