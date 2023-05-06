package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.omg.CORBA.PUBLIC_MEMBER;

import dto.User;

public class UserDao {
	EntityManagerFactory f1=Persistence.createEntityManagerFactory("dev");
	EntityManager manager=f1.createEntityManager();
	EntityTransaction t1=manager.getTransaction();
	
	public void save(User user)
	{
		t1.begin();
		manager.persist(user);
		t1.commit();
	}
	public   User find(int userid)
	{
		return manager.find(User.class, userid);
	}

	public void Update (User user)
	{
		t1.begin();
		manager.merge(user);
		t1.commit();
	}

}
