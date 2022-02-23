package fr.bulutsamet;

import fr.bulutsamet.Forms.*;
import fr.bulutsamet.Forms3d.Forms3d;
import fr.bulutsamet.Forms3d.Forms3dComposite;

public class Main {

    public static void main(String[] args) {
        // Créer une instance d'un rectangle
        Rectangle r1 = new Rectangle(6,12, "Rectangle 1");
        System.out.println(r1);
        Rectangle r2 = new Rectangle(8,79, "Rectangle 2");
        System.out.println(r2);
        //

        // Créer une instance d'un cercle
        Circle c1 = new Circle(90, "Cercle 1");
        System.out.println(c1);
        //

        // Créer une instance d'un triangle
        Triangle t1 = new Triangle(10,7, "Triangle 1");
        System.out.println(t1);
        //

        FormsComposite manageForms = new FormsComposite();
        manageForms.addForm(r1);
        System.out.println(manageForms);
        manageForms.addForm(r2);
        manageForms.addForm(c1);
        manageForms.addForm(t1);
        System.out.println(manageForms);

        Forms3d myForms3d = new Forms3d(r1, 10);
        System.out.println(myForms3d);

        Forms3d myForms3dT = new Forms3d(t1, 10);
        System.out.println(myForms3dT);

        Forms3dComposite forms3d = new Forms3dComposite();
        forms3d.add3dForm(myForms3d);
        forms3d.add3dForm(myForms3dT);
        System.out.println(forms3d);
    }
}
