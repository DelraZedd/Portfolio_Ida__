/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tp04.metier;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author perussel
 */
public class ActionSimple extends Action {

    // attribut lien
    private Map<Jour, Cours> mapCours;

    // constructeur
    public ActionSimple(String libelle) {
        // Action simple initialisée comme 1 action
        super(libelle);
        // init spécifique
        this.mapCours = new HashMap();
    }

    // enrg possible si pas de cours pour ce jour
    public void enrgCours(Jour j, float v) {
        if (this.mapCours.containsKey(j) == false)
            this.mapCours.put(j, new Cours(j, v));
    }

    @Override
    public float valeur(Jour j) {
        if (this.mapCours.containsKey(j) == true)
            return this.mapCours.get(j).getValeur();
        else
            return 0; // definition d'une constante possible
    }

    public String visualiserCours(Jour jInf, Jour jSup) {
        // pour chaque jour de jInf jusqu'à jSup, on affiche le cours de mapCours
        int nbJourJSup = (jInf.getAnnee() - jSup.getAnnee()) * 365 + jSup.getNoJour();
        String str = new String();
        for (int i = jInf.getNoJour(); i < nbJourJSup; i++) {
            // Si une évolution dans mapCours, alors on sysout le cours et le jour
            int anneeDiff = i / 365;
            int anneeReel = jInf.getAnnee() + anneeDiff;
            int jourReel = i % 365;
            Jour jReel = new Jour(anneeReel, jourReel);
            if (mapCours.containsKey(jReel)) {
                str += "Jour : " + jReel.toString() + " Cours : " + mapCours.get(jReel) + "\n";
            }
        }
        return str;
    }

    // encapsulation de la définition de la classe Cours
    private class Cours {

        private Jour jour;

        private float valeur;

        public float getValeur() {
            return valeur;
        }

        public Jour getJour() {
            return jour;
        }

        public Cours(Jour jour, float valeur) {
            this.jour = jour;
            this.valeur = valeur;
        }

    }
}
