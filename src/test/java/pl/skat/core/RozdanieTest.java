package pl.skat.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RozdanieTest {
    private Gracz gracz;
    private Rozdanie rozdanie;

    @BeforeEach
    public void setUp(){
        gracz = new Gracz();
        rozdanie = new Rozdanie(gracz, new Gracz(), new Gracz());
    }

    @Test
    public void graczWygrywaGdyZdobyl61Oczek(){
        gracz.ustawZebraneKarty(karty(
                new Karta(Kolor.TREFL, Figura.AS),
                new Karta(Kolor.PIK, Figura.AS),
                new Karta(Kolor.SERCE, Figura.AS),
                new Karta(Kolor.DZWONEK, Figura.AS),
                new Karta(Kolor.TREFL, Figura.DZIESIATKA),
                new Karta(Kolor.PIK, Figura.KROL),
                new Karta(Kolor.SERCE, Figura.KROLOWA)
        ));

        WynikGry wynik = rozdanie.obliczWynik();

        assertEquals(true, wynik.wygrana && wynik.wynik == 61);
    }

    @Test
    public void graczWygrywaGreNullGdyNieZebralZadnejKarty(){
        rozdanie.ustawRodzajGry(graNull());
        gracz.ustawZebraneKarty(karty());

        WynikGry wynik = rozdanie.obliczWynik();

        assertEquals(true, wynik.wygrana);
    }

    @Test
    public void graczPrzegrywaGreNullGdyZebralJednaKarte(){
        rozdanie.ustawRodzajGry(graNull());
        gracz.ustawZebraneKarty(karty(
                new Karta(Kolor.TREFL, Figura.SIODEMKA)
        ));

        WynikGry wynik = rozdanie.obliczWynik();

        assertEquals(false, wynik.wygrana);
    }

    private RodzajGry graNull(){
        RodzajGry rodzajGry = new RodzajGry();
        rodzajGry.typ = TypGry.NULL;
        return rodzajGry;
    }

    private ArrayList<Karta> karty(Karta... karty){
        ArrayList<Karta> zebraneKarty = new ArrayList<>();

        for (Karta karta : karty){
            zebraneKarty.add(karta);
        }

        return zebraneKarty;
    }
}
