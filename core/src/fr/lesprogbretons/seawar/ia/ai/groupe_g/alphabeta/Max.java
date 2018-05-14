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

    public Max(Etat etat){
        super(etat);
    }


    @Override
    public void genererFils() { //TODO: preciser le nombre d'etage de generation ?
        //TODO: creer les fils du noeud en cours en fonction de l'état
        //deplacement d'une case (le controller ne gère que des déplacements d'une case) + tir possible

        HashSet<Action> actions = getActionsPossible();
        for (Action action : actions) {
            Noeud nextAction = new Max(etat.clone());
            nextAction.getEtat().simulateAction(action); //mettre à jour l'état
            fils.add(nextAction);
            //TODO: generer les fils de nextAction => implementer avec une file ???
        }

        Noeud nextAction = new Min(etat.clone());// TODO: on le fait avec un Min ?
        nextAction.getEtat().simulateAction(new PassTurn(null));//hmm... PassTurn serait plutôt pour un bateau...
    }


    @Override
    public int utilite() {
        //TODO: Creer une fonction heuristique qui retourne une valeur en fonction de l'etat

        return 0;
    }

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
