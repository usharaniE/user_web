package org.jsp.userweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;	
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsp.userweb.dao.UserDao;
import org.jsp.userweb.dto.User;

@WebServlet("/save")
@SuppressWarnings("all")
public class SaveUser extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		long phone = Long.parseLong(req.getParameter("phone"));
		String name = req.getParameter("name");
		String gender = req.getParameter("gender");
		User user = new User();
		user.setGender(gender);
		user.setId(id);
		user.setName(name);
		user.setPhone(phone);
		UserDao dao = new UserDao();
		dao.saveUser(user);
		PrintWriter writer = resp.getWriter();
		writer.write("<html><body><h1>User Saved Succesfully</h1></body></html>");
	}
}
