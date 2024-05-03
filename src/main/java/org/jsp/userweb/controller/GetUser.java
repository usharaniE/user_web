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

@WebServlet("/get")
public class GetUser extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		UserDao dao = new UserDao();
		User user = dao.getUser(id);
		PrintWriter writer = resp.getWriter();
		if (user != null) {
			writer.write("<html><body><h1>Your details</h1>");
			writer.write("<h2>ID:" + user.getId() + "</h2>");
			writer.write("<h2>Name:" + user.getName() + "</h2>");
			writer.write("<h2>Gender:" + user.getGender() + "</h2>");
			writer.write("<h2>Phone:" + user.getPhone() + "</h2></body></html>");
		} else {
			writer.write("<html><body><h1>User Not Found</h1></body></html>");
		}
	}
}
