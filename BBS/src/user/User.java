package user;

public class User {
	//최대한 만들었던 db와 동일하게 이름을 만든다.
	//데이터베이스와 java bing?? 완성 - jsp에서 데이터를 다룰 수 있도록 만드는 기법
	//하나의 회원정보를 담을 수 있는 user classGamestart.jsp?select=eq1`
	//java binz 
	//jsp - 회원 user db 접근 -> DAO(Data Access Object)
	private String userID;
	private String userPassword;
	private String userName;
	private String userGender;
	private String userEmail; 	
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
	
}
