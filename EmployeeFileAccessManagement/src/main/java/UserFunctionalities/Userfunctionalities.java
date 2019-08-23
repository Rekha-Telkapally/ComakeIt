package UserFunctionalities;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;


public class Userfunctionalities extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
	private static int userid;
	private static String pass;

	
	public Userfunctionalities() {
		super();
	}

	public Userfunctionalities(Userbean a) {
		userid = a.getUserid();
		pass = a.getPassword();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		if (request.getParameter("button1") != null) {
			Userbean user = new Userbean();
			String password = (request.getParameter("password"));

			user.setUserid(userid);
			user.setPassword(password);
			Client client = ClientBuilder.newClient(new ClientConfig());
			WebTarget webTarget = client.target("http://localhost:8080/EmployeeFileAccessManagement/webapi/myresource")
					.path("validate");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response res = invocationBuilder.put(Entity.entity(user, MediaType.APPLICATION_JSON));
			String restResponse = res.readEntity(String.class);

			int i = Integer.parseInt(restResponse);
			if (i == 1) {
				response.sendRedirect("success.jsp");
			} else {
				response.sendRedirect("invalid.jsp");
			}

		} else if (request.getParameter("button2") != null) {
			Userbean user = new Userbean();
			String address = (request.getParameter("address"));
			user.setUserid(userid);
			user.setAddress(address);
			Client client = ClientBuilder.newClient(new ClientConfig());
			WebTarget webTarget = client.target("http://localhost:8080/EmployeeFileAccessManagement/webapi/myresource")
					.path("validate3");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response res = invocationBuilder.put(Entity.entity(user, MediaType.APPLICATION_JSON));
			String restResponse = res.readEntity(String.class);
			int n = Integer.parseInt(restResponse);

			if (n == 1) {
				response.sendRedirect("success.jsp");
			} else {
				response.sendRedirect("fail.jsp");
			}
		}
		if (request.getParameter("button3") != null) {
			response.sendRedirect("index.jsp");
		}
	}
}
