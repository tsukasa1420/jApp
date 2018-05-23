package enquete.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enquete.Java.User;
	/**
		userMake.jsp
		-> userOperate.servlet
		-> Login.userMakeFunc()
		-> DAO_user.userMake()
		-> SQL
	*/
@WebServlet("/UserOperate")
public class UserOperate extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		User user = new User();

		user.userMakeFunc(request, response);
		out.println("Finish<br>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}