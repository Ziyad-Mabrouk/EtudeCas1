package etude.cas1;

import java.util.ArrayList;

public class Client {

	private static IHM mIHM;

	public Client(){

	}

	public static void main(String[] args){
		ArrayList<User> listUsers = new ArrayList<>();
		listUsers.add(Admin.getInstance());
		Controller controller = Controller.getInstance(listUsers);
		mIHM = new IHM(controller);
		mIHM.authentifier();
	}

}