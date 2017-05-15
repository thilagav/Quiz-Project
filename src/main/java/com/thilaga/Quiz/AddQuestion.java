package com.thilaga.Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import connectionQ.ConnectionQuiz;



public class AddQuestion {
	public static void addQues() throws ClassNotFoundException, SQLException
	{
		Connection con=ConnectionQuiz.getconnectQuiz();
		String sql="INSERT INTO Question(Q_Text, Ques_Type) VALUES(?,?)";
		System.out.println(sql);
		PreparedStatement pst=con.prepareStatement(sql);
		System.out.println("ENTER THE QUESTION:");
		Scanner n = new Scanner(System.in);
		String b=n.nextLine();
		pst.setObject(1, b);
		System.out.println("ENTER THE QUESTION_TYPE(1 or 2):");
		Scanner in2=new Scanner(System.in);
		int a=in2.nextInt();
		pst.setInt(2,a );
		int row=pst.executeUpdate();
		System.out.println("No.of.rows Inserted"+ row);
		
	}
	public static void addAns() throws ClassNotFoundException, SQLException
	{
		Connection con=ConnectionQuiz.getconnectQuiz();
		String sql="INSERT INTO Q_Ans_Opt(Ans, Q_Text_Id,Score) VALUES(?,?,?)";
		System.out.println(sql);
		String sql2="select Id, Q_Text from Question where Id not in(select Q.Id from Question Q, Q_Ans_Opt Qopt where Q.Id=Qopt.Q_Text_Id)";
		PreparedStatement pst=con.prepareStatement(sql2);
		PreparedStatement pst1=con.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		int row=0;
		while(rs.next())
		{
		Qschema qs=new Qschema();
		qs.qid=rs.getInt("Id");
		qs.qtext=rs.getString("Q_Text");
		System.out.println("ENTER THE ANSWER FOR:"+ qs.qtext);
		Scanner n = new Scanner(System.in);
		String b=n.nextLine(); 
		pst1.setObject(1, b);
		pst1.setInt(2,qs.qid );
		System.out.println("Enter the score:");
		Scanner n1 = new Scanner(System.in);
		int c=n1.nextInt();
		pst1.setInt(3,c );
		pst1.executeUpdate();
		row++;
		}
		System.out.println("No.of.rows Inserted"+ row);
		
	}
	

}
