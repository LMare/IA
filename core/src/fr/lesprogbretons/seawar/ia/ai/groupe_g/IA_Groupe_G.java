package fr.lesprogbretons.seawar.ia.ai.groupe_g;

import fr.lesprogbretons.seawar.ia.AbstractIA;
import fr.lesprogbretons.seawar.ia.ai.groupe_g.alphabeta.Max;
import fr.lesprogbretons.seawar.ia.ai.groupe_g.alphabeta.Noeud;
import fr.lesprogbretons.seawar.ia.ai.groupe_g.etat.Etat;
import fr.lesprogbretons.seawar.model.Partie;
import fr.lesprogbretons.seawar.model.actions.Action;




public class IA_Groupe_G extends AbstractIA {

    public IA_Groupe_G(int number) {
        super(number);
    }

    @Override
    public Action chooseAction(Partie partie) {
        // On a le droit à 4 threads

        // On commence le tour, il faut créer un noeud
        Max initial = new Max((Etat) partie.clone());

        // on a TIME_TO_THINK = 1000 ms pour trouver un executer une/des actions
        long debut = System.currentTimeMillis();
        // on cherche la meilleur action etage par etage
        int etage = 1;
        int alphabeta;

        boolean process = true;
        while (process) {
            if(System.currentTimeMillis() - debut > 0.8 * AbstractIA.TIME_TO_THINK) { // TODO: ameliorer la condition

                //TODO: retrouver la meilleur branche avec la valeur alphabeta !
                //TODO: Executer la meilleur action/ les actions (tout les noeuds max)
                Noeud noeud = initial;
                while (noeud instanceof Max) {
                    noeud.getBestNoeud().getAction().apply(null);
                    noeud = noeud.getBestNoeud();
                }


                process = false;
            } else { // On a du temps pour trouver une solution plus precise
                initial.genererFils(etage);
                alphabeta = initial.alphabeta(Integer.MAX_VALUE, Integer.MIN_VALUE);

                // On incremente le nombre d'étage
                etage++;
                // idee pour ameliorer les performances : trier les noeuds fils
                // parallelisation avec des threads (pour la generation des fils)

                System.out.println("etage = "+etage);
            }
        }


        return null;
    }


}
