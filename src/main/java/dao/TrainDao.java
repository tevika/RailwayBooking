package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Train;
import dto.User;

public class TrainDao {
	EntityManagerFactory f1=Persistence.createEntityManagerFactory("dev");
	EntityManager manager=f1.createEntityManager();
	EntityTransaction t1=manager.getTransaction();
	
	public void save(Train train)
	{
		t1.begin();
		manager.persist(train);
		t1.commit();
	}
	public List<Train> fetchAll() {
		return manager.createQuery("select x from Train x").getResultList();
		}
	public void delete(int tnumber) {
		t1.begin();
        manager.remove(manager.find(Train.class,tnumber));
        t1.commit();
	}
	public Train fetch(int number) {
		return manager.find(Train.class, number);
	}
	public void update(Train train)
	{
		t1.begin();
		manager.merge(train);
		t1.commit();
}

}
