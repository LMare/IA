package fr.lesprogbretons.seawar.ia.ai.groupe_g.alphabeta;


import fr.lesprogbretons.seawar.ia.ai.groupe_g.etat.Etat;
import fr.lesprogbretons.seawar.model.Partie;
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
                int filsVal = noeud.alphabeta(alpha, beta);
                if(alpha < filsVal) {
                    alpha = filsVal;
                    bestNoeud = noeud;
                }
                if (beta < alpha) break;
            }
            return alpha;
        }
    }






}
