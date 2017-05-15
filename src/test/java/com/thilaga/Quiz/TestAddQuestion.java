package com.thilaga.Quiz;

import java.sql.SQLException;
import java.util.Scanner;

public class TestAddQuestion {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println("Enter your choice: \n1.ADD Q \n 2. Add Ans Opt\n 3. Exit");
		Scanner s=new Scanner(System.in);
		int a=s.nextInt();
		switch (a)
		{
		case 1:
				AddQuestion.addQues();
				break;
		case 2:
				AddQuestion.addAns();
				break;
		case 3:
				System.exit(0);
			   
		}

	}

}
