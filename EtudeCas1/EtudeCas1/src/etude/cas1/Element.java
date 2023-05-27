package etude.cas1;

public abstract class Element {
	
	private String nom;
	
	public Element(String nom) {
		this.nom = nom;
	}

	public abstract int getTaille();
	
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
