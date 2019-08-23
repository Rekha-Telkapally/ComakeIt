package ManagerFunctionalities;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;


public class Grantedleave extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public Grantedleave() {
		super();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());

		int id = Integer.parseInt(request.getParameter("Id"));
		String status = request.getParameter("status");
		if (status.equals("accept")) {

			Client client = ClientBuilder.newClient(new ClientConfig());
			WebTarget webTarget = client.target("http://localhost:8080/EmployeeFileAccessManagement/webapi/myresource")
					.path("accept");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response res = invocationBuilder.put(Entity.entity(id, MediaType.APPLICATION_JSON));
			String restResponse = res.readEntity(String.class);
			response.sendRedirect("Accepted.jsp");

		}

		else {
			Client client = ClientBuilder.newClient(new ClientConfig());
			WebTarget webTarget = client.target("http://localhost:8080/EmployeeFileAccessManagement/webapi/myresource")
					.path("reject");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response res = invocationBuilder.put(Entity.entity(id, MediaType.APPLICATION_JSON));
			String restResponse = res.readEntity(String.class);
			response.sendRedirect("Rejected.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
