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
 * Servlet implementation class AddJob
 */
@WebServlet("/AddJob")
public class AddJob extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String JobLocation=request.getParameter("size");
		String kilometer=request.getParameter("kg");
		String Facility=request.getParameter("facility");
		
		String url="jdbc:mysql://localhost:3306/test";
		
		try {
			
			
			PrintWriter out=response.getWriter();
			int id=(int)(Math.random()*1000);
			String location=(request.getParameter("location"));
			int Kg=Integer.parseInt(request.getParameter("Kg"));
			String facility=request.getParameter("facility");
			String status="free";
			
			Connection cn=GetData.getCn();
			PreparedStatement ps=cn.prepareStatement("insert into addjob values(?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, location);
			ps.setInt(3, Kg);
			ps.setString(4, facility);
			ps.setString(5, status);
			ps.execute();
			out.print("record saved...");
			RequestDispatcher rd=request.getRequestDispatcher("create.html");
			rd.include(request, response);
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
