package fr.bulutsamet.Forms;

public class Forms3d {
    private Forms forms;
    private double profondeur;

    public double getVolumes() {
        if(forms instanceof Triangle) {
            double base = ((Triangle) forms).getBase();
            double longueur = ((Triangle) forms).getLongueur();
            double hauteur = Math.sqrt((longueur * longueur) - (base * base) / 4);
            return (float)1/3 * base * this.profondeur * hauteur;
        } else if(forms instanceof Rectangle) {
            double largeur = ((Rectangle) forms).getLargeur();
            double longueur = ((Rectangle) forms).getLongueur();
            return longueur * largeur * profondeur;
        } else if (forms instanceof Circle) {
            double rayon = ((Circle) forms).getRayon();
            return (4 * Math.PI * (10*10*10)) / 3;
        } else {
            return 0;
        }
    }

    public Forms getForms() {
        return forms;
    }

    public void setForms(Forms forms) {
        this.forms = forms;
    }

    public double getProfondeur() {
        return profondeur;
    }

    public void setProfondeur(double profondeur) {
        this.profondeur = profondeur;
    }

    public Forms3d(){
    }

    public Forms3d(Forms forms) {
        this.forms = forms;
    }
}
