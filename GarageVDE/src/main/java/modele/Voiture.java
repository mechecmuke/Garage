package modele;


public class Voiture {
    private String marque;
    private String modele;
    private String immatriculation;
    private String etat;

    public Voiture(String marque, String modele, String immatriculation, String etat) {
        this.marque = marque;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.etat = etat;
    }

    public String getMarque() { return marque; }
    public String getModele() { return modele; }
    public String getImmatriculation() { return immatriculation; }
    public String getEtat() { return etat; }
}
