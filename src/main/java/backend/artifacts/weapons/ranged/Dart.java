package backend.artifacts.weapons.ranged;

import backend.artifacts.weapons.RangedFinesseWeapon;
import backend.logic.Dice;

    public class Dart extends RangedFinesseWeapon {
    public Dart(){super(new Dice (4),2);}


            private int anzahlDart;
            private int maximaleDart;
             Dart dart = new Dart(20);


            public Dart(int maximaleDart) {
                this();
                this.maximaleDart = maximaleDart;
                this.anzahlDart = maximaleDart;
                this.dartthrow();
            }

            public void dartthrow() {
                if (anzahlDart > 0) {
                    anzahlDart--;
                    System.out.println("A dart was thrown. there remain " + anzahlDart + " darts.");
                } else {
                    System.out.println("There are no more darts available.");
                }

                for (int i = 0; i < 20; i++) {
                    dart.dartthrow();
                }
            }

            public int getAnzahlDart() {
                return anzahlDart;
            }

            {
               System.out.println("there remain " + dart.getAnzahlDart() + " darts.");
            }
    }