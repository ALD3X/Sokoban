package vueGraphique;

import javax.swing.*;
import java.awt.event.*;

public class FinPartieDialog extends JDialog {
    private JFrame parentFrame;

    public FinPartieDialog(JFrame parent, String message) {
        super(parent, "Fin de partie", true);
        this.parentFrame = parent;

        JLabel label = new JLabel(message);
        getContentPane().add(label);

        JButton boutonOk = new JButton("OK");
        boutonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentFrame.dispose();
                setVisible(false);
            }
        });
        
        getContentPane().add(boutonOk, "South");
        setSize(300, 150);
        setLocationRelativeTo(parent);
    }
}
