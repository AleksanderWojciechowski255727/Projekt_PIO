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
    public void graGrandMaBazowaWartosc24(){
        rozdanie.ustawRodzajGry(graGrand());

        assertEquals(24, rozdanie.obliczWartoscBazowaGry());
    }


    @Test
    public void graKolorowaTreflMaBazowaWartosc12(){
        rozdanie.ustawRodzajGry(graKolorowa());

        assertEquals(12, rozdanie.obliczWartoscBazowaGry());
    }


    @Test
    public void graKolorowaPikMaBazowaWartosc11(){
        rozdanie.ustawRodzajGry(graKolorowa(Kolor.PIK));

        assertEquals(11, rozdanie.obliczWartoscBazowaGry());
    }

    @Test
    public void graKolorowaSerceMaBazowaWartosc10(){
        rozdanie.ustawRodzajGry(graKolorowa(Kolor.SERCE));

        assertEquals(10, rozdanie.obliczWartoscBazowaGry());
    }

    @Test
    public void graKolorowaDzwonekMaBazowaWartosc9(){
        rozdanie.ustawRodzajGry(graKolorowa(Kolor.DZWONEK));

        assertEquals(9, rozdanie.obliczWartoscBazowaGry());
    }


    @Test
    public void graczPrzegrywaGdyWartoscGryJestMniejszaOdLicytacji(){
        rozdanie.ustawRodzajGry(graGrand());
        rozdanie.ustawWartoscLicytacji(50);
        gracz.ustawZebraneKarty(kartyZa61Oczek());

        WynikGry wynik = rozdanie.obliczWynik();

        assertEquals(false, wynik.wygrana);
    }


    @Test
    public void graczWygrywaGdyWartoscGryJestRownaLicytacji(){
        rozdanie.ustawRodzajGry(graGrand());
        rozdanie.ustawWartoscLicytacji(48);
        gracz.ustawZebraneKarty(kartyZa61Oczek());

        WynikGry wynik = rozdanie.obliczWynik();

        assertEquals(true, wynik.wygrana);
    }


    @Test
    public void graczWygrywaGdyWartoscGryJestWiekszaOdLicytacji(){
        rozdanie.ustawRodzajGry(graGrand());
        rozdanie.ustawWartoscLicytacji(18);
        gracz.ustawZebraneKarty(kartyZa61Oczek());

        WynikGry wynik = rozdanie.obliczWynik();

        assertEquals(true, wynik.wygrana);
    }

    @Test
    public void graNullMaWartosc23(){
        rozdanie.ustawRodzajGry(graNull());

        assertEquals(23, rozdanie.obliczWartoscGry());
    }

    @Test
    public void graNullHandMaWartosc35(){
        rozdanie.ustawRodzajGry(graNullHand());

        assertEquals(35, rozdanie.obliczWartoscGry());
    }

    @Test
    public void graNullOuvertMaWartosc46(){
        rozdanie.ustawRodzajGry(graNullOuvert());

        assertEquals(46, rozdanie.obliczWartoscGry());
    }

    @Test
    public void graNullOuvertHandMaWartosc59(){
        rozdanie.ustawRodzajGry(graNullOuvertHand());

        assertEquals(59, rozdanie.obliczWartoscGry());
    }


    @Test
    public void graczPrzegrywaGreNullGdyWartoscGryJestMniejszaOdLicytacji(){
        rozdanie.ustawRodzajGry(graNull());
        rozdanie.ustawWartoscLicytacji(24);
        gracz.ustawZebraneKarty(karty());

        WynikGry wynik = rozdanie.obliczWynik();

        assertEquals(false, wynik.wygrana);
    }

    @Test
    public void graczWygrywaGreNullGdyWartoscGryJestRownaLicytacji(){
        rozdanie.ustawRodzajGry(graNull());
        rozdanie.ustawWartoscLicytacji(23);
        gracz.ustawZebraneKarty(karty());

        WynikGry wynik = rozdanie.obliczWynik();

        assertEquals(true, wynik.wygrana);
    }

    @Test
    public void graczWygrywaGreNullGdyWartoscGryJestWiekszaOdLicytacji(){
        rozdanie.ustawRodzajGry(graNull());
        rozdanie.ustawWartoscLicytacji(18);
        gracz.ustawZebraneKarty(karty());

        WynikGry wynik = rozdanie.obliczWynik();

        assertEquals(true, wynik.wygrana);
    }


    @Test
    // zebrał 30 oczek lub mniej
    public void rozgrywajacyZostalKrawcemZ30Oczkami() {
        rozdanie.ustawRodzajGry(graKolorowa());
        gracz.ustawZebraneKarty(kartyZa30Oczek());

        WynikGry wynik = rozdanie.obliczWynik();
		assertEquals(true,rozdanie.rodzaj.schneider);
        assertEquals(false, wynik.wygrana);
    }

    @Test
    public void rozgrywajacyZostalKrawcemZ29Oczkami() {

        gracz.ustawZebraneKarty(kartyZa29Oczek());

        WynikGry wynik = rozdanie.obliczWynik();
		assertEquals(true,rozdanie.rodzaj.schneider);
        assertEquals(false, wynik.wygrana);
    }

    @Test
    public void rozgrywajacyNieZostalKrawcemZ31Oczkami() {
        gracz.ustawZebraneKarty(kartyZa31Oczek());

        WynikGry wynik = rozdanie.obliczWynik();
//        if (rozdanie.rodzaj!=null){
            assertEquals(false,rozdanie.rodzaj.schneider);
//        }
        assertEquals(false, wynik.wygrana);
    }

    private RodzajGry graNull(){
        RodzajGry rodzajGry = new RodzajGry();
        rodzajGry.typ = TypGry.NULL;
        return rodzajGry;
    }

    private RodzajGry graNullHand(){
        RodzajGry rodzajGry = graNull();
        rodzajGry.hand = true;
        return rodzajGry;
    }

    private RodzajGry graNullOuvert(){
        RodzajGry rodzajGry = graNull();
        rodzajGry.ouvert = true;
        return rodzajGry;
    }

    private RodzajGry graNullOuvertHand(){
        RodzajGry rodzajGry = graNull();
        rodzajGry.hand = true;
        rodzajGry.ouvert = true;
        return rodzajGry;
    }

    private RodzajGry graKolorowa(){
        return graKolorowa(Kolor.TREFL);
    }

    private RodzajGry graKolorowa(Kolor kolor){
        RodzajGry rodzajGry = new RodzajGry();
        rodzajGry.typ = TypGry.KOLOROWA;
        rodzajGry.kolor = kolor;
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

    private ArrayList<Karta> kartyZa30Oczek(){
        return karty(
                new Karta(Kolor.TREFL, Figura.DZIESIATKA),
                new Karta(Kolor.DZWONEK, Figura.DZIESIATKA),
                new Karta(Kolor.PIK, Figura.DZIESIATKA)
        );
    }

    private ArrayList<Karta> kartyZa29Oczek(){
        return karty(
                new Karta(Kolor.TREFL, Figura.DZIESIATKA),
                new Karta(Kolor.DZWONEK, Figura.DZIESIATKA),
                new Karta(Kolor.PIK, Figura.KROL),
                new Karta(Kolor.PIK, Figura.KROLOWA),
                new Karta(Kolor.PIK, Figura.SIODEMKA),
                new Karta(Kolor.PIK, Figura.OSEMKA)
        );
    }

    private ArrayList<Karta> kartyZa31Oczek(){
        return karty(
                new Karta(Kolor.TREFL, Figura.DZIESIATKA),
                new Karta(Kolor.DZWONEK, Figura.DZIESIATKA),
                new Karta(Kolor.PIK, Figura.AS)
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
