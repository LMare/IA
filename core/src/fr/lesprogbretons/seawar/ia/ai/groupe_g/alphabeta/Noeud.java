package fr.lesprogbretons.seawar.ia.ai.groupe_g.alphabeta;

import fr.lesprogbretons.seawar.ia.ai.groupe_g.etat.Etat;
import fr.lesprogbretons.seawar.model.Partie;
import fr.lesprogbretons.seawar.model.actions.Action;
import fr.lesprogbretons.seawar.model.boat.Boat;
import fr.lesprogbretons.seawar.model.cases.Case;
import fr.lesprogbretons.seawar.model.map.Grille;

import java.util.HashSet;

public abstract class Noeud {

    protected Etat etat;
    protected HashSet<Noeud> fils;
    protected Action action;

    //protected Joueur joueur


    /**
     * Constructeur :
     */
    public Noeud(Etat etat) {
        this.etat = etat;
    }

    public abstract void genererFils();

    /**On cherche a minimiser l'utilite*/
    public abstract int utilite();

    public abstract int alphabeta(int alpha, int beta);

    public abstract HashSet<Action> getActionsPossible();

    public Etat getEtat() {
        return etat;
    }

    public void genererFils(int etage) {
        if (fils == null) {
            fils = new HashSet<Noeud>();
            genererFils();
        }
        if (etage > 0) {
            for (Noeud noeud : fils) {
                noeud.genererFils(--etage);
            }
        }
    }

    /**
     * Fonction permettant de trouver les 3 phares, utile pour l'heuristique
     */
    protected Case[] getPhares() {
        int i;
        int j;
        int num = 0;
        Partie partie=etat.getPartie();
        Case[] cases;
        cases = new Case[]{null, null, null};
        Grille gril = partie.getMap();
        for (i = 0; i < gril.getHauteur(); i++) {
            for (j = 0; j < gril.getLargeur(); j++) {
                if (gril.getCase(i, j).isPhare()) {
                    cases[num] = gril.getCase(i, j);
                    num += 1;
                }
            }
        }
        return cases;
    }

    /**
     * Fonction indiquant la distance au phare non occupe par soi le plus proche
     * @param nav bateau concerne
     * @return La distance au phare le plus proche du bateau
     */
    protected int distNearestPhare(Boat nav) {
        int i;
        int minDist=Integer.MAX_VALUE;

        Case[] phares=getPhares();
        for(i=0;i<3;i++){//3 car il y toujours 3 phares
            //TODO Pour l'instant,on ne s'interesent qu'au navire 1, a changer
            int dx=nav.getPosition().getX()-phares[i].getX();
            int dy=nav.getPosition().getY()-phares[i].getY();
            int dist=(int) Math.floor(Math.sqrt(dx*dx+dy*dy));
            if(dist<minDist){
                minDist=dist;
            }
        }
        return minDist;
    }


}