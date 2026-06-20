package pl.skat.core;

public class Skat {

    private Karta karta1;
    private Karta karta2;

    public void ustawKarta1(Karta karta){
        karta1=karta;
    }

    public void ustawKarta2(Karta karta){
        karta2=karta;
    }

    public Karta getKarta1(){
        return karta1;
    }

    public Karta getKarta2(){
        return karta2;
    }
}
