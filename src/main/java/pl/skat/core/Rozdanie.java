package pl.skat.core;

import java.util.ArrayList;

public class Rozdanie {
    public Rozdanie(Gracz gracz, Gracz przeciwnik1, Gracz przeciwnik2){
        this.gracz = gracz;
        this.przeciwnik1 = przeciwnik1;
        this.przeciwnik2 = przeciwnik2;
        // rozdajKarty(gracz)
    }

    public void ustawRodzajGry(RodzajGry rodzaj){
        this.rodzaj = rodzaj;
    }

    private void rozdajKarty(){
    }

    public void ustawWartoscLicytacji(int wartosc){
        wartoscLicytacji = wartosc;
    }

    public void ustawSkat(Skat skat){
        this.skat = skat;
    }

    public int obliczIloscZebranychKart(){
        ArrayList zebraneKarty = gracz.getZebraneKarty();

        if (zebraneKarty == null){
            return 0;
        }

        return zebraneKarty.size();
    }

    public int obliczIloscOczekWZebranychKartach(){
        ArrayList zebraneKarty = gracz.getZebraneKarty();
        int suma = 0;

        if (zebraneKarty == null){
            return suma;
        }

        for (Object obiekt : zebraneKarty){
            Karta karta = (Karta) obiekt;
            suma += karta.figura().getKod();
        }

        return suma;
    }

    public WynikGry obliczWynik(){
        rezultat = new WynikGry();
        rezultat.wynik = obliczIloscOczekWZebranychKartach();
        rezultat.wygrana = rezultat.wynik >= 61;
        return rezultat;
    }

    private int wartoscLicytacji;
    private Gracz przeciwnik1;
    private Gracz przeciwnik2;
    private Gracz gracz;
    private Skat skat;
    private RodzajGry rodzaj;
    private WynikGry rezultat;
}
