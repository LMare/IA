package fr.lesprogbretons.seawar.ia.ai.groupe_g.alphabeta;


import fr.lesprogbretons.seawar.ia.ai.groupe_g.etat.Etat;
import fr.lesprogbretons.seawar.model.actions.Action;
import fr.lesprogbretons.seawar.model.actions.MoveBoat;
import fr.lesprogbretons.seawar.model.actions.PassTurn;
import fr.lesprogbretons.seawar.model.boat.Boat;
import fr.lesprogbretons.seawar.model.cases.Case;

import java.util.ArrayList;
import java.util.HashSet;

import static java.lang.StrictMath.max;

public class Max extends Noeud {

    /*
    *  Stocker :
    *  le joueur + l'adv ?
    *  valeur de retour de l'algo alphabeta ?
    *
    *
    *
    * */




    public Max(Etat etat){
        super(etat);
    }


    @Override
    public void genererFils() {

        HashSet<Action> actions = getActionsPossible();
        for (Action action : actions) {
            Noeud nextAction = new Max(etat.clone());
            nextAction.getEtat().simulateAction(action); //mettre à jour l'état
            fils.add(nextAction);
        }
        //TODO: changement de tour
        Noeud nextAction = new Min(etat.clone());// TODO: on le fait avec un Min ?
        nextAction.getEtat().simulateAction(new PassTurn(null));//hmm... PassTurn serait plutôt pour un bateau...
    }

    /**
     * Fonction heuristique
     * @return une heuristique
     */
    @Override
    public int utilite() {
        //TODO: Creer une fonction heuristique qui retourne une valeur en fonction de l'etat

        return 0;
    }

    /**
     * Algorithme AlphaBeta
     * @param alpha
     * @param beta
     * @return un entier
     * //TODO : faire une fonction pour retouver le noeud grace à la valeur de retour de l'algo
     * //TODO : Strocker dans une variable d'instance la valeur alpha ???
     */
    @Override
    public int alphabeta(int alpha, int beta) {
        if(fils == null || fils.isEmpty()) {
            return utilite();
        } else {
            for (Noeud noeud : fils) {
                alpha = max(alpha, noeud.alphabeta(alpha, beta));
                if (beta < alpha) break;
            }
            return alpha;
        }
    }

    /**
     * Cherche toutes les actions possibles (deplacement + tir) pour les 2 bateaux
     * @return Liste d'action
     * //TODO: A finir + passer le tour ??? (ou dans genererFils si on le fait avec un min)
     */
    @Override
    public HashSet<Action> getActionsPossible() {
        //TODO: etudier les actions possible pour max (bateau du joueur1 par ex)
        HashSet<Action> actions = new HashSet<Action>();
        Boat nav1 = etat.getPartie().getMap().getBateaux1().get(0);
        Boat nav2 = etat.getPartie().getMap().getBateaux1().get(1);

        if(false) {// canPlayBoat1
            actions.addAll(getDeplacement(nav1));
            //TODO: tir bateau 1
            if(nav1.getMove() != nav1.getMoveAvailable()) {
                actions.add(new PassTurn(nav1));
            }
        }
        if(false) {// canPlayBoat2
            actions.addAll(getDeplacement(etat.getPartie().getMap().getBateaux1().get(1)));
            //TODO: tir bateau 2
            if(nav2.getMove() != nav2.getMoveAvailable()) {
                actions.add(new PassTurn(nav1));
            }
        }


        return null;
    }


    /**
     * Cherche les deplacements possible (d'une seule case) pour un bateau
     * @param boat
     * @return Liste d'actions de Deplacement
     */
    public HashSet<Action> getDeplacement(Boat boat) {
        HashSet<Action> actions = new HashSet<Action>();
        ArrayList<Case> tab = etat.getMove(boat);
        if(tab != null) {
            for (Case cell: tab) {
                MoveBoat action = new MoveBoat(boat, cell);
                actions.add(action);
            }
        }
        return actions;
    }

}
