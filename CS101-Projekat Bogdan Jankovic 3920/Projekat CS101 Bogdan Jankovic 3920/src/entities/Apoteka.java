package entities;

import pojo.StavkaPorudzbine;
import pojo.StavkaApoteke;
import pojo.Porudzbina;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Apoteka implements Buyable {

    private String naziv;
    private List<Klijent> klijenti = new ArrayList<>();
    private List<Porudzbina> porudzbine = new ArrayList<>();
    private List<StavkaApoteke> stavkeApoteke = new ArrayList<>();

    public Apoteka() {
    }

    public Apoteka(String naziv) {
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(List<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }

    public List<StavkaApoteke> getStavkeApoteke() {
        return stavkeApoteke;
    }

    public List<Klijent> getKlijenti() {
        return klijenti;
    }

    public void setStavkeApoteke(List<StavkaApoteke> stavkeApoteke) {
        this.stavkeApoteke = stavkeApoteke;
    }

    public void setKlijenti(List<Klijent> klijenti) {
        this.klijenti = klijenti;
    }

    public void prikaziSveLekove() {
        Collections.sort(stavkeApoteke);
        for (int i = 0; i < stavkeApoteke.size(); i++) {
            System.out.println(stavkeApoteke.get(i).getLek());
        }
    }
    public void prikaziSveStavkeApoteke(){
        Collections.sort(stavkeApoteke);
        for (int i = 0; i < stavkeApoteke.size(); i++) {
            System.out.println(stavkeApoteke.get(i).toString());          
       }
    }

    public void pregledajSvePorudzbine() {
        for (int i = 0; i < porudzbine.size(); i++) {
            porudzbine.get(i).pregledPorudzbine();
        }
    }
    public void pregledajSveKlijente(){
        for (int i = 0; i < klijenti.size(); i++) {
            System.out.println(klijenti.get(i));
        }
    }

    public void dodajKlijenta(Klijent k) {
        this.klijenti.add(k);
    }

    public double stanjeUKasi() {
        double kasa = 0;
        for (int i = 0; i < porudzbine.size(); i++) {
            kasa += porudzbine.get(i).racunajUkupnuCenu();
        }
        return kasa;
    }

    public void smanjiStavke(StavkaPorudzbine stavkaPorudzbine) {
        for (StavkaApoteke stavka : stavkeApoteke) {
            if (stavka.getLek().equals(stavkaPorudzbine.getLek())) {
                stavka.setKolicna(stavka.getKolicna() - stavkaPorudzbine.getKolicina());
            }
        }
    }
    
    @Override
    public String toString() {
        return "Apoteka-" + "naziv = " + naziv;
    }

    @Override
    public boolean imaNaStanju(Lek l, int kolicina) {
        for (StavkaApoteke stavka : stavkeApoteke) {
            if (stavka.getLek().equals(l) && stavka.getKolicna() >= kolicina) {
                return true;
            }
        }
        return false;
    }
}
