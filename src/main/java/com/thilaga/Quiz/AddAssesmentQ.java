package com.thilaga.Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AddAssesmentQ {
	public static void addAssesQues(Connection con,int id) throws ClassNotFoundException, SQLException
	{
		int row=0;
			
		String sql="select Q.ID,Q.Q_TEXT from QUESTION Q, QUES_TYPE QT where QT.IS_ACTIVE=1 and Q.Q_TYPE_ID=QT.ID";
		PreparedStatement pst1=con.prepareStatement(sql);
		ResultSet rs1=pst1.executeQuery();
		while(rs1.next())
		{
			String ques=rs1.getString("Q.Q_Text");
			int quesId=rs1.getInt("Q.ID");
			System.out.println(ques+"\n you want to ADD this Question say (YES-'1' or NO-'0')\n");
			Scanner n1 = new Scanner(System.in);
			int ch=n1.nextInt();
			if(ch==1)
			{
				String sql2="insert into ASSES_QUESTION(ASSES_ID,QUES_TEXT_ID) values(?,?)";
				PreparedStatement pst2=con.prepareStatement(sql2);
				pst2.setInt(1, id);
				pst2.setInt(2, quesId);
				int r=pst2.executeUpdate();	
				row=row+r;
			}		
			
		}
		
		System.out.println("No.of.Questions included is :"+ row);
		
		}
	public static void listAssesQues(Connection con,int id) throws ClassNotFoundException, SQLException
	{
		String sql="select Q.Q_TEXT,Q.ID from QUESTION Q , ASSES_QUESTION AQ where AQ.ASSES_ID=? and AQ.QUES_TEXT_ID=Q.ID";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setInt(1, id);
		ResultSet rs=pst.executeQuery();
		while(rs.next())
		{
			String s=rs.getString("Q_TEXT");
			int qid=rs.getInt("Q.ID");
			System.out.println("Question: "+ s);
			System.out.println("\n Choices: ");
			String sql2="select ANS_TEXT from QUES_ANS_OPT where Q_TEXT_ID= ?";
			PreparedStatement pst1=con.prepareStatement(sql2);
			pst1.setInt(1, qid);
			ResultSet rs1=pst1.executeQuery();
			while(rs1.next())
			{
				String ch=rs1.getString("ANS_TEXT");
				System.out.println(ch+"\n");
			}
		}
	}
	public static void delAssesQues(Connection con,int id) throws ClassNotFoundException, SQLException
	{
		int row=0;
			
		String sql="select Q.Q_TEXT,Q.ID from QUESTION Q , ASSES_QUESTION AQ where AQ.ASSES_ID=? and AQ.QUES_TEXT_ID=Q.ID";
		PreparedStatement pst1=con.prepareStatement(sql);
		pst1.setInt(1, id);
		ResultSet rs1=pst1.executeQuery();
		while(rs1.next())
		{
			String ques=rs1.getString("Q.Q_Text");
			int quesId=rs1.getInt("Q.ID");
			System.out.println(ques+"\n you want to DELETE this Question say (YES-'1' or NO-'0')\n");
			Scanner n1 = new Scanner(System.in);
			int ch=n1.nextInt();
			if(ch==1)
			{
				String sql2="delete from ASSES_QUESTION where QUES_TEXT_ID=? and ASSES_ID=?";
				PreparedStatement pst2=con.prepareStatement(sql2);
				pst2.setInt(1, quesId);
				pst2.setInt(2, id);
				int r=pst2.executeUpdate();	
				row=row+r;
			}		
			
		}
		
		System.out.println("No.of.Questions deleted is :"+ row);
		
		} 
	public static void takeAssesment(Connection con,int id) throws ClassNotFoundException, SQLException
	{
		int score=0;
		String sql="select Q.Q_TEXT,Q.ID from QUESTION Q , ASSES_QUESTION AQ where AQ.ASSES_ID=? and AQ.QUES_TEXT_ID=Q.ID";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setInt(1, id);
		ResultSet rs=pst.executeQuery();
		while(rs.next())
		{
			String s=rs.getString("Q_TEXT");
			int qid=rs.getInt("Q.ID");
			System.out.println("Question: "+ s);
			System.out.println("\n Choices: ");
			String sql2="select ANS_TEXT from QUES_ANS_OPT where Q_TEXT_ID= ?";
			PreparedStatement pst1=con.prepareStatement(sql2);
			pst1.setInt(1, qid);
			ResultSet rs1=pst1.executeQuery();
			while(rs1.next())
			{
				String ch=rs1.getString("ANS_TEXT");
				System.out.println(ch);
			}
			System.out.println("Your Answer:\n");
			Scanner s1= new Scanner(System.in);
			String ans=s1.nextLine();
			String sql3=" select Q.Q_SCORE from QUESTION Q, QUES_ANS_OPT QO, QUES_CORRECT_ANS QC where QO.ANS_TEXT=? and Q.ID=QO.Q_TEXT_ID and Q.ID=QC.QUES_ID and QO.ID=QC.Q_ANS_OPT_ID";	
			PreparedStatement pst2=con.prepareStatement(sql3);
			pst2.setString(1, ans);
			ResultSet rs2= pst2.executeQuery();
			if(rs2.next()==true)
			{
				int i= rs2.getInt("Q.Q_SCORE");
				score=score+i;				
			}
		}
		System.out.println("\n Your Score: "+score);
	}
				
}
