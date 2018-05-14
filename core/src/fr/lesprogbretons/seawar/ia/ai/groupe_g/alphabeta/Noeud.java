package fr.lesprogbretons.seawar.ia.ai.groupe_g.alphabeta;

import fr.lesprogbretons.seawar.ia.ai.groupe_g.etat.Etat;
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
    public Noeud(Etat etat) {
        this.etat = etat;
    }

    public abstract void genererFils();
    public abstract int utilite();
    public abstract int alphabeta(int alpha, int beta);
    public abstract HashSet<Action> getActionsPossible();

    public Etat getEtat() {
        return etat;
    }

    public void genererFils(int etage) {
        if(fils == null) {
            fils = new HashSet<Noeud>();
            genererFils();
        }
        if(etage > 0) {
            for (Noeud noeud : fils) {
                noeud.genererFils(--etage);
            }
        }
    }
}
