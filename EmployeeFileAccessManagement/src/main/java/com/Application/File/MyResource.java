package com.Application.File;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import com.google.gson.Gson;

import AdminFunctionalities.Adminbean;
import ManagerFunctionalities.Leavebean;
import UserFunctionalities.Userbean;
import UserValidation.AdminDAO;
import UserValidation.UserDAO;


@Path("myresource")
public class MyResource {

	private static final String id = null;

	@POST
	@Path("validate1")
	public int getIt(Userbean bean) {
		Userbean bean1 = new Userbean();
		bean1.setUserid(bean.getUserid());
		bean1.setPassword(bean.getPassword());
		int value = new UserDAO().validate(bean1);

		return value;
	}

	@POST
	@Path("validate2")
	public int getIt(Adminbean admin) {
		int v = new AdminDAO().validate1(admin);
		return v;
	}

	@PUT
	@Path("validate")

	public int getIt1(Userbean bean) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("file");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		if (bean.getPassword().matches("^(([A-Za-z])(?=.*\\d)(?=.*\\W)(?=.*[a-zA-Z])).{8,}")) {
			Query query = em.createNativeQuery(
					"Update user set password='" + bean.getPassword() + "' WHERE userid ='" + bean.getUserid() + "' ");
			query.executeUpdate();
			bean.setFirsttimelogin(0);
			Query query1 = em
					.createNativeQuery("Update user set firsttimelogin=1 WHERE userid ='" + bean.getUserid() + "' ");
			query1.executeUpdate();
			em.getTransaction().commit();
			return 1;

		}
		return 0;
	}

	@PUT
	@Path("validate3")

	public int getIt2(Userbean bean) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("file");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Userbean u = em.find(Userbean.class, bean.getUserid());
		if (u.getFirsttimelogin() == 1) {
			Query query = em.createNativeQuery(
					"UPDATE user SET Address = '" + bean.getAddress() + "' WHERE userid ='" + bean.getUserid() + "'");
			query.executeUpdate();
			em.getTransaction().commit();
			return 1;
		}
		return 0;
	}

	@POST
	@Path("validate4")
	public int getIt3(Userbean admin) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("file");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		admin.setGrantedpermision(0);
		admin.setPassword("Hello");
		admin.setStartdate(LocalDate.now().toString());
		admin.setFirsttimelogin(0);

		em.persist(admin);

		em.getTransaction().commit();
		return 0;
	}

	@DELETE
	@Path("validate5/{id}")
	public int getIt4(@PathParam("id") int id) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("file");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Userbean u = em.find(Userbean.class, id);
		em.remove(u);
		em.getTransaction().commit();
		return 1;
	}

	@SuppressWarnings("unchecked")
	@GET
	@Path("list")
	public List<String> displayusers() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("file");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<String> users = new ArrayList<String>();
		users = em.createNativeQuery("SELECT username from user order by username").getResultList();
		em.getTransaction().commit();
		return users;

	}

	@SuppressWarnings("unchecked")
	@GET
	@Path("granted")
	public List<String> displaygrantedusers() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("file");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		List<Integer> users = new ArrayList<Integer>();
		users = (List<Integer>) em
				.createNativeQuery(
						" SELECT userid from user where DATEDIFF(CURDATE(),startdate)>=100 and GrantedPermision=0")
				.getResultList();
		List<String> users1 = new ArrayList<String>();
		Iterator i1 = users.iterator();
		for (Integer x : users) {
			Query query = em.createNativeQuery(" Update user set GrantedPermision=1  where userid='" + x + "'");
			query.executeUpdate();
		}
		for (Integer x : users) {
			Userbean u = em.find(Userbean.class, x);
			users1.add(u.getUsrname());
		}
		em.getTransaction().commit();
		return users1;

	}

	@POST
	@Path("leave")
	public int getIt5(Leavebean leave) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("file");
		EntityManager em = emf.createEntityManager();
		Adminbean a = new Adminbean();
		a = em.find(Adminbean.class, leave.getId());
		em.getTransaction().begin();
		leave.setManagerid(a.getReportingManager());
		leave.setStatus("pending ");
		em.persist(leave);

		em.getTransaction().commit();
		return 0;
	}

	@POST
	@Path("leave1")
	public int getIt6(Leavebean leave) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("file");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		leave.setStatus("pending ");
		em.persist(leave);

		em.getTransaction().commit();
		return 0;
	}

	@GET
	@Path("grantedleave/{id}")
	public String Grantedleave(@PathParam("id") int id) {

		Gson gson = new Gson();

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("file");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<Leavebean> a = em
				.createQuery("Select x from LeaveDetails x where x.managerid='" + id + "' and x.status='pending'",
						Leavebean.class)
				.getResultList();

		em.getTransaction().commit();

		return gson.toJson(a);

	}

	@PUT
	@Path("accept")

	public int Accept(int id) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("file");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createNativeQuery("UPDATE admin SET LeaveStatus = 1 where adminid='" + id + "'");
		query.executeUpdate();
		Query query1 = em.createNativeQuery("UPDATE LeaveDetails SET status='Accepted' where Id='" + id + "'");
		query1.executeUpdate();
		em.getTransaction().commit();
		return 1;
	}

	@PUT
	@Path("reject")

	public int Reject(int id) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("file");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createNativeQuery("UPDATE admin SET LeaveStatus = 0 where adminid='" + id + "'");
		query.executeUpdate();
		Query query1 = em.createNativeQuery("UPDATE LeaveDetails SET status='Rejected' where Id='" + id + "'");
		query1.executeUpdate();
		em.getTransaction().commit();
		return 1;
	}

	@GET
	@Path("status/{id}")
	public int status(@PathParam("id") int id) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("file");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Adminbean u = em.find(Adminbean.class, id);

		return u.getLeaveStatus();

	}

	@GET
	@Path("leavestatus/{id}")
	public int Leavestatus(@PathParam("id") int id) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("file");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Adminbean u = em.find(Adminbean.class, id);

		return u.getLeaveStatus();

	}

}
