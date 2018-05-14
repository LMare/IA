package fr.lesprogbretons.seawar.ia.ai.groupe_g.alphabeta;

import fr.lesprogbretons.seawar.ia.etat.Etat;
import fr.lesprogbretons.seawar.model.actions.Action;

import java.util.HashSet;

public abstract class Noeud {

    protected Etat etat;
    protected HashSet<Noeud> fils;
    protected Action action;

    //protected Joueur joueur


    /**
     * Constructeur :
     *
     */
    Noeud(Etat etat) {
        this.etat = etat;
    }

    public abstract void genererFils();
    public abstract int utilite();
    public abstract int alphabeta(int alpha, int beta);
    public abstract HashSet<Action> getActionsPossible();

    public Etat getEtat() {
        return etat;
    }
}
