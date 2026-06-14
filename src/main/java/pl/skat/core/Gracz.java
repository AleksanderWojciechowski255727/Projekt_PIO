package pl.skat.core;

import java.util.ArrayList;

public class Gracz {


    public void ustawPosiadaneKarty(Karta [] karty){
        if (karty.length != 10){
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



    private Karta[] posiadaneKarty = null;
    private ArrayList<Karta> zebraneKarty = null;
    public ArrayList<WynikGry> wyniki;

}