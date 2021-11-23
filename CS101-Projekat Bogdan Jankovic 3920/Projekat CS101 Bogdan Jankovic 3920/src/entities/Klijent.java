package entities;

import exceptions.BrojKnjiziceNijeValidanException;
import java.io.Serializable;

public class Klijent extends Osoba implements Serializable{

    private String brojZdravsteneKnjizice;

    public Klijent() {
    }

    public Klijent(String ime, String prezime, String brojZdravsteneKnjizice) throws BrojKnjiziceNijeValidanException {
        super(ime, prezime);
        this.setBrojZdravsteneKnjizice(brojZdravsteneKnjizice);
    }

    public String getBrojZdravsteneKnjizice() {
        return brojZdravsteneKnjizice;
    }

    public void setBrojZdravsteneKnjizice(String brojZdravsteneKnjizice) throws BrojKnjiziceNijeValidanException {
        if (brojZdravsteneKnjizice.length() < 9 || brojZdravsteneKnjizice.length() > 12) {
            throw new BrojKnjiziceNijeValidanException();
        }
        this.brojZdravsteneKnjizice = brojZdravsteneKnjizice;
    }

    @Override
    public String toString() {
        return super.toString() + " " + "Zdravstvena Knjižica broj- " + brojZdravsteneKnjizice;
    }
}
