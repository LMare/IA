package fr.lesprogbretons.seawar.ia;


import fr.lesprogbretons.seawar.controller.Controller;
import fr.lesprogbretons.seawar.model.Partie;
import fr.lesprogbretons.seawar.model.Player;
import fr.lesprogbretons.seawar.model.actions.Action;
import fr.lesprogbretons.seawar.model.boat.Boat;

import java.util.List;


public abstract class AbstractIA extends Player {

    public static int TIME_TO_THINK = 1000;
    private Action memorisedAction;


    public AbstractIA(int number) {
        super(number);
        this.memorisedAction = null;
    }


    public AbstractIA(int number, String name) {
        super(number, name);
    }

    public AbstractIA(int number, String name, List<Boat> boats) {
        super(number, name, boats);

    }

    public final Action getMemorizedAction() {
        return memorisedAction;
    }


    public final void memoriseAction(Action action) {

        if (action != null) {

            memorisedAction = (Action) action.clone();

        } else {

            memorisedAction = action;

        }


    }

    public abstract Action chooseAction(Controller controller);

}