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

        if (jestGraNull()){
            rezultat.wygrana = obliczIloscZebranychKart() == 0 && obliczWartoscGry() >= wartoscLicytacji;
            return rezultat;
        }

		if (jestGraPunktowa()) {
			if (rodzaj == null) rodzaj = new RodzajGry();
			if (rezultat.wynik <= 30 || rezultat.wynik >= 90) {
				rodzaj.schneider = true;
			}
            if (obliczIloscZebranychKart() == 30 || obliczIloscZebranychKart() == 0) {
                rodzaj.schwarz = true;
            }

			rezultat.wygrana = rezultat.wynik >= 61 && obliczWartoscGry() >= wartoscLicytacji;
		}

        return rezultat;
    }


    public int obliczWartoscGry(){
        if (jestGraNull()){
            return obliczWartoscGryNull();
        }
        if (gracz.getPosiadaneKarty() == null){
            return obliczWartoscBazowaGry()*2;
        }

        int mnoznik = policzIloscSzczytow()+1;
        if (rodzaj.schneider){
            mnoznik ++;
        }
        if (rodzaj.schneiderZapowiedziany){
            mnoznik ++;
        }
        if (rodzaj.schwarz){
            mnoznik ++;
        }
        if (rodzaj.hand){
            mnoznik ++;
        }
        return obliczWartoscBazowaGry()* mnoznik;
    }

    public int obliczWartoscBazowaGry(){
        if (rodzaj != null && rodzaj.typ == TypGry.GRAND){
            return 24;
        }

        if (rodzaj != null && rodzaj.typ == TypGry.KOLOROWA){
            return obliczWartoscKoloru();
        }

        return 0;
    }

    private int obliczWartoscGryNull(){
        if (rodzaj.hand && rodzaj.ouvert){
            return 59;
        }

        if (rodzaj.ouvert){
            return 46;
        }

        if (rodzaj.hand){
            return 35;
        }

        return 23;
    }

    private int obliczWartoscKoloru(){
        if (rodzaj.kolor == Kolor.TREFL){
            return 12;
        }

        if (rodzaj.kolor == Kolor.PIK){
            return 11;
        }

        if (rodzaj.kolor == Kolor.SERCE){
            return 10;
        }

        if (rodzaj.kolor == Kolor.DZWONEK){
            return 9;
        }

        return 0;
    }

    public int policzIloscSzczytow(){
        iloscSzczytow=0;
        if(rodzaj.typ==TypGry.NULL){
            return iloscSzczytow;
        }
        ArrayList<Karta> kartyZeSzczytami = kartySzczytow11();

        for (Karta karta : kartyZeSzczytami) {
            if (sprawdzCzyKartaJestWKartachPoczatkowych(karta) == 1 || sprawdzCzyKartaJestWSkacie(karta) == 1) {
                iloscSzczytow++;
            } else {
                break;
            }
        }
        if(iloscSzczytow==0){
            for (Karta karta : kartyZeSzczytami) {
                if (sprawdzCzyKartaJestWKartachPoczatkowych(karta) == 0 && sprawdzCzyKartaJestWSkacie(karta) == 0) {
                    iloscSzczytow++;
                } else {
                    break;
                }
            }
        }
        if(rodzaj.typ==TypGry.GRAND && iloscSzczytow>4){
            iloscSzczytow = 4;
        }
        return iloscSzczytow;
    }

    public int sprawdzCzyKartaJestWKartachPoczatkowych(Karta karta){
        ArrayList<Karta> kartyPoczatkoweGracza = gracz.getPosiadaneKarty();
        for (Karta value : kartyPoczatkoweGracza) {
            if (value.equals(karta)) {
                return 1;
            }
        }
        return 0;
    }

    public int sprawdzCzyKartaJestWSkacie(Karta karta){
        if(skat.getKarta1().equals(karta)||skat.getKarta2().equals(karta)){
            return 1;
        }
        return 0;
    }

    private boolean jestGraNull(){
        return rodzaj != null && rodzaj.typ == TypGry.NULL;
    }

    private boolean jestGraPunktowa(){
        return rodzaj == null || rodzaj.typ == TypGry.KOLOROWA || rodzaj.typ == TypGry.GRAND;
    }

    private ArrayList<Karta> kartySzczytow11(){
        Kolor kolor = Kolor.TREFL;
        if(rodzaj.typ==TypGry.KOLOROWA){
            kolor = rodzaj.kolor;
        }
        ArrayList<Karta> karty = new ArrayList<>();
        karty.add(new Karta(Kolor.TREFL, Figura.JOPEK));
        karty.add(new Karta(Kolor.PIK, Figura.JOPEK));
        karty.add(new Karta(Kolor.SERCE, Figura.JOPEK));
        karty.add(new Karta(Kolor.DZWONEK, Figura.JOPEK));
        karty.add(new Karta(kolor, Figura.AS));
        karty.add(new Karta(kolor, Figura.DZIESIATKA));
        karty.add(new Karta(kolor, Figura.KROL));
        karty.add(new Karta(kolor, Figura.KROLOWA));
        karty.add(new Karta(kolor, Figura.DZIEWIATKA));
        karty.add(new Karta(kolor, Figura.OSEMKA));
        karty.add(new Karta(kolor, Figura.SIODEMKA));
        return karty;
    }

    private int iloscSzczytow;
    private int wartoscLicytacji;
    private Gracz przeciwnik1;
    private Gracz przeciwnik2;
    private Gracz gracz;
    private Skat skat;
    public RodzajGry rodzaj;
    private WynikGry rezultat;
}
