package etude.cas1;

public class Fichier extends Element {

	private int taille;

	public Fichier(String nom, int taille){
		super(nom);
		this.taille = taille;
	}

	public int getTaille(){
		return this.taille;
	}

	@Override
	public String toString() {
		return this.getNom() + "[" + this.getTaille() + "]";
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}
	
}
