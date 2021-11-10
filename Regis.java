package allserv;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calldatabase.GetData;

/**
 * Servlet implementation class Regis
 */
@WebServlet("/Regis")
public class Regis extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out=response.getWriter();
			int cid=(int)Math.random()*100;
			
			String cname=request.getParameter("cname");
			String cmail=request.getParameter("cmail");
			String cphn=request.getParameter("cphn");
			String caddr=request.getParameter("caddr");
			String cpass=request.getParameter("cpass");
			String crpass=request.getParameter("crpass");
			
			Connection cn=GetData.getCn();
			PreparedStatement ps=cn.prepareStatement("insert into customer values(?,?,?,?,?,?,?)");
			ps.setInt(1, cid);
			ps.setString(2, cname);
			ps.setString(3, cmail);
			ps.setString(4, cphn);
			ps.setString(5, caddr);
			ps.setString(6, cpass);
			ps.setString(7, crpass);
			ps.execute();
			out.print("Resitration Success For ID :"+cid);
			RequestDispatcher rd=request.getRequestDispatcher("Register.html");
			rd.include(request, response);
			
			}catch(Exception e) {
			System.out.println(e);
		}
	}

}
