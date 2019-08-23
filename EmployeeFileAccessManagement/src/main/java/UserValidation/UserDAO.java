package UserValidation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import UserFunctionalities.Userbean;

public class UserDAO {
	public int validate(Userbean bean1) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("file");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Userbean user1 = em.find(Userbean.class, bean1.getUserid());
		if (user1 != null) {
			if (user1.getPassword().equals(bean1.getPassword())) {
				em.getTransaction().commit();
				return 1;
			}
		}
		return 0;
	}
}