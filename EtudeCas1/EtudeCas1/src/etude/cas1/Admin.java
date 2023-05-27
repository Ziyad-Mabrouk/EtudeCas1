package etude.cas1;

import java.util.UUID;

public class Admin extends User{
	
	private Admin(String userId, String username, String password) {
		super(userId, username, password);
	}

	private static User admin = new Admin("0", "admin", "admin");
	
	public static synchronized Admin getInstance() {
        return (Admin) admin;
	}
	
	public User addUser(String username, String password){
		String userId = UUID.randomUUID().toString();
		User user = new User(userId, username, password);
		return user;
	}

}
