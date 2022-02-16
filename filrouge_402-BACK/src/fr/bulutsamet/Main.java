package fr.bulutsamet;

import fr.bulutsamet.Forms.Cercle;
import fr.bulutsamet.Forms.Rectangle;
import fr.bulutsamet.Forms.Triangle;

public class Main {

    public static void main(String[] args) {
        // Créer une instance d'un rectangle
        Rectangle r1 = new Rectangle(5,5);
        System.out.println(r1.toString());
        r1.modifRectangle(2,2);
        System.out.println("Après modification " + r1.toString());
        //

        // Créer une instance d'un cercle
        Cercle c1 = new Cercle(5);
        System.out.println(c1.toString());
        //

        // Créer une instance d'un triangle
        Triangle t1 = new Triangle(10,20);
        System.out.println(c1.toString());
        //
    }
}
