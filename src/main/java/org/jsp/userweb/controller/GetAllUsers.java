package org.jsp.userweb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsp.userweb.dao.UserDao;
import org.jsp.userweb.dto.User;

@WebServlet("/getall")
public class GetAllUsers extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDao dao = new UserDao();
		List<User> users = dao.getAllUsers();
		PrintWriter writer = resp.getWriter();
		for (User user : users) {
			writer.write("<html><body><h1>Your details</h1>");
			writer.write("<h2>ID:" + user.getId() + "</h2>");
			writer.write("<h2>Name:" + user.getName() + "</h2>");
			writer.write("<h2>Gender:" + user.getGender() + "</h2>");
			writer.write("<h2>Phone:" + user.getPhone() + "</h2>");
			writer.write("<h2>------------------</h2></body></html>");
		}
	}
}
