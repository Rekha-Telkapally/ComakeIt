package com.Application.File;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import AdminFunctionalities.Adminbean;
import AdminFunctionalities.Adminfunctionalities;
import ManagerFunctionalities.Managerfunctionalities;
import UserFunctionalities.Userbean;
import UserFunctionalities.Userfunctionalities;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public Login() {
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		Userbean bean = new Userbean();
		bean.setUserid(Integer.parseInt(id));
		bean.setPassword(password);

		int t = ClientBuilder.newClient()
				.target("http://localhost:8080/EmployeeFileAccessManagement/webapi/myresource/validate1")
				.request(MediaType.APPLICATION_JSON).post(Entity.entity(bean, MediaType.APPLICATION_JSON))
				.readEntity(int.class);

		if (t == 1) {
			Userfunctionalities a = new Userfunctionalities(bean);
			response.sendRedirect("user.jsp");
		} else {

			Adminbean admin = new Adminbean();

			admin.setAdminid(Integer.parseInt(id));
			admin.setPassword(password);
			admin.setReportingManager(Integer.parseInt(id));
			int n = ClientBuilder.newClient()
					.target("http://localhost:8080/EmployeeFileAccessManagement/webapi/myresource/validate2")
					.request(MediaType.APPLICATION_JSON).post(Entity.entity(admin, MediaType.APPLICATION_JSON))
					.readEntity(int.class);

			if (n == 1) {
				Managerfunctionalities mg = new Managerfunctionalities(admin.getAdminid());
				response.sendRedirect("Manager.jsp");
			} else if (n == 0) {
				Adminfunctionalities ad = new Adminfunctionalities(admin);
				response.sendRedirect("admin.jsp");
			}else {
				
				response.sendRedirect("error.jsp");
		}
		
	}
	}

}