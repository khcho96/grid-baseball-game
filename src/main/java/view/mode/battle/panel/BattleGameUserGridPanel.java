package view.mode.battle.panel;

import dto.SizeDto;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import view.util.ComponentSetter;

public class BattleGameUserGridPanel extends JPanel {

    private List<List<JButton>> gridButtons;
    private boolean IS_CLICKABLE;

    public List<List<JButton>> getButtons() {
        return List.copyOf(gridButtons);
    }

    public void setUserGridComponents(SizeDto size) {
        setButtons(size);
    }

    public void setUserGridPanel(SizeDto size) {
        removeAll();

        ComponentSetter.setPanel(this, 500, 500, 30, 280, new GridLayout(size.size(), size.size()));

        for (int i = 0; i < size.size(); i++) {
            for (int j = 0; j < size.size(); j++) {
                add(gridButtons.get(i).get(j));
            }
        }

        revalidate();
        repaint();
    }

    private void setButtons(SizeDto size) {
        gridButtons = new ArrayList<>();
        for (int i = 0; i < size.size(); i++) {
            gridButtons.add(new ArrayList<>());
            for (int j = 0; j < size.size(); j++) {
                JButton button = new JButton();
                button.setFocusPainted(false);
                button.setFont(new Font("돋움", Font.BOLD, 80 / size.size()));
                gridButtons.get(i).add(button);
            }
        }
    }

    public void disableAllGridButtons() {
        for (List<JButton> gridButton : gridButtons) {
            for (JButton button : gridButton) {
                disableGridButton(button);
            }
        }
    }

    public void disableGridButton(JButton button) {
        for (ActionListener actionListener : button.getActionListeners()) {
            button.removeActionListener(actionListener);
        }
    }

    public boolean isClickable() {
        return IS_CLICKABLE;
    }

    public void setClickable(boolean isClickable) {
        IS_CLICKABLE = isClickable;
    }
}
