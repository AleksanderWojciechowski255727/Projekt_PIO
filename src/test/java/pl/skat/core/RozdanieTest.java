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
        gracz.ustawZebraneKarty(kartyZa61Oczek());

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

    @Test
    public void graczWygrywaGreKolorowaGdyZdobyl61Oczek(){
        rozdanie.ustawRodzajGry(graKolorowa());
        gracz.ustawZebraneKarty(kartyZa61Oczek());

        WynikGry wynik = rozdanie.obliczWynik();

        assertEquals(true, wynik.wygrana && wynik.wynik == 61);
    }

    @Test
    public void graczPrzegrywaGreKolorowaGdyZdobyl60Oczek(){
        rozdanie.ustawRodzajGry(graKolorowa());
        gracz.ustawZebraneKarty(kartyZa60Oczek());

        WynikGry wynik = rozdanie.obliczWynik();

        assertEquals(false, wynik.wygrana);
    }


    @Test
    public void graczWygrywaGreGrandGdyZdobyl61Oczek(){
        rozdanie.ustawRodzajGry(graGrand());
        gracz.ustawZebraneKarty(kartyZa61Oczek());

        WynikGry wynik = rozdanie.obliczWynik();

        assertEquals(true, wynik.wygrana && wynik.wynik == 61);
    }

    @Test
    public void graczPrzegrywaGreGrandGdyZdobyl60Oczek(){
        rozdanie.ustawRodzajGry(graGrand());
        gracz.ustawZebraneKarty(kartyZa60Oczek());

        WynikGry wynik = rozdanie.obliczWynik();

        assertEquals(false, wynik.wygrana);
    }


    @Test
    public void graGrandMaWartosc24(){
        rozdanie.ustawRodzajGry(graGrand());

        assertEquals(24, rozdanie.obliczWartoscGry());
    }

    private RodzajGry graNull(){
        RodzajGry rodzajGry = new RodzajGry();
        rodzajGry.typ = TypGry.NULL;
        return rodzajGry;
    }

    private RodzajGry graKolorowa(){
        RodzajGry rodzajGry = new RodzajGry();
        rodzajGry.typ = TypGry.KOLOROWA;
        rodzajGry.kolor = Kolor.TREFL;
        return rodzajGry;
    }


    private RodzajGry graGrand(){
        RodzajGry rodzajGry = new RodzajGry();
        rodzajGry.typ = TypGry.GRAND;
        return rodzajGry;
    }

    private ArrayList<Karta> kartyZa61Oczek(){
        return karty(
                new Karta(Kolor.TREFL, Figura.AS),
                new Karta(Kolor.PIK, Figura.AS),
                new Karta(Kolor.SERCE, Figura.AS),
                new Karta(Kolor.DZWONEK, Figura.AS),
                new Karta(Kolor.TREFL, Figura.DZIESIATKA),
                new Karta(Kolor.PIK, Figura.KROL),
                new Karta(Kolor.SERCE, Figura.KROLOWA)
        );
    }

    private ArrayList<Karta> kartyZa60Oczek(){
        return karty(
                new Karta(Kolor.TREFL, Figura.AS),
                new Karta(Kolor.PIK, Figura.AS),
                new Karta(Kolor.SERCE, Figura.AS),
                new Karta(Kolor.DZWONEK, Figura.AS),
                new Karta(Kolor.TREFL, Figura.DZIESIATKA),
                new Karta(Kolor.PIK, Figura.KROL),
                new Karta(Kolor.SERCE, Figura.JOPEK)
        );
    }

    private ArrayList<Karta> karty(Karta... karty){
        ArrayList<Karta> zebraneKarty = new ArrayList<>();

        for (Karta karta : karty){
            zebraneKarty.add(karta);
        }

        return zebraneKarty;
    }
}
