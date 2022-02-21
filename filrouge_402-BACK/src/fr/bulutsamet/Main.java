package fr.bulutsamet;

import fr.bulutsamet.Forms.*;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // Créer une instance d'un rectangle
        Rectangle r1 = new Rectangle(5,5);
        System.out.println(r1);
        r1.setLongueur(2);
        r1.setLargeur(2);
        System.out.println("Après modification " + r1);
        //

        // Créer une instance d'un cercle
        Circle c1 = new Circle(5);
        System.out.println(c1);
        //

        // Créer une instance d'un triangle
        Triangle t1 = new Triangle(10,20);
        System.out.println(t1);
        //

        FormsManager manageForms = new FormsManager(Arrays.asList(r1, t1, c1));
        System.out.println(manageForms);
    }
}
