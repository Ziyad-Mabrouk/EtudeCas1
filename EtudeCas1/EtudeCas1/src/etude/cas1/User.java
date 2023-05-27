package etude.cas1;

import java.util.ArrayList;

public class User {
	
	private String userId;
	private String username;
	private String password;
	
	private Dossier home;
	
	public User(String userId, String username, String password){
		this.userId = userId;
		this.username = username;
		this.password = password;
		Dossier home = new Dossier(userId);
		this.home = home;
	}

	public boolean authentifier(String username, String password){
		return username.equals(this.username) && password.equals(this.password);
	}

	public boolean addFichier(String nom, int taille) {
		if (searchElement(nom) == null) {
			ArrayList<Element> listElements = home.getListElements();
			Fichier file = new Fichier(nom, taille);
			listElements.add(file);
			home.setListElements(listElements);
			return true;
		}
		
		return false;
		
	}
	
	public boolean addDossier(String nom) {
		if (searchElement(nom) == null) {
			ArrayList<Element> listElements = home.getListElements();
			Dossier dossier = new Dossier(nom);
			listElements.add(dossier);
			home.setListElements(listElements);
			return true;
		}
		
		return false;
	}
	
	public Element searchElement(String nom) {
		ArrayList<Element> listElements = home.getListElements();
		for (Element element : listElements) {
			if (element.getNom().equals(nom)) {
				return element;
			}
		}
		return null;
	}
	
	public boolean removeElement(String nom) {
		Element element = this.searchElement(nom);
		
		if (element != null) {
			ArrayList<Element> listElements = home.getListElements();
			listElements.remove(element);
			home.setListElements(listElements);
			return true;
		}
		
		return false;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Dossier getHome() {
		return home;
	}

	public void setHome(Dossier home) {
		this.home = home;
	}

	public String getPassword() {
		return this.password;
	}

}
