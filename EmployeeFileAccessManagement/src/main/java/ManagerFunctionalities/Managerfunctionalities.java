package ManagerFunctionalities;

import java.io.IOException;
import java.lang.reflect.Type;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import AdminFunctionalities.Adminbean;


public class Managerfunctionalities extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static int id;
	public static String password;

	
	public Managerfunctionalities() {
		super();
	}

	public Managerfunctionalities(int id) {
		this.id = id;

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
			response.sendRedirect("LeaveForm.jsp");
		}
		if (request.getParameter("button2") != null) {
			Leavebean leave = new Leavebean();

			leave.setId(Integer.parseInt(request.getParameter("id")));
			leave.setReason(request.getParameter("dropdown"));
			leave.setStartdate(request.getParameter("start_date"));
			leave.setDays(Integer.parseInt(request.getParameter("days")));
			Client client = ClientBuilder.newClient(new ClientConfig());
			WebTarget webTarget = client.target("http://localhost:8080/EmployeeFileAccessManagement/webapi/myresource")
					.path("leave");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response res = invocationBuilder.post(Entity.entity(leave, MediaType.APPLICATION_JSON));
			String restResponse = res.readEntity(String.class);
			int i = Integer.parseInt(restResponse);
			if (i == 0) {
				response.sendRedirect("applyed.jsp");

			}
		}

		if (request.getParameter("button3") != null) {

			Leavebean leave = new Leavebean();

			Client newClient = ClientBuilder.newClient(new ClientConfig());
			WebTarget web = newClient.target("http://localhost:8080/EmployeeFileAccessManagement/webapi/myresource")
					.path("grantedleave/" + id);
			Invocation.Builder inBuilder = web.request(MediaType.APPLICATION_JSON);
			Response res = inBuilder.get();
			String restResponse = res.readEntity(String.class);

			Type type = new TypeToken<List<Leavebean>>() {
			}.getType();
			List<Leavebean> listPersons = new Gson().fromJson(restResponse, type);

			RequestDispatcher r = request.getRequestDispatcher("/Grantedleave.jsp");
			request.setAttribute("list", listPersons);
			r.forward(request, response);
		}
		if (request.getParameter("button4") != null) {

			Client newClient = ClientBuilder.newClient(new ClientConfig());
			WebTarget web = newClient.target("http://localhost:8080/EmployeeFileAccessManagement/webapi/myresource")
					.path("status/" + id);
			Invocation.Builder inBuilder = web.request(MediaType.APPLICATION_JSON);
			Response res = inBuilder.get();
			String restResponse = res.readEntity(String.class);
			int i = Integer.parseInt((restResponse));
			RequestDispatcher r = request.getRequestDispatcher("/displaystatus.jsp");
			request.setAttribute("list", i);
			r.forward(request, response);

		}

		if (request.getParameter("button5") != null) {
			response.sendRedirect("Manager.jsp");
		}
		if (request.getParameter("button") != null) {
			response.sendRedirect("Manager.jsp");
		}
		if (request.getParameter("button6") != null) {
			response.sendRedirect("index.jsp");
		}

	}
}