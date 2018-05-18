package fr.lesprogbretons.seawar.model.actions;

import fr.lesprogbretons.seawar.controller.Controller;

public abstract class Action {

    public abstract Object clone();

    public abstract void apply(Controller state);

    public abstract String toString();

}
