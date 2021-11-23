package entities;

import java.io.Serializable;

public class Lek  implements Serializable{

    private String naziv;
    private String proizvodjac;
    private double jacina;
    private TipLeka tipLeka;
    private double cena;
    private boolean recept;

    public Lek() {
    }

    public Lek(String naziv, String proizvodjac, double jacina, TipLeka tipLeka, double cena, boolean recept) {
        this.naziv = naziv;
        this.proizvodjac = proizvodjac;
        this.jacina = jacina;
        this.tipLeka = tipLeka;
        this.cena = cena;
        this.recept = recept;
    }
    public Lek(Lek temp){
        this.naziv = temp.naziv;
        this.proizvodjac = temp.proizvodjac;
        this.jacina = temp.jacina;
        this.tipLeka = temp.tipLeka;
        this.cena = temp.cena;
        this.recept = temp.recept;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public double getJacina() {
        return jacina;
    }

    public void setJacina(double jacina) {
        this.jacina = jacina;
    }

    public TipLeka getTipLeka() {
        return tipLeka;
    }

    public void setTipLeka(TipLeka tipLeka) {
        this.tipLeka = tipLeka;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public boolean isRecept() {
        return recept;
    }

    public void setRecept(boolean recept) {
        this.recept = recept;
    }

    @Override
    public String toString() {
        return naziv + " " + proizvodjac + " " + jacina + " " + tipLeka + " " + cena + " " + recept;
    }

}
