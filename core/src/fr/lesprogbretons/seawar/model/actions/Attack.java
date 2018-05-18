package fr.lesprogbretons.seawar.model.actions;

import fr.lesprogbretons.seawar.controller.Controller;
import fr.lesprogbretons.seawar.model.boat.Boat;
import fr.lesprogbretons.seawar.model.cases.Case;

import static fr.lesprogbretons.seawar.SeaWar.logger;

public class Attack extends Move {


    public Attack(Boat boat, Case target) {
        super(boat, target);
    }

    @Override
    public Object clone() {
        return new Attack(this.getBoat(), this.getTarget());
    }

    @Override
    public void apply(Controller controller) {
        super.apply(controller);
        // partie.unselectBateau();
        logger.debug("Action attaque : " + this.getTarget().getX() + ";" + this.getTarget().getY());


    }

    @Override
    public String toString() {
        return "Shooting the enemy...";
    }

}
