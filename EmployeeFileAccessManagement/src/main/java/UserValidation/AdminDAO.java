package UserValidation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import AdminFunctionalities.Adminbean;

public class AdminDAO {
	public int validate1(Adminbean bean) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("file");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Adminbean u1 = em.find(Adminbean.class, bean.getAdminid());
		if (u1 != null && u1.getPassword().equals(bean.getPassword())) {
			List query = em
					.createNativeQuery(
							"SELECT * FROM admin WHERE ReportingManager='" + bean.getReportingManager() + "'")
					.getResultList();
			if (query.size() > 0) {

				em.getTransaction().commit();
				return 1;
			}
			return 0;
		}

		return 2;
	}
}
