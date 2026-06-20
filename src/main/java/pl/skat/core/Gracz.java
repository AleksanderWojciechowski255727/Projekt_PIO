package pl.skat.core;

import java.util.ArrayList;

public class Gracz {


    public void ustawPosiadaneKarty(ArrayList<Karta> karty){
        if (karty.size() != 10){
            throw new RuntimeException("Niepoprawna ilosc posiadanych kart");
        }
        posiadaneKarty = karty;
    }

    public void ustawZebraneKarty(ArrayList<Karta> zebrane){
        if (zebrane.size() > 30){
            throw new RuntimeException("Niepoprawna ilosc zebranych kart");
        }
        zebraneKarty = zebrane;
    }

    public ArrayList<Karta> getZebraneKarty(){
        return zebraneKarty;
    }
    public ArrayList<Karta> getPosiadaneKarty() { return posiadaneKarty; }



    private ArrayList<Karta> posiadaneKarty = null;
    private ArrayList<Karta> zebraneKarty = null;
    public ArrayList<WynikGry> wyniki;

}