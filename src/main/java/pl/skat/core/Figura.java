package pl.skat.core;

public enum Figura{
        SIODEMKA(0),
        OSEMKA(0),
        DZIEWIATKA(0),
        DZIESIATKA(10),
        JOPEK(2),
        KROLOWA(3),
        KROL(4),
        AS(11);

        private final int kod;

        Figura(int kod){
            this.kod = kod;
        }

        public int getKod(){
            return kod;
        }


    }
