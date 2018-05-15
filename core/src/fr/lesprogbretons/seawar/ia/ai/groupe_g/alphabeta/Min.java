package fr.lesprogbretons.seawar.ia.ai.groupe_g.alphabeta;


import fr.lesprogbretons.seawar.ia.ai.groupe_g.etat.Etat;
import fr.lesprogbretons.seawar.model.Partie;
import fr.lesprogbretons.seawar.model.actions.Action;
import fr.lesprogbretons.seawar.model.actions.PassTurn;
import fr.lesprogbretons.seawar.model.boat.Boat;

import java.util.HashSet;

import static java.lang.Math.min;

public class Min extends Noeud {

    public Min(Etat etat){
        super(etat);
    }

    @Override
    public void genererFils() {
        //TODO: creer les fils du noeud en cours en fonction de l etat
        //deplacement d'une case (le controller ne gère que des déplacements d'une case) + tir possible

        HashSet<Action> actions = getActionsPossible();
        for (Action action : actions) {
            Noeud nextAction = new Min(etat.clone());
            nextAction.getEtat().simulateAction(action); //mettre à jour l'état
            fils.add(nextAction);
            //TODO: generer les fils de nextAction => implementer avec une file ???
        }

        Noeud nextAction = new Max(etat.clone());// TODO: on le fait avec un Max ?
        nextAction.getEtat().simulateAction(new PassTurn(null));
    }

    @Override
    public int utilite() {
        //TODO: Ameliorer l'heuristique
        Boat nav1 = etat.getPartie().getMap().getBateaux2().get(0);


        return -distNearestPhare(nav1);
    }

    @Override
    public int alphabeta(int alpha, int beta) {
        if(fils == null || fils.isEmpty()) {
            return utilite();
        } else {
            for (Noeud noeud : fils) {
                alpha = min(beta, noeud.alphabeta(alpha, beta));
                if (beta < alpha) break;
            }
            return beta;//TODO:  verifier !!!
        }
    }


    @Override
    public HashSet<Action> getActionsPossible() {
        //TODO: etudier les actions possible pour min (bateau du joueur2 par ex)
        return null;
    }
}
