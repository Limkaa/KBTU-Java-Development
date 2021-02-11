package consoleMenu;

import systemLogic.Database;
import systemLogic.LogFile;
import systemLogic.LogType;
import systemLogic.User;

public class Facade {
	
	// Сhecking validity of password
	public static String checkAndChangePassword(User user, String password, String repeatedPassword) {
		if (password.equals(repeatedPassword)) 
			if (!password.equals(user.getPassword()))
				if (password.length() > 4) 
					if (!password.contains(" ")) 
						if (password.contains(".") || password.contains(",") || password.contains("&") || 
								password.contains("%") || password.contains("*") || password.contains("_")) {
							
							user.setPassword(password);
							Database.logFiles.add(new LogFile(LogType.PASSWORD_CHANGED));
							return "[Password successfully changed]";
						}
						else 
							return "[New password must have on or more special symbol like: '. , & % * _']";
					else 
						return "[New password can't contain space(' ') symbol]";
				else 
					return "[New password is too short. Password must have more than 4 symbols]";
			else 
				return "[This password is used now. Choose another]";
		
		return "[Passwords does not matches each other]";
	}

}
