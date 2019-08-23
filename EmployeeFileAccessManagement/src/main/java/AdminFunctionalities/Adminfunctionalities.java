package AdminFunctionalities;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import ManagerFunctionalities.Leavebean;
import UserFunctionalities.Userbean;


public class Adminfunctionalities extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static int adminid;
	public static String pass;

	public Adminfunctionalities() {
		super();
		
	}

	public Adminfunctionalities(Adminbean ad) {
		adminid = ad.getAdminid();
		pass = ad.getPassword();

	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		if (request.getParameter("button1") != null) {
			response.sendRedirect("adduser.jsp");
		}
		if (request.getParameter("button5") != null) {
			Userbean us = new Userbean();
			us.setUserid(Integer.parseInt(request.getParameter("uid")));
			us.setUsrname(request.getParameter("uname"));
			us.setAddress(request.getParameter("addr"));

			Client client = ClientBuilder.newClient(new ClientConfig());
			WebTarget webTarget = client.target("http://localhost:8080/EmployeeFileAccessManagement/webapi/myresource")
					.path("validate4");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response res = invocationBuilder.post(Entity.entity(us, MediaType.APPLICATION_JSON));
			String restResponse = res.readEntity(String.class);
			int i = Integer.parseInt(restResponse);
			if (i == 0) {
				response.sendRedirect("successadd.jsp");

			}
		}
		if (request.getParameter("button2") != null) {
			response.sendRedirect("delete.jsp");
		}
		if (request.getParameter("button6") != null) {

			Userbean us = new Userbean();
			us.setUserid(Integer.parseInt(request.getParameter("userid")));

			Client client = ClientBuilder.newClient(new ClientConfig());
			WebTarget webTarget = client.target("http://localhost:8080/EmployeeFileAccessManagement/webapi/myresource")
					.path("validate5/" + us.getUserid());
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response res = invocationBuilder.delete();
			String restResponse = res.readEntity(String.class);
			int i = Integer.parseInt(restResponse);
			if (i == 1) {
				response.sendRedirect("successadd.jsp");
			}
		}
		if (request.getParameter("button3") != null) {
			Userbean us = new Userbean();
			Client client = ClientBuilder.newClient(new ClientConfig());
			WebTarget webTarget = client.target("http://localhost:8080/EmployeeFileAccessManagement/webapi/myresource")
					.path("list");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response res = invocationBuilder.get();
			List<String> users = res.readEntity(List.class);

			request.setAttribute("list", users);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/display.jsp");
			rd.forward(request, response);
		}
		if (request.getParameter("button4") != null) {

			Client client = ClientBuilder.newClient(new ClientConfig());
			WebTarget webTarget = client.target("http://localhost:8080/EmployeeFileAccessManagement/webapi/myresource")
					.path("granted");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response res = invocationBuilder.get();
			List<String> user1 = res.readEntity(List.class);

			request.setAttribute("list", user1);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/display.jsp");
			rd.forward(request, response);
		}
		if (request.getParameter("button9") != null) {
			response.sendRedirect("index.jsp");
		}
		if (request.getParameter("button7") != null) {
			response.sendRedirect("AdminLeave.jsp");
		}
		if (request.getParameter("button8") != null) {
			Leavebean leave = new Leavebean();
			System.out.println("gnfh");
			leave.setId(Integer.parseInt(request.getParameter("id")));
			leave.setReason(request.getParameter("dropdown"));
			leave.setStartdate(request.getParameter("start_date"));
			leave.setDays(Integer.parseInt(request.getParameter("days")));
			Client client = ClientBuilder.newClient(new ClientConfig());
			WebTarget webTarget = client.target("http://localhost:8080/EmployeeFileAccessManagement/webapi/myresource")
					.path("leave1");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response res = invocationBuilder.post(Entity.entity(leave, MediaType.APPLICATION_JSON));
			String restResponse = res.readEntity(String.class);

			int i = Integer.parseInt(restResponse);
			if (i == 0) {
				response.sendRedirect("successadd.jsp");

			}
		}
		if (request.getParameter("button10") != null) {
			Client newClient = ClientBuilder.newClient(new ClientConfig());
			WebTarget web = newClient.target("http://localhost:8080/EmployeeFileAccessManagement/webapi/myresource")
					.path("leavestatus/" + adminid);
			Invocation.Builder inBuilder = web.request(MediaType.APPLICATION_JSON);
			Response res = inBuilder.get();
			String restResponse = res.readEntity(String.class);
			int i = Integer.parseInt((restResponse));
			RequestDispatcher r = request.getRequestDispatcher("/Leavestatus.jsp");
			request.setAttribute("list", i);
			r.forward(request, response);

		}
	}

}
