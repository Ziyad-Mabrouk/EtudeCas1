package etude.cas1;

import java.util.ArrayList;

public class Controller {

    private ArrayList<User> listUsers = new ArrayList<>();
    private User currentUser;
    private Dossier currentDirectory;

    // Singleton
    private static Controller controller_instance = null;

    private Controller(ArrayList<User> listUsers) {
    	this.listUsers = listUsers;
    }

    public static synchronized Controller getInstance(ArrayList<User> listUsers) {
        if (controller_instance == null)
            controller_instance = new Controller(listUsers);

        return controller_instance;
    }

    public String authentifier(String username, String password) {
        for (User user : listUsers) {
            if (user.authentifier(username, password)) {
                currentUser = user;
                currentDirectory = user.getHome();
                if (user.getUserId().equals("0")) {
                    return "admin";
                } else {
                    return user.getUserId();
                }
            }
        }
        return "null";
    }

    public String currentDirectory() {
        return currentDirectory.toString();
    }

    public Element searchElement(String name) {
        return searchElementRecursive(currentDirectory, name);
    }

    private Element searchElementRecursive(Dossier directory, String name) {
        for (Element element : directory.getListElements()) {
            if (element.getNom().equals(name)) {
                return element;
            } else if (element instanceof Dossier) {
                Element foundElement = searchElementRecursive((Dossier) element, name);
                if (foundElement != null) {
                    return foundElement;
                }
            }
        }
        return null;
    }

    public boolean modifyDossier(String name, String new_name) {
        Element element = searchElement(name);
        if (element instanceof Dossier) {
            Dossier dossier = (Dossier) element;
            dossier.setNom(new_name);
            return true;
        }
        return false;
    }

    public boolean modifyFichier(String name, String new_name, int new_size) {
        Element element = searchElement(name);
        if (element instanceof Fichier) {
            Fichier fichier = (Fichier) element;
            fichier.setNom(new_name);
            fichier.setTaille(new_size);
            return true;
        }
        return false;
    }

    public void addDossier(Dossier directory) {
        currentDirectory.getListElements().add(directory);
    }

    public void addFichier(Fichier file) {
        currentDirectory.getListElements().add(file);
    }

    public User addUser(String username, String password) {
    	currentUser = Admin.getInstance();
        User user = ((Admin) currentUser).addUser(username, password);
        listUsers.add(user);
        return user;
    }

    public String afficherUsers() {
        String string = "";
        for (User user : listUsers) {
            string += "User ID: " + user.getUserId() + ", Username: " + user.getUsername() + ", Password: " + user.getPassword() + "\n";
        }
        return string;
    }

    public boolean removeUser(String userId) {
        for (User user : listUsers) {
            if (user.getUserId().equals(userId)) {
                listUsers.remove(user);
                return true;
            }
        }
        return false;
    }

    public boolean changeCurrentDirectory(String name) {
        Element element = searchElement(name);
        if (element instanceof Dossier) {
            currentDirectory = (Dossier) element;
            return true;
        }
        return false;
    }

    public boolean deleteElement(String name) {
    	Element element = searchElement(name);
        
        if (element != null) {
            if (element instanceof Fichier) {
                return currentDirectory.removeElement(element);
            } else if (element instanceof Dossier) {
                if (currentDirectory.getNom().equals(name)) {
                    return false;
                } else {
                    return currentDirectory.removeElement(element);
                }
            }
        }
        
        return false;
    }
}
