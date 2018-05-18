package fr.lesprogbretons.seawar.ia;

import fr.lesprogbretons.seawar.controller.Controller;
import fr.lesprogbretons.seawar.model.Partie;
import fr.lesprogbretons.seawar.model.actions.Action;
import fr.lesprogbretons.seawar.model.boat.Boat;

import java.util.ArrayList;
import java.util.Random;


public class IAAleatoire extends AbstractIA {

    public IAAleatoire(int identifier) {
        super(identifier, "IAAleatoire");
    }


    public IAAleatoire(int identifier, String name, ArrayList<Boat> boats) {
        super(identifier, name, boats);
    }


    @Override
    public Action chooseAction(Controller controller) {
        Random rnd = new Random();
        int indexAct = rnd.nextInt(controller.getPossibleActions().size());
        return controller.getPossibleActions().get(indexAct);


    }


}
