package fr.lesprogbretons.seawar.model.actions;

import fr.lesprogbretons.seawar.controller.Controller;
import fr.lesprogbretons.seawar.model.Partie;
import fr.lesprogbretons.seawar.model.boat.Boat;

import static fr.lesprogbretons.seawar.SeaWar.logger;

public class PassTurn extends Move {
    private Partie partie;
    private Boat boat;

    public PassTurn(Boat boat) {
        super(boat, boat.getPosition());


    }

    @Override
    public Object clone() {
        return new PassTurn(this.getBoat());
    }

    @Override
    public void apply(Controller controller) {
        //Will be done
//        controller.getPartie().endTurn();
        // partie.unselectBateau();
        logger.debug("Pass turn action");
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Prefer to withdrawing, it better ";
    }

}
