package main;

import pojo.StavkaPorudzbine;
import pojo.StavkaApoteke;
import pojo.Porudzbina;
import entities.*;
import exceptions.BrojKnjiziceNijeValidanException;
import java.util.Scanner;
import util.FileUtil;

public class Main {

    private static Scanner input = new Scanner(System.in);
    private static Apoteka apoteka = new Apoteka("Benu");
    private static Porudzbina porudzbina = new Porudzbina();

    public static void main(String[] args) {

        apoteka.setStavkeApoteke(FileUtil.procitajStavkeApoteke());
        apoteka.setKlijenti(FileUtil.procitajKlijente());

        prikaziOpcijePristupa();
        int opcija = input.nextInt();
        switch (opcija) {
            case 1:
                opcijeFarmacuta();
                break;
            case 2:
                opcijeKlijenta();
                break;
            default:
                System.out.println("Izaberite neku od ponudjenih opcija! ");
        }
    }

    public static void opcijeFarmacuta() {
        logovanjeFarmaceuta();
        while (true) {
            System.out.println("_____________________________________________");
            prikaziOpcijeFarmaceuta();
            int opcijaApotekara = input.nextInt();
            switch (opcijaApotekara) {
                case 1:
                    apoteka.prikaziSveStavkeApoteke();
                    break;
                case 2:
                    apoteka.pregledajSveKlijente();
                    break;
                case 3:
                    dodajNovogKlijenta();
                    break;
                case 4:
                    FileUtil.upisiStavkeApoteke(apoteka.getStavkeApoteke());
                    FileUtil.upisiKlijente(apoteka.getKlijenti());
                    FileUtil.stampajPoruzbine(apoteka.getPorudzbine());
                    System.exit(0);
                    break;
                default:
                    System.out.println("Izaberite neku od ponudjenih opcija!");
            }
        }
    }

    public static void opcijeKlijenta() {
        while (true) {
            prikaziOpcijeKlijenta();
            int opcijaKlijenta = input.nextInt();
            switch (opcijaKlijenta) {
                case 1: {
                    kupiLek();
                }
                break;
                default:
                    System.out.println("Izaberite neku od ponudjenih opcija!");
            }
        }
    }

    public static void logovanjeFarmaceuta() {
        System.out.println("Unesite PIN kod kako biste se ulogovali");
        System.out.print("PIN:");
        String pin = input.next();

        while (!FileUtil.validanPristupniPin(pin)) {
            System.out.println("Pogrešan pin");
            System.out.println("Pokusajte ponovo");
            System.out.print("PIN:");
            pin = input.next();
        }
    }

    public static void dodajNovogKlijenta() {
        Klijent k = null;
        System.out.println("Unesite Ime");
        String ime = input.next();
        System.out.println("Unesite Prezime");
        String prezime = input.next();
        System.out.println("Unesite broj zdravstvene knjizice");
        String knjizica = input.next();
        try {
            k = new Klijent(ime, prezime, knjizica);
            apoteka.dodajKlijenta(k);
            System.out.println("Uspešno ste dodali klijenta");
        } catch (BrojKnjiziceNijeValidanException ex) {
            System.out.println("Broj knjizice nije validan Klijent nije kreiran.");
        }
    }

    public static void prikaziOpcijePristupa() {
        System.out.println("Izaberite opciju kako zelite da se ulogujete:");
        System.out.println("1. Farmaceut");
        System.out.println("2. Klijent");
        System.out.print("Izabrana opcija: ");
    }

    public static void prikaziOpcijeFarmaceuta() {
        System.out.println("Izaberite opciju:");
        System.out.println("1. Vidi sve stavke apoteke");
        System.out.println("2. Vidi sve klijente");
        System.out.println("3. Dodaj klijenta");
        System.out.println("4. Izadji iz programa");
        System.out.print("Izabrana opcija: ");
    }

    public static void prikaziOpcijeKlijenta() {
        System.out.println("1.Kupite lek");
        System.out.print("Izabrana opcija: ");
    }

    public static void kupiLek() {
        Porudzbina porudzbina = new Porudzbina();
        while (true) {
            System.out.println("_______________________________________________________________");
            System.out.println("LISTA LEKOVA: ");
            apoteka.prikaziSveLekove();
            System.out.println("Unesite naziv proizvoda koji želite da kupite:");
            String naziv = input.next();
            System.out.println("Unesite količinu leka koji želite da kupite:");
            int kolicna = input.nextInt();

            Lek lek = null;
            for (StavkaApoteke stavka : apoteka.getStavkeApoteke()) {
                if (stavka.getLek().getNaziv().equalsIgnoreCase(naziv)) {
                    lek = stavka.getLek();
                    break;
                }
            }
            if (lek != null && apoteka.imaNaStanju(lek, kolicna)) {
                StavkaPorudzbine stavkaPorudzbine = new StavkaPorudzbine(lek, kolicna);
                porudzbina.getStavke().add(stavkaPorudzbine);
                apoteka.smanjiStavke(stavkaPorudzbine);
                System.out.println("Prodat Vam je lek.");
                stavkaPorudzbine.racunajCenu(lek, stavkaPorudzbine);
            } else {
                System.out.println("Traženi lek nemamo na stanju!");
            }
            input.nextLine();
            System.out.println("Da li želite da nastavite kupovinu? Da/Ne");
            String odgovor = input.nextLine();
            if (odgovor.equalsIgnoreCase("Da")) {
                continue;
            } else if (odgovor.equalsIgnoreCase("Ne")) {
                proveraKlijenta();
                break;
            }
        }
        apoteka.getPorudzbine().add(porudzbina);
        System.out.println("Račun:");
        porudzbina.pregledPorudzbine();
        System.out.println("Ukupna cena je: " + porudzbina.racunajUkupnuCenu());
        System.out.println("Hvala što ste koristili našu aplikaciju!");
        FileUtil.upisiStavkeApoteke(apoteka.getStavkeApoteke());
        FileUtil.upisiKlijente(apoteka.getKlijenti());
        FileUtil.stampajPoruzbine(apoteka.getPorudzbine());
        System.exit(0);
    }

    public static void proveraKlijenta() {
        System.out.println("Unesite zdravstvenu knjižicu kako bismo uporedili da li ste vec nas postojeci klijent:");
        String knjizica = input.next();
        Klijent k = null;
        for (Klijent klijent : apoteka.getKlijenti()) {
            if (klijent.getBrojZdravsteneKnjizice().equals(knjizica)) {
                k = klijent;
                System.out.println("Već ste naš postojeći klijent-Hvala!");
                break;
            }
        }
        if (k == null) {
            System.out.println("Niste u našoj bazi klijenata.");
            System.out.println("Unesite Vase Ime:");
            String ime = input.next();
            System.out.println("Unesite Vase Prezime:");
            String prezime = input.next();
            System.out.println("Unesite broj zdravstvene knjižice:");
            String brojKnjizice = input.next();
            try {
                k = new Klijent(ime, prezime, knjizica);
            } catch (BrojKnjiziceNijeValidanException ex) {
                System.out.println("Broj zdravstvene knjižice nije validan");
            }
            apoteka.getKlijenti().add(k);
            System.out.println("Uspešno ste dodati u našu listu klijenata!");
            FileUtil.upisiKlijente(apoteka.getKlijenti());
        }
    }
}
