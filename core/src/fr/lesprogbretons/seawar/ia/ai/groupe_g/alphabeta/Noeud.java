package fr.lesprogbretons.seawar.ia.ai.groupe_g.alphabeta;

import fr.lesprogbretons.seawar.ia.ai.groupe_g.etat.Etat;
import fr.lesprogbretons.seawar.model.Partie;
import fr.lesprogbretons.seawar.model.actions.Action;
import fr.lesprogbretons.seawar.model.actions.MoveBoat;
import fr.lesprogbretons.seawar.model.actions.PassTurn;
import fr.lesprogbretons.seawar.model.boat.Boat;
import fr.lesprogbretons.seawar.model.cases.Case;
import fr.lesprogbretons.seawar.model.map.Grille;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class Noeud {

    protected Etat etat;
    protected HashSet<Noeud> fils;
    protected Action action;
    protected Noeud bestNoeud;

    //protected Joueur joueur


    /**
     * Constructeur :
     */
    public Noeud(Etat etat) {
        this.etat = etat;
    }

    public abstract void genererFils();

    public abstract int alphabeta(int alpha, int beta);

    /**On cherche a minimiser l'utilite*/
    public int utilite() {
        //TODO: Ameliorer l'heuristique

        Boat nav1 = etat.getPartie().getCurrentPlayer().getBoats().get(0);

        return -distNearestPhare(nav1);
    }




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
            if(phares[i].getPossedePhare()!=etat.getPartie().getCurrentPlayer()) {//On ss'interesent uniquement aux phares n'appartenant pas au joueur courant (soi)
                int dx = nav.getPosition().getX() - phares[i].getX();
                int dy = nav.getPosition().getY() - phares[i].getY();
                int dist = (int) Math.floor(Math.sqrt(dx * dx + dy * dy));
                if (dist < minDist) {
                    minDist = dist;
                }
            }
        }
        return minDist;
    }


    /**
     * Cherche toutes les actions possibles (deplacement + tir) pour les 2 bateaux
     * @return Liste d'action
     */
    public HashSet<Action> getActionsPossible() {
        HashSet<Action> actions = new HashSet<Action>();
        Boat nav1 = etat.getPartie().getCurrentPlayer().getBoats().get(0);
        Boat nav2 = etat.getPartie().getCurrentPlayer().getBoats().get(1);

        if (false) {// canPlayBoat1
            actions.addAll(getDeplacement(nav1));
            //TODO: tir bateau 1
            if (nav1.getMove() != nav1.getMoveAvailable()) {
                actions.add(new PassTurn(nav1));
            }
        }
        if (false) {// canPlayBoat2
            actions.addAll(getDeplacement(etat.getPartie().getMap().getBateaux1().get(1)));
            //TODO: tir bateau 2
            if (nav2.getMove() != nav2.getMoveAvailable()) {
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