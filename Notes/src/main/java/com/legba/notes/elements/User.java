/**
 * 
 */
package com.legba.notes.elements;

import java.io.IOException;
import java.util.Objects;

/**
 * Class represents the user object
 * @author jjds502
 *
 */
public class User {

	public enum USER_TYPE {
		STUDENT, LECTURER, ADMIN
	}
	
	private String forename;
	private String surname;
	private String username;
	private String password;
	private USER_TYPE userType;
	private int userID;
	
	public User(String username, String password, USER_TYPE type){
		this.setUsername(username);
		this.setPassword(password);
		this.setUserType(type);
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public USER_TYPE getUserType() {
		return userType;
	}
	public void setUserType(USER_TYPE userType) {
		this.userType = userType;
	}

	public int getUserID() {
		return userID;
	}

	private void setUserID(int userID) {
		this.userID = userID;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public boolean checkPassword(String password) throws IOException {
		/**
		 * Will compare the provided password to the User's existing password
		 * @param The Password to compare as String
		 * @return Boolean result of comparison
		 */
		//return this.password.equals(password);
		return Objects.equals(this.password, password);
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Name: " + this.forename + " " + this.surname + System.lineSeparator() +
		"Username: " + this.username + System.lineSeparator() + 
		"User Type: " + this.userType.toString() + System.lineSeparator() +
		"UserID: " + this.userID; 
		
	}
	
}
