package fr.lesprogbretons.seawar.ia.ai.groupe_g.etat;

import fr.lesprogbretons.seawar.model.Orientation;
import fr.lesprogbretons.seawar.model.Partie;
import fr.lesprogbretons.seawar.model.actions.Action;
import fr.lesprogbretons.seawar.model.actions.Attack;
import fr.lesprogbretons.seawar.model.actions.MoveBoat;
import fr.lesprogbretons.seawar.model.actions.PassTurn;
import fr.lesprogbretons.seawar.model.boat.Boat;
import fr.lesprogbretons.seawar.model.cases.Case;
import fr.lesprogbretons.seawar.model.cases.CaseEau;
import fr.lesprogbretons.seawar.model.cases.CaseTerre;
import fr.lesprogbretons.seawar.model.map.Grille;

import java.util.ArrayList;
import java.util.HashSet;

public class Etat {

    private Partie partie;

    public Etat(Partie p) {
        partie = p;

    }

    public Etat clone() {
        Etat clone = new Etat(partie);
        clone.setPartie(partie.clone());
        return clone;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }

    public void simulateAction(Action action) {
        if(action instanceof MoveBoat) {
            MoveBoat moveBoat = (MoveBoat) action;
            moveBoat.getBoat().moveBoat(moveBoat.getTarget());
        }
        if(action instanceof Attack) {
            Attack attack = (Attack) action;
            attack.getBoat().shoot(partie.getMap().bateauSurCase(attack.getTarget()));
        }
        if(action instanceof PassTurn) {
            partie.endTurn();
        }
    }

    //TODO: cloner un etat, trouver les actions à faire...


    public ArrayList<Case> getMove(Boat boat) {
        if(boat.getMove() != 0) {
            return partie.getMap().getCasesDisponibles(boat.getPosition(), 1);
        } else {
            return null;// pas de deplacement possible
        }
    }

}
