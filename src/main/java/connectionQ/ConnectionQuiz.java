package connectionQ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionQuiz 
{
	public static Connection getconnectQuiz() throws Exception 
	{
		Connection con=null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/quiz";
		String username="root";
		String password="thilaga";
		con=DriverManager.getConnection(url,username,password);
		System.out.println(con);
		return (con);
		}
		catch(ClassNotFoundException e)
		{
			throw new Exception("Unable to Connect");
		}
		catch(SQLException e)
		{
			throw new Exception("");
		}
		finally
		{
			con.close();
		}
		
		}
	}


