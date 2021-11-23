package util;

import pojo.StavkaApoteke;
import pojo.Porudzbina;
import entities.*;
import exceptions.BrojKnjiziceNijeValidanException;
import java.io.*;
import java.util.*;


public class FileUtil {

    private static BufferedReader input;
    private static BufferedWriter output;
    private static Scanner sc;

    public static List<StavkaApoteke> procitajStavkeApoteke() {
        List<StavkaApoteke> stavkeApoteke = new ArrayList<>();
        try {
            input = new BufferedReader(new FileReader("StavkeApoteke.txt"));
            String line = "";
            while ((line = input.readLine()) != null) {
                Lek lek = new Lek();
                lek.setNaziv(line);
                lek.setProizvodjac(input.readLine());
                lek.setJacina(Double.parseDouble(input.readLine()));
                lek.setTipLeka(TipLeka.valueOf(input.readLine()));
                lek.setCena(Double.parseDouble(input.readLine()));
                lek.setRecept(input.readLine().equals("true") ? true : false);

                StavkaApoteke stavka = new StavkaApoteke();
                stavka.setLek(lek);
                stavka.setKolicna(Integer.parseInt(input.readLine()));
                stavkeApoteke.add(stavka);
                input.readLine();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return stavkeApoteke;
    }

    public static List<Klijent> procitajKlijente() {
        List<Klijent> klijenti = new ArrayList<>();
        try {
            input = new BufferedReader(new FileReader("Klijenti.txt"));
            String line = "";
            while ((line = input.readLine()) != null) {
                Klijent klijent = new Klijent();
                klijent.setIme(line);
                klijent.setPrezime(input.readLine());
                klijent.setBrojZdravsteneKnjizice(input.readLine());
                input.readLine();
                klijenti.add(klijent);

            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (BrojKnjiziceNijeValidanException ex) {
            System.out.println("Broj knjizice nije validan");
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return klijenti;
    }

    public static boolean validanPristupniPin(String pristupniPin) {
        try {
            sc = new Scanner(new FileInputStream("PristupniPin.txt"));
            while (sc.hasNext()) {
                String pin = sc.nextLine();
                if (pristupniPin.equals(new StringBuilder(pin).reverse().toString())) {
                    return true;
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void stampajPoruzbine(List<Porudzbina> porudzbine) {
        FileOutputStream file = null;
        try {
            file = new FileOutputStream("Porudzbine.bin");
            ObjectOutputStream out = new ObjectOutputStream(file);
            for (int i = 0; i < porudzbine.size(); i++) {
                out.writeObject(porudzbine.get(i));
                out.writeDouble(porudzbine.get(i).racunajUkupnuCenu());
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void upisiKlijente(List<Klijent> klijenti) {

        try {
            output = new BufferedWriter(new FileWriter("Klijenti.txt"));
            for (Klijent k : klijenti) {
                output.write(k.getIme());
                output.newLine();
                output.write(k.getPrezime());
                output.newLine();
                output.write(k.getBrojZdravsteneKnjizice());
                output.newLine();
                output.write("***************");
                output.newLine();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                output.flush();
                output.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void upisiStavkeApoteke(List<StavkaApoteke> stavkeApoteke) {;
        try {
            output = new BufferedWriter(new FileWriter("StavkeApoteke.txt"));
            for (StavkaApoteke s : stavkeApoteke) {
                output.write(s.getLek().getNaziv());
                output.newLine();
                output.write(s.getLek().getProizvodjac());
                output.newLine();
                output.write( s.getLek().getJacina() + "");
                output.newLine();
                output.write(s.getLek().getTipLeka().name());
                output.newLine();
                output.write(s.getLek().getCena() + "");
                output.newLine();
                output.write((s.getLek().isRecept()) ? "true" : "false");
                output.newLine();
                output.write(s.getKolicna() + "");
                output.newLine();
                output.write("***********************");
                output.newLine();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                output.flush();
                output.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
