package fr.lesprogbretons.seawar.model.actions;

import fr.lesprogbretons.seawar.controller.Controller;
import fr.lesprogbretons.seawar.model.boat.Boat;
import fr.lesprogbretons.seawar.model.cases.Case;

public abstract class Move extends Action {
    protected final Boat boat;
    protected final Case target;

    public Move(Boat boat, Case target) {
        super();
        this.boat = boat;
        this.target = target;
    }

    @Override
    public void apply(Controller state) {
        state.selection(this.getBoat().getPosition().getX(), this.getBoat().getPosition().getY());
        state.selection(this.getTarget().getX(), this.getTarget().getY());
    }

    public Boat getBoat() {

        return boat;
    }

    public Case getTarget() {
        return target;
    }


}
