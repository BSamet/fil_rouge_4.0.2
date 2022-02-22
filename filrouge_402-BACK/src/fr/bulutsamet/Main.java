package fr.bulutsamet;

import fr.bulutsamet.Forms.*;

public class Main {

    public static void main(String[] args) {
        // Créer une instance d'un rectangle
        Rectangle r1 = new Rectangle(6,12);
        System.out.println(r1);
        Rectangle r2 = new Rectangle(8,79);
        System.out.println(r2);
        //

        // Créer une instance d'un cercle
        Circle c1 = new Circle(5);
        System.out.println(c1);
        //

        // Créer une instance d'un triangle
        Triangle t1 = new Triangle(10,20);
        System.out.println(t1);
        //

        FormsComposite manageForms = new FormsComposite();
        manageForms.addForm(r1);
        System.out.println(manageForms);
        manageForms.addForm(r2);
        manageForms.addForm(c1);
        manageForms.addForm(t1);
        System.out.println(manageForms);
    }
}
