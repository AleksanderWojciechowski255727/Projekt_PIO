package pl.skat.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void przeciwnicyZostaliKrawcamiZ30Oczkami() {
        gracz.ustawZebraneKarty(kartyZa90Oczek());

        WynikGry wynik = rozdanie.obliczWynik();
        assertEquals(true,rozdanie.rodzaj.schneider);
    }

    @Test
    public void przeciwnicyZostaliKrawcamiZ93Oczkami() {
        gracz.ustawZebraneKarty(kartyZa93Oczek());

		rozdanie.obliczWynik();
		assertTrue(rozdanie.rodzaj.schneider);
    }

    @Test
    public void brakKrawca89Oczek() {
        gracz.ustawZebraneKarty(kartyZa89Oczek());

		rozdanie.obliczWynik();
		assertFalse(rozdanie.rodzaj.schneider);
    }

    @Test
    public void iloscSzczytowWReceGraczaWynosi2(){
        rozdanie.ustawRodzajGry(graKolorowa(Kolor.DZWONEK));
        rozdanie.ustawWartoscLicytacji(18);
        gracz.ustawPosiadaneKarty(kartyZ2Szczytami());
        rozdanie.ustawSkat(skatZ1Szczytem());
        int iloscSzczytow = rozdanie.policzIloscSzczytow();

        assertEquals(2, iloscSzczytow);
    }

    @Test
    public void GraczGraBezDwochSzczytow(){
        rozdanie.ustawRodzajGry(graKolorowa(Kolor.DZWONEK));
        rozdanie.ustawWartoscLicytacji(18);
        gracz.ustawPosiadaneKarty(kartyBez2Szczytow());
        rozdanie.ustawSkat(skatBezSzczytow());
        int iloscSzczytow = rozdanie.policzIloscSzczytow();

        assertEquals(2, iloscSzczytow);
    }

    @Test
    public void iloscSzczytowWReceGraczaWynosi11(){
        rozdanie.ustawRodzajGry(graKolorowa(Kolor.TREFL));
        rozdanie.ustawWartoscLicytacji(18);
        gracz.ustawPosiadaneKarty(kartyZ10Szczytami());
        rozdanie.ustawSkat(skatZ1Szczytem());

        int iloscSzczytow = rozdanie.policzIloscSzczytow();

        assertEquals(11, iloscSzczytow);
    }

    @Test
    public void GraczGraBezJedenastuSzczytow(){
        rozdanie.ustawRodzajGry(graKolorowa(Kolor.DZWONEK));
        rozdanie.ustawWartoscLicytacji(18);
        gracz.ustawPosiadaneKarty(kartyBez10Szczytow());
        rozdanie.ustawSkat(skatBezSzczytow());

        int iloscSzczytow = rozdanie.policzIloscSzczytow();

        assertEquals(11, iloscSzczytow);
    }

    @Test
    public void iloscSzczytowWReceGraczaWynosi5AleGraczGraGrand(){
        rozdanie.ustawRodzajGry(graGrand());
        rozdanie.ustawWartoscLicytacji(18);
        gracz.ustawPosiadaneKarty(kartyZ5Szczytami());
        rozdanie.ustawSkat(skatBezSzczytow());
        int iloscSzczytow = rozdanie.policzIloscSzczytow();

        assertEquals(4, iloscSzczytow);
    }

    @Test
    public void przeciwnicyPrzegraliSzwarc() {
        gracz.ustawZebraneKarty(karty10Lew());
        rozdanie.obliczIloscZebranychKart();

        rozdanie.obliczWynik();
		assertTrue(rozdanie.rodzaj.schneider);
		assertTrue(rozdanie.rodzaj.schwarz);

    }

    @Test
    public void rozgrywajacyPrzegralSzwarc() {
        gracz.ustawZebraneKarty(karty0Lew());
        rozdanie.obliczIloscZebranychKart();

        rozdanie.obliczWynik();
		assertTrue(rozdanie.rodzaj.schneider);
		assertTrue(rozdanie.rodzaj.schwarz);

    }

    @Test
    public void brakSzwarca9Lew() {
        gracz.ustawZebraneKarty(karty9Lew());

		rozdanie.obliczWynik();
		assertFalse(rozdanie.rodzaj.schwarz);
    }

    @Test
    public void brakSzwarca1Lewa() {
        gracz.ustawZebraneKarty(karty1Lewa());

		rozdanie.obliczWynik();
		assertFalse(rozdanie.rodzaj.schwarz);
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

    private ArrayList<Karta> kartyZa90Oczek(){
        return karty(
                new Karta(Kolor.TREFL, Figura.DZIESIATKA),
                new Karta(Kolor.DZWONEK, Figura.DZIESIATKA),
                new Karta(Kolor.PIK, Figura.DZIESIATKA),
                new Karta(Kolor.SERCE, Figura.DZIESIATKA),
                new Karta(Kolor.TREFL, Figura.AS),
                new Karta(Kolor.DZWONEK, Figura.AS),
                new Karta(Kolor.PIK, Figura.AS),
                new Karta(Kolor.SERCE, Figura.AS),
                new Karta(Kolor.SERCE, Figura.KROLOWA),
                new Karta(Kolor.PIK, Figura.KROLOWA),
                new Karta(Kolor.PIK, Figura.SIODEMKA),
                new Karta(Kolor.PIK, Figura.OSEMKA)
        );
    }

    private ArrayList<Karta> kartyZa93Oczek(){
        return karty(
                new Karta(Kolor.TREFL, Figura.DZIESIATKA),
                new Karta(Kolor.DZWONEK, Figura.DZIESIATKA),
                new Karta(Kolor.PIK, Figura.DZIESIATKA),
                new Karta(Kolor.SERCE, Figura.DZIESIATKA),
                new Karta(Kolor.TREFL, Figura.AS),
                new Karta(Kolor.DZWONEK, Figura.AS),
                new Karta(Kolor.PIK, Figura.AS),
                new Karta(Kolor.SERCE, Figura.AS),
                new Karta(Kolor.SERCE, Figura.KROLOWA),
                new Karta(Kolor.PIK, Figura.KROLOWA),
                new Karta(Kolor.PIK, Figura.SIODEMKA),
                new Karta(Kolor.SERCE, Figura.KROLOWA)
        );
    }

    private ArrayList<Karta> kartyZa89Oczek(){
        return karty(
                new Karta(Kolor.TREFL, Figura.DZIESIATKA),
                new Karta(Kolor.DZWONEK, Figura.DZIESIATKA),
                new Karta(Kolor.PIK, Figura.DZIESIATKA),
                new Karta(Kolor.SERCE, Figura.DZIESIATKA),
                new Karta(Kolor.TREFL, Figura.AS),
                new Karta(Kolor.DZWONEK, Figura.AS),
                new Karta(Kolor.PIK, Figura.AS),
                new Karta(Kolor.SERCE, Figura.AS),
                new Karta(Kolor.SERCE, Figura.JOPEK),
                new Karta(Kolor.PIK, Figura.DZIEWIATKA),
                new Karta(Kolor.PIK, Figura.SIODEMKA),
                new Karta(Kolor.SERCE, Figura.KROLOWA)
        );
    }

    private ArrayList<Karta> kartyZ2Szczytami(){
        return karty(
                new Karta(Kolor.TREFL, Figura.JOPEK),
                new Karta(Kolor.PIK, Figura.JOPEK),
                new Karta(Kolor.SERCE, Figura.AS),
                new Karta(Kolor.DZWONEK, Figura.AS),
                new Karta(Kolor.TREFL, Figura.DZIESIATKA),
                new Karta(Kolor.PIK, Figura.KROL),
                new Karta(Kolor.SERCE, Figura.DZIESIATKA),
                new Karta(Kolor.PIK, Figura.KROLOWA),
                new Karta(Kolor.SERCE, Figura.SIODEMKA),
                new Karta(Kolor.SERCE, Figura.DZIEWIATKA)
        );
    }

    private ArrayList<Karta> kartyBez2Szczytow(){
        return karty(
                new Karta(Kolor.TREFL, Figura.AS),
                new Karta(Kolor.PIK, Figura.AS),
                new Karta(Kolor.SERCE, Figura.JOPEK),
                new Karta(Kolor.DZWONEK, Figura.JOPEK),
                new Karta(Kolor.TREFL, Figura.SIODEMKA),
                new Karta(Kolor.PIK, Figura.KROL),
                new Karta(Kolor.SERCE, Figura.DZIESIATKA),
                new Karta(Kolor.PIK, Figura.KROLOWA),
                new Karta(Kolor.SERCE, Figura.SIODEMKA),
                new Karta(Kolor.SERCE, Figura.DZIEWIATKA)
        );
    }

    private ArrayList<Karta> kartyZ10Szczytami(){
        return karty(
                new Karta(Kolor.TREFL, Figura.JOPEK),
                new Karta(Kolor.PIK, Figura.JOPEK),
                new Karta(Kolor.SERCE, Figura.JOPEK),
                new Karta(Kolor.DZWONEK, Figura.JOPEK),
                new Karta(Kolor.TREFL, Figura.AS),
                new Karta(Kolor.TREFL, Figura.DZIESIATKA),
                new Karta(Kolor.TREFL, Figura.KROL),
                new Karta(Kolor.TREFL, Figura.KROLOWA),
                new Karta(Kolor.TREFL, Figura.DZIEWIATKA),
                new Karta(Kolor.TREFL, Figura.OSEMKA)
        );
    }

    private ArrayList<Karta> kartyBez10Szczytow(){
        return karty(
                new Karta(Kolor.TREFL, Figura.AS),
                new Karta(Kolor.PIK, Figura.AS),
                new Karta(Kolor.SERCE, Figura.AS),
                new Karta(Kolor.PIK, Figura.DZIESIATKA),
                new Karta(Kolor.TREFL, Figura.DZIESIATKA),
                new Karta(Kolor.PIK, Figura.KROL),
                new Karta(Kolor.SERCE, Figura.DZIESIATKA),
                new Karta(Kolor.PIK, Figura.KROLOWA),
                new Karta(Kolor.SERCE, Figura.SIODEMKA),
                new Karta(Kolor.SERCE, Figura.DZIEWIATKA)
        );
    }

    private ArrayList<Karta> kartyZ5Szczytami(){
        return karty(
                new Karta(Kolor.TREFL, Figura.JOPEK),
                new Karta(Kolor.PIK, Figura.JOPEK),
                new Karta(Kolor.SERCE, Figura.JOPEK),
                new Karta(Kolor.DZWONEK, Figura.JOPEK),
                new Karta(Kolor.DZWONEK, Figura.AS),
                new Karta(Kolor.PIK, Figura.KROL),
                new Karta(Kolor.SERCE, Figura.DZIESIATKA),
                new Karta(Kolor.PIK, Figura.KROLOWA),
                new Karta(Kolor.SERCE, Figura.SIODEMKA),
                new Karta(Kolor.SERCE, Figura.DZIEWIATKA)
        );
    }
    private Skat skatBezSzczytow(){
        Skat skat = new Skat();
        skat.ustawKarta1(new Karta(Kolor.TREFL, Figura.DZIEWIATKA));
        skat.ustawKarta2(new Karta(Kolor.TREFL, Figura.OSEMKA));
        return skat;
    }
    private Skat skatZ1Szczytem(){
        Skat skat = new Skat();
        skat.ustawKarta1(new Karta(Kolor.TREFL, Figura.SIODEMKA));
        skat.ustawKarta2(new Karta(Kolor.DZWONEK, Figura.OSEMKA));
        return skat;
    }

    private ArrayList<Karta> karty(Karta... karty){
        ArrayList<Karta> zebraneKarty = new ArrayList<>();

        for (Karta karta : karty){
            zebraneKarty.add(karta);
        }

        return zebraneKarty;
    }

    private ArrayList<Karta> karty10Lew(){
        return karty(
                new Karta(Kolor.TREFL, Figura.AS),
                new Karta(Kolor.PIK, Figura.AS),
                new Karta(Kolor.SERCE, Figura.AS),
                new Karta(Kolor.DZWONEK, Figura.AS),
                new Karta(Kolor.SERCE, Figura.JOPEK),
                new Karta(Kolor.PIK, Figura.JOPEK),
                new Karta(Kolor.SERCE, Figura.JOPEK),
                new Karta(Kolor.TREFL, Figura.JOPEK),

                new Karta(Kolor.SERCE, Figura.KROLOWA),
                new Karta(Kolor.PIK, Figura.KROLOWA),
                new Karta(Kolor.SERCE, Figura.KROLOWA),
                new Karta(Kolor.TREFL, Figura.KROLOWA),

                new Karta(Kolor.SERCE, Figura.KROL),
                new Karta(Kolor.PIK, Figura.KROL),
                new Karta(Kolor.SERCE, Figura.KROL),
                new Karta(Kolor.TREFL, Figura.KROL),

                new Karta(Kolor.SERCE, Figura.DZIESIATKA),
                new Karta(Kolor.PIK, Figura.DZIESIATKA),
                new Karta(Kolor.SERCE, Figura.DZIESIATKA),
                new Karta(Kolor.TREFL, Figura.DZIESIATKA),

                new Karta(Kolor.SERCE, Figura.DZIEWIATKA),
                new Karta(Kolor.PIK, Figura.DZIEWIATKA),
                new Karta(Kolor.SERCE, Figura.DZIEWIATKA),
                new Karta(Kolor.TREFL, Figura.DZIEWIATKA),

                new Karta(Kolor.SERCE, Figura.OSEMKA),
                new Karta(Kolor.PIK, Figura.OSEMKA),
                new Karta(Kolor.SERCE, Figura.OSEMKA),
                new Karta(Kolor.TREFL, Figura.OSEMKA),

                new Karta(Kolor.SERCE, Figura.SIODEMKA),
                new Karta(Kolor.PIK, Figura.SIODEMKA)
        );
    }

     private ArrayList<Karta> karty0Lew(){
        return karty(       );
    }

    private ArrayList<Karta> karty9Lew(){
        return karty(
                new Karta(Kolor.TREFL, Figura.AS),
                new Karta(Kolor.PIK, Figura.AS),
                new Karta(Kolor.SERCE, Figura.AS),
                new Karta(Kolor.DZWONEK, Figura.AS),
                new Karta(Kolor.SERCE, Figura.JOPEK),
                new Karta(Kolor.PIK, Figura.JOPEK),
                new Karta(Kolor.SERCE, Figura.JOPEK),
                new Karta(Kolor.TREFL, Figura.JOPEK),

                new Karta(Kolor.SERCE, Figura.KROLOWA),
                new Karta(Kolor.PIK, Figura.KROLOWA),
                new Karta(Kolor.SERCE, Figura.KROLOWA),
                new Karta(Kolor.TREFL, Figura.KROLOWA),

                new Karta(Kolor.SERCE, Figura.KROL),
                new Karta(Kolor.PIK, Figura.KROL),
                new Karta(Kolor.SERCE, Figura.KROL),
                new Karta(Kolor.TREFL, Figura.KROL),

                new Karta(Kolor.SERCE, Figura.DZIESIATKA),
                new Karta(Kolor.PIK, Figura.DZIESIATKA),
                new Karta(Kolor.SERCE, Figura.DZIESIATKA),
                new Karta(Kolor.TREFL, Figura.DZIESIATKA),

                new Karta(Kolor.SERCE, Figura.DZIEWIATKA),
                new Karta(Kolor.PIK, Figura.DZIEWIATKA),
                new Karta(Kolor.SERCE, Figura.DZIEWIATKA),
                new Karta(Kolor.TREFL, Figura.DZIEWIATKA),

                new Karta(Kolor.SERCE, Figura.OSEMKA),
                new Karta(Kolor.PIK, Figura.OSEMKA),
                new Karta(Kolor.SERCE, Figura.OSEMKA)
        );
    }

    private ArrayList<Karta> karty1Lewa(){
        return karty(
                new Karta(Kolor.TREFL, Figura.AS),
                new Karta(Kolor.PIK, Figura.AS),
                new Karta(Kolor.SERCE, Figura.AS)
        );
    }
}
