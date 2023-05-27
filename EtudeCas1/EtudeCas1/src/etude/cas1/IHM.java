package etude.cas1;

import java.util.Scanner;

public class IHM {

	private Controller controller;
	private Scanner scanner;

	public IHM(Controller controller) {
		this.controller = controller;
		this.scanner = new Scanner(System.in);
	}

	public void menu() {
		System.out.println(controller.currentDirectory());
		System.out.println("Select an option:");
		System.out.println("1. Select Directory");
		System.out.println("2. Create file");
		System.out.println("3. Create directory");
		System.out.println("4. Modify file");
		System.out.println("5. Modify directory");
		System.out.println("6. Delete element");
		System.out.println("7. Search");
		System.out.println("8. Go back");
		System.out.println("0. Exit");

		int choice = getIntInput();

		switch (choice) {
			case 1:
				selectDirectory();
				break;
			case 2:
				addFichier();
				break;
			case 3:
				addDossier();
				break;
			case 4:
				modifyFichier();
				break;
			case 5:
				modifyDossier();
				break;
			case 6:
				deleteElement();
				break;
			case 7:
				searchElement();
				break;
			case 8:
				authentifier();
				break;
			case 0:
				System.out.println("Exiting..." + "\n");
				return;
			default:
				System.out.println("Invalid choice. Try again." + "\n");
		}

		menu();
	}

	private void deleteElement() {
		System.out.print("Enter element name: ");
		String name = scanner.nextLine();

		if (!controller.deleteElement(name)) {
			System.out.print("Element not found: " + name + "\n");
		} else {
			System.out.print("Element deleted: " + name + "\n");
		}

	}

	private void selectDirectory() {
		System.out.print("Enter directory name: ");
		String name = scanner.nextLine();

		if (!controller.changeCurrentDirectory(name)) {
			System.out.print("Directory not found: " + name + "\n");
		}
	}

	private void searchElement() {
		System.out.print("Enter element name to search for: ");
		String name = scanner.nextLine();

		Element element = controller.searchElement(name);

		if (element != null) {
			System.out.println("Element found: " + element.getNom() + "[" + element.getTaille() + "]" + "\n");
			return;
		}

		System.out.println("Element not found: " + name + "\n");
	}

	private void modifyDossier() {
		System.out.print("Enter directory name to modify: ");
		String name = scanner.nextLine();

		System.out.print("Enter new directory name: ");
		String new_name = scanner.nextLine();

		if (controller.modifyDossier(name, new_name)) {
			System.out.println("Directory modified: " + new_name + "\n");
			return;
		}

		System.out.println("Directory not modified: " + name + "\n");
	}

	private void modifyFichier() {
		System.out.print("Enter file name to modify: ");
		String name = scanner.nextLine();

		System.out.print("Enter new name: ");
		String new_name = scanner.nextLine();

		System.out.print("Enter new size (in bytes): ");
		int new_size = getIntInput();

		if (controller.modifyFichier(name, new_name, new_size)) {
			System.out.println("File modified: " + new_name + "\n");
			return;
		}

		System.out.println("File not found: " + name + "\n");
	}

	private void addDossier() {
		System.out.print("Enter directory name: ");
		String name = scanner.nextLine();

		Dossier directory = new Dossier(name);
		controller.addDossier(directory);
		System.out.println("Directory created: " + name + "\n");
	}

	private void addFichier() {
		System.out.print("Enter file name: ");
		String name = scanner.nextLine();
		System.out.print("Enter file size (in bytes): ");
		int size = getIntInput();

		Fichier file = new Fichier(name, size);
		controller.addFichier(file);
		System.out.println("File created: " + name + "\n");
	}

	public void authentifier() {
		System.out.println("Systeme virtuel de gestion de fichiers");
		System.out.print("Username: ");
		String username = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();

		if (controller.authentifier(username, password).equals("null")) {
			System.out.println("Authentication failed." + "\n");
			authentifier();
		} else if (controller.authentifier(username, password).equals("admin")) {
			System.out.println("Authentication successful. Welcome, admin!" + "\n");
			adminMenu();
		} else {
			System.out.println("Authentication successful. Welcome, " + username + "!" + "\n");
			menu();
		}
	}

	private void adminMenu() {
		System.out.println("Select an option:");
		System.out.println("1. Add user");
		System.out.println("2. Remove user");
		System.out.println("3. Display all users");
		System.out.println("4. Access other users");
		System.out.println("0. Exit");

		int choice = getIntInput();

		switch (choice) {
			case 1:
				addUser();
				break;
			case 2:
				removeUser();
				break;
			case 3:
				afficherUsers();
				break;
			case 4:
				authentifier();
				return;
			case 0:
				System.out.println("Exiting..." + "\n");
				return;
			default:
				System.out.println("Invalid choice. Try again." + "\n");
		}

		adminMenu();
	}

	private void afficherUsers() {
		System.out.print(controller.afficherUsers());
	}

	private void removeUser() {
		System.out.print("Enter user's id to be removed: ");
		String userId = scanner.nextLine();
		
		if (userId.equals("0")) {
			System.out.println("You can't remove admin user. \n");
			return;
		}

		if (controller.removeUser(userId)) {
			System.out.println("User removed: id = " + userId + "\n");
		} else {
			System.out.println("Error removing user with id = " + userId + "\n");
		}
	}

	private void addUser() {
		System.out.print("Enter new user's name: ");
		String username = scanner.nextLine();
		System.out.print("Enter new user's password: ");
		String password = scanner.nextLine();

		User user = controller.addUser(username, password);
		if (user != null) {
			System.out.println("User created: id = " + user.getUserId() + "\n");
		} else {
			System.out.println("Error creating user: " + username + "\n");
		}
	}

	private int getIntInput() {
		int input;
		while (true) {
			try {
				input = Integer.parseInt(scanner.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.print("Invalid input. Enter a valid integer: ");
			}
		}
		return input;
	}
}
