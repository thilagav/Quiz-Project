package quizValidator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connectionQ.ConnectionQuiz;

public class ValidateAssesmentID {
	public static void  verifyAsessID(int id) throws Exception
	{
		Connection c=ConnectionQuiz.getconnectQuiz();

		System.out.println("LIST OF ASSESMENT NAMES");
		String sql="select * from ASSESMENT where ID="+id;
		PreparedStatement pst=c.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		if(rs.next()==false)
		{
			throw new Exception("Assesment ID is not Found");
		}
	}
}
