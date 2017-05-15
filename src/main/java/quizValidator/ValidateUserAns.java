package quizValidator;

public class ValidateUserAns {
public static void verifyUserAns(String a) throws Exception
{
	if(a==null || a.trim().equals(""))
	{
		throw new Exception("Please enter Your Answer");
	}
}
}
