package com.thilaga.Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import connectionQ.ConnectionQuiz;

public class TestAssesment {
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		Connection c=ConnectionQuiz.getconnectQuiz();
		System.out.println("LIST OF ASSESMENT NAMES");
		String sql="select * from ASSESMENT";
		PreparedStatement pst=c.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		while(rs.next())
		{
			int assesId=rs.getInt("ID");
			String name=rs.getString("NAME");
			System.out.println("ID:"+assesId+"\tASSESSMENT NAME: "+name+"\n");			
		}
		System.out.println("ENTER THE ASSESSMENT ID:");
		Scanner nid = new Scanner(System.in);
		int assesid=nid.nextInt();
		
		System.out.println("Enter your choice: \n1.ADD Q to ASSESMENT \n 2. DEL Q from ASSESMENT\n 3. LIST ASSESSMENT Q \n 4. TAKE TEST\n 5.EXIT");
		Scanner s=new Scanner(System.in);
		int a=s.nextInt();
		switch (a)
		{
		case 1:
			AddAssesmentQ.addAssesQues(c, assesid);
				break;
		case 2:
				AddAssesmentQ.delAssesQues(c, assesid);
				break;
		case 3:
			AddAssesmentQ.listAssesQues(c, assesid);
			break;
		case 4:
			AddAssesmentQ.takeAssesment(c, assesid);
			break;
		case 5:
				System.exit(0);
		}
		rs.close();
		pst.close();
		c.close();
	}
}
		
	
