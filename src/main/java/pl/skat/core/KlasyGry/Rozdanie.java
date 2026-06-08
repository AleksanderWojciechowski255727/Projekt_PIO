package pl.skat.core.KlasyGry;

public class Rozdanie
{

    public Rozdanie(Gracz gracz, Gracz przeciwnik1, Gracz przeciwnik2){
        this.gracz = gracz;
        this.przeciwnik1 = przeciwnik1;
        this.przeciwnik2 = przeciwnik2;


//        rozdajKarty(gracz)

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







    private int wartoscLicytacji;

    private Gracz przeciwnik1;
    private Gracz przeciwnik2;

    private Gracz gracz;

    private Skat skat;

    private RodzajGry rodzaj;
    private WynikGry rezultat;


}
