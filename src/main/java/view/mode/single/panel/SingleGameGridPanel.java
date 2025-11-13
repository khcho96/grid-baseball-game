package view.mode.single.panel;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import dto.SizeDto;

public class SingleGameGridPanel extends JPanel {

    private List<List<JButton>> gridButtons;

    public List<List<JButton>> getButtons() {
        return List.copyOf(gridButtons);
    }

    public void setGridComponents(SizeDto size) {
        setButtons(size);
    }

    public void setGridPanel(SizeDto size) {
        removeAll();

        setSize(600, 600);
        setLocation(700, 100);
        setLayout(new GridLayout(size.size(), size.size()));

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
                button.setFocusPainted(false); // 포커스 하이라이트 숨김
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
}
