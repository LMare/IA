package fr.lesprogbretons.seawar.model.actions;


import fr.lesprogbretons.seawar.controller.Controller;
import fr.lesprogbretons.seawar.model.boat.Boat;
import fr.lesprogbretons.seawar.model.cases.Case;

import static fr.lesprogbretons.seawar.SeaWar.logger;

// very bad programming way in my o

public class MoveBoat extends Move {


    public MoveBoat(Boat boat, Case target) {
        super(boat, target);

    }

    @Override
    public Object clone() {
        MoveBoat moveOnBoat = new MoveBoat(this.getBoat(), this.getTarget());
        return moveOnBoat;
    }

    @Override
    public void apply(Controller controller) {
        super.apply(controller);
        //On se déplace
        //partie.unselectBateau();
        logger.debug("Action déplacement déplacement : " + this.getTarget().getX() + ";" + this.getTarget().getY());
    }


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Move the boat";
    }


}
