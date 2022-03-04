import java.awt.event.*;

/**
 * Event listener for GUI.
 */
public class EventListener extends MouseAdapter implements ActionListener {

    private final AppController appController;

    /**
     * Constructor for EventListener
     * @param appController Main controller and overhead that controls the app
     */
    public EventListener(AppController appController){
        this.appController = appController;
    }
    @Override
    public void actionPerformed(ActionEvent event){
        if(event.getSource() == appController.gui.searchButton){
            appController.searchButtonPressed();;
        }
        if(event.getSource() == appController.gui.refreshButton){
            appController.refreshButtonPressed();;
        }


    }
}
    

