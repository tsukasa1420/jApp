package enquete.Java;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login {
	public void loginFunc(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			String userName = request.getParameter("userName");
			String pass = request.getParameter("userName");

			out.println(userName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
// + "<br>"
// "<br>" +