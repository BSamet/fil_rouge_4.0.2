package fr.bulutsamet;

import fr.bulutsamet.Forms.Cercle;
import fr.bulutsamet.Forms.Rectangle;
import fr.bulutsamet.Forms.Triangle;

public class Main {

    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(5,5,5);
        System.out.println("le perimètre du premier rectangle est de " + r1.getPerimetre() + " cm et l'aire est de " + r1.getAire() + " cm2 et le volume est de : " + r1.getVolume() + " cm3.");

        Cercle c1 = new Cercle(5);
        System.out.println("le perimètre du premier cercle est de " + c1.getPerimetre() + " cm et l'aire est de " + c1.getAire() + " cm2.");

        Triangle t1 = new Triangle(10,20);
        System.out.println("le perimètre du premier triangle est de " + t1.getPerimetre() + " cm et l'aire est de " + t1.getAire() + " cm2.");
    }
}
