package result;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class Gettingvalues
 */
public class Gettingvalues extends HttpServlet {
	Connection conn;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Gettingvalues() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("org.postgresql.Driver");
			conn=DriverManager.getConnection("jdbc:postgresql://localhost:5433/"+"bank","postgres","Vinay@123");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String s1=request.getParameter("stname");
			String s2=request.getParameter("htnum");
			PreparedStatement ps=conn.prepareStatement("select * from result where stu_name=? and ht_number=?");
			ps.setString(1, s1);
			ps.setInt(2,Integer.parseInt(s2));
			ResultSet rs=ps.executeQuery();
			if(s1.equals("Admin")) {
				RequestDispatcher rd=request.getRequestDispatcher("data");
				rd.forward(request, response);
				
			}else {
				RequestDispatcher rd=request.getRequestDispatcher("data1");
				rd.forward(request, response);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
