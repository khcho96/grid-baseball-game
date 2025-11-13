package view.init;

import communicator.EventCommunicator;
import javax.swing.JButton;

public class InitEventSetter {

    private final EventCommunicator eventCommunicator;
    private final JButton singleButton;
    private final JButton battleButton;

    public InitEventSetter(EventCommunicator eventCommunicator, JButton singleButton, JButton battleButton) {
        this.eventCommunicator = eventCommunicator;
        this.singleButton = singleButton;
        this.battleButton = battleButton;
    }

    public void setEvents() {
        singleButton.addActionListener(e -> {
            eventCommunicator.selectSingleGame();
        });

        battleButton.addActionListener(e -> {
            eventCommunicator.selectBattleGame();
        });
    }
}
