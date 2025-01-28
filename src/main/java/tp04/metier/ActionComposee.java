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
public class ActionComposee extends Action {
    // attribut lien
    Map<ActionSimple, Float> mapPanier;

    public ActionComposee(String libelle) {
        super(libelle);
        this.mapPanier = new HashMap();
    }

    public void enrgComposition(ActionSimple as, float pourcentage) {
        this.mapPanier.put(as, pourcentage);
    }

    @Override
    public float valeur(Jour j) {
        float valeur;

        valeur = 0;
        for (ActionSimple as : this.mapPanier.keySet()) {
            valeur = valeur + (as.valeur(j) * this.mapPanier.get(as));
        }

        return valeur;
    }

    public String visualiserCours(Jour jInf, Jour jSup) {
        // pour chaque jour de jInf jusqu'à jSup, on affiche le cours de mapCours
        int nbJourJSup = (jSup.getAnnee() - jInf.getAnnee()) * 365 + jSup.getNoJour();
        String str = new String();
        for (int i = jInf.getNoJour(); i < nbJourJSup + 1; i++) {
            // Si une évolution dans mapCours, alors on sysout le cours et le jour
            int anneeDiff = i / 365;
            int anneeReel = jInf.getAnnee() + anneeDiff;
            int jourReel = i % 365;
            Jour jReel = new Jour(anneeReel, jourReel);
            // Pour chaque action simple de l'action composée
            for (ActionSimple as : mapPanier.keySet()) {
                if (as.getMapCours().containsKey(jReel)) {
                    str += "Action " + as.getLibelle() + " Jour : " + jReel.toString() + " Cours : " + as.valeur(jReel)
                            + "\n";
                }
            }
        }
        return str;
    }

    @Override
    public String toString() {
        String str = "";
        int count = 0;
        int size = this.mapPanier.size();

        for (ActionSimple key : this.mapPanier.keySet()) {
            str += key.getLibelle() + " : " + this.mapPanier.get(key);
            count++;
            if (count < size) {
                str += ", ";
            }
        }
        return str;
    }

    // public void afficherPerformancesIndividuelles(Jour j) {

    // return null;
    // }

}
