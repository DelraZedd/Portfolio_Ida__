/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tp04.metier;

/**
 *
 * @author perussel
 */
public class Cours {
    private float valeur;
    private Jour jour;

    public Cours(Jour j, float v) {
        this.jour = j;
        this.valeur = v;
    }

    public float getValeur() {
        return valeur;
    }

    public Jour getJour() {
        return jour;
    }

}
