package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import entity.*;
import java.util.*;

public class Dao {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Harj1PU");

	
	public void addRegister(Register reg) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
        em.persist(reg);
        
        em.getTransaction().commit();
        em.close();
	}
	
	public void addEvent(int eventNumber, int regNumber, double amount) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
        Register reg = em.find(Register.class, regNumber);
        SalesEvent evt = new SalesEvent(eventNumber, reg, amount);
        
        em.persist(evt);
        
        em.getTransaction().commit();
        em.close();	
	}

	public List<SalesEvent> retrieveSmallSales(double limit) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();


		String jpql = "SELECT s FROM SalesEvent s WHERE s.amount < :limit";
		TypedQuery<SalesEvent> query = em.createQuery(jpql, SalesEvent.class);
		query.setParameter("limit", limit);

		List<SalesEvent> result = query.getResultList();

		em.getTransaction().commit();
		em.close();
		return result;
	}

	public List<SalesEvent> retrieveSmallSales2(double limit) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<SalesEvent> cq = cb.createQuery(SalesEvent.class);
		Root<SalesEvent> salesEvent = cq.from(SalesEvent.class);
		cq.select(salesEvent);
		cq.where(cb.lt(salesEvent.get("amount"), limit));

		TypedQuery<SalesEvent> query = em.createQuery(cq);
		List<SalesEvent> result = query.getResultList();

		em.getTransaction().commit();
		em.close();
		return result;
	}

	public void serviceFee(double fee) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		String jpql = "UPDATE SalesEvent s SET s.amount = s.amount + :fee";
		Query query = em.createQuery(jpql);
		query.setParameter("fee", fee);

		int rowsUpdated = query.executeUpdate();

		em.getTransaction().commit();
		em.close();

		System.out.println("Updated " + rowsUpdated + " sales events with service fee.");
	}

	public void serviceFee2(double fee) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<SalesEvent> cu = cb.createCriteriaUpdate(SalesEvent.class);
		
		Root<SalesEvent> salesEvent = cu.from(SalesEvent.class);
		cu.set(salesEvent.<Double>get("amount"), cb.sum(salesEvent.get("amount"), fee));

		Query query = em.createQuery(cu);
		int rowsUpdated = query.executeUpdate();

		em.getTransaction().commit();
		em.close();

		System.out.println("Updated " + rowsUpdated + " sales events with service fee.");
	}


	public void deleteEvents(){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		String jpql = "DELETE SalesEvent";
		Query query = em.createQuery(jpql);
		query.executeUpdate();

		em.getTransaction().commit();
		em.close();
	}

	public void deleteEvents2(){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<SalesEvent> cd = cb.createCriteriaDelete(SalesEvent.class);

		Query query = em.createQuery(cd);
		query.executeUpdate();

		em.getTransaction().commit();
		em.close();
	}


}
