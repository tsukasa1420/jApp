package enquete.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enquete.Java.Result;

@WebServlet("/ResultOperate")
public class ResultOperate extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		Result re = new Result();

		int priNo = 0;
		if( request.getParameter("priNo") == null ) priNo = 0;
		else priNo = Integer.parseInt( request.getParameter("priNo") );

		/*◆◆◆*/System.out.println(priNo);

		if( priNo != 0 ) re.resultView(request, response, priNo);
		else re.resultViewAll(request,response);

		out.println("ResultOperate Link OK.");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
