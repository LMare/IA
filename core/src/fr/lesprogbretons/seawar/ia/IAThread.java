package fr.lesprogbretons.seawar.ia;

import fr.lesprogbretons.seawar.controller.Controller;
import fr.lesprogbretons.seawar.model.Partie;
import fr.lesprogbretons.seawar.model.actions.Action;
import fr.lesprogbretons.seawar.model.boat.Boat;

import java.util.List;
import java.util.concurrent.ExecutorService;

import static fr.lesprogbretons.seawar.SeaWar.logger;

public class IAThread extends Thread {


    private AbstractIA ia;

    private ExecutorService executorService;
    private Controller controller;

    private Action choosedAction;


    public IAThread(AbstractIA ia, Controller controller, ExecutorService executorService) {
        super("Calcul");
        setName("Calcul");
        this.ia = ia;
        this.executorService = executorService;
        this.controller = (Controller) controller.clone();
        this.choosedAction = null;

    }

    public Action getActionChoice() {
        return choosedAction;

    }


    public void run() {
        while (true) {
            try {
                // action choisie
                Partie myPartie = controller.getPartie();

                List<Boat> boats;
                if (ia.getNumber() == 2) {
                    boats = myPartie.getMap().getBateaux2();

                } else {
                    boats = myPartie.getMap().getBateaux1();
                }

                int cantPlay = 0;

//                for (Boat boat : boats) {
//                    if (boat.getMoveAvailable() == 0 || myPartie.getMap().getCasesDisponibles(boat.getPosition(), 1).isEmpty())
//                        cantPlay++;
//                }

//                if (cantPlay == boats.size()) {
//                    seaWarController.endTurn();
//                }

                if (!myPartie.isAnyBateauSelectionne()) {
                    int index = 0;
                    Boat boat = boats.get(index);

                    do {
                        myPartie.setBateauSelectionne(boat);
                        this.choosedAction = ia.chooseAction(controller);
                        myPartie.unselectBateau();
                        index += 1;

                    } while (myPartie.getMap().getCasesDisponibles(boat.getPosition(), 1).isEmpty()
                            || boat.getMoveAvailable() == 0);
                } else {

                }

            } catch (Exception ex) {
                ex.printStackTrace();
                logger.error("Erreur choix action", ex);
//                Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, "Erreur choix action", ex);
            } finally {

                executorService.shutdown();

            }

        }
    }


}
