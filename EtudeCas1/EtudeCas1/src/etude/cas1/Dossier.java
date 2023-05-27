package etude.cas1;

import java.util.ArrayList;

public class Dossier extends Element {

	private ArrayList<Element> ListElements = new ArrayList<Element>();

	public Dossier(String nom){
		super(nom);
	}

	public int getTaille(){
		int taille = 0;
		
		for (Element element : ListElements) {
			taille += element.getTaille();
		}
		
		return taille;
	}

	public ArrayList<Element> getListElements() {
		return ListElements;
	}

	public void setListElements(ArrayList<Element> ListElements) {
		this.ListElements = ListElements;
	}

	@Override
	public String toString() {
		String affichage = "/" + this.getNom() + "[" + this.getTaille() + "]";
		for (Element element : ListElements) {
			affichage += "\n" + "   " + element.toString();
		}
		return affichage;
	}
	
	public boolean removeElement(Element element) {
		ArrayList<Element> ListElements = this.getListElements();
		if (ListElements.remove(element)) {
			this.setListElements(ListElements);
			return true;
		}
		return false;
	}
	
}
