package result;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class Result
 */
public class Result extends HttpServlet {
	Connection conn;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Result() {
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
			PreparedStatement ps=conn.prepareStatement("select * from result");
			//ps.setString(1, s1);
			ResultSet rs=ps.executeQuery();
			PrintWriter pw=response.getWriter();
			pw.println("<html><head><style>table,th,td{"
														+ "border:2px solid cadetblue;"
															+ " border-collapse: collapse;"
															+ "text-align:center;"
															+ "}"
															+ "th{"
															+ "color:blue;}"
														    +"td{"
															+ "color:rgb(173, 13, 185);}"
															+ "a{"
															+ "text-decoration: none;"
															+ "}"
															+ "body{"
															+ "background-color:bisque;"
														    +"text-align: center;"
															+"}"
															+"#k{"
															+"font-size:35px;"
															+"color:blue;"
															+"}"
															+ "h1{"
														    +" color: blueviolet;}"
					                                        +"</style></head><body>");
			pw.println("<form action=''>");
			pw.println("<table style=width:100%>");
			pw.println("<tr>");
			pw.println("<th>Stu_Name</th>");
			pw.println("<th>Ht_Number</th>");
			pw.println("<th>Telugu</th>");
			pw.println("<th>Hindhi</th>");
			pw.println("<th>English</th>");
			pw.println("<th>Mathas</th>");
			pw.println("<th>Pyshics</th>");
			pw.println("<th>Scociel</th>");
			pw.println("<th>Ns</th>");
			pw.println("</tr>");
			while(rs.next()) {
			pw.println("<tr>");
			pw.println("<td>"+rs.getString("stu_name")+"</td>");
			pw.println("<td>"+rs.getString("ht_number")+"</td>");
			pw.println("<td>"+rs.getString("telugu")+"</td>");
			pw.println("<td>"+rs.getString("hindhi")+"</td>");
			pw.println("<td>"+rs.getString("english")+"</td>");
			pw.println("<td>"+rs.getString("mathas")+"</td>");
			pw.println("<td>"+rs.getString("pyshics")+"</td>");
			pw.println("<td>"+rs.getString("scociel")+"</td>");
			pw.println("<td>"+rs.getString("ns")+"</td>");
			
			pw.println("</tr>");
			}
			pw.println("</table>");
			pw.println("<a href='result.html'>Homepage</a>");
			
			pw.println("</form>");
			
			pw.println("</body></html>");
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
