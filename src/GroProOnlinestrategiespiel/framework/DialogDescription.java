package GroProOnlinestrategiespiel.framework;

import GroProOnlinestrategiespiel.Demo;
import GroProOnlinestrategiespiel.graph.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is concert component which get default
 * implementation for the operation {@link #way(Field)}
 * and provides default operation for{@link #render()}.
 * @author Montahaee
 * @version 1.0
 * @created 05-August-2022 10:37:40 PM
 */

public class DialogDescription extends JFrame implements Description {

    private final JTextField dir;
    private final JTextField filename;

    public DialogDescription() {
        this.dir = new JTextField();
        this.filename = new JTextField();
        JButton open = new JButton("Open");
        JButton save = new JButton("Save");
        JPanel p = new JPanel();
        open.addActionListener(new OpenL());
        p.add(open);
        save.addActionListener(new SaveL());
        p.add(save);
        Container cp = getContentPane();
        cp.add(p, BorderLayout.SOUTH);
        dir.setEditable(false);
        filename.setEditable(false);
        p = new JPanel();
        p.setLayout(new GridLayout(2, 1));
        p.add(filename);
        p.add(dir);
        cp.add(p, BorderLayout.NORTH);
    }

    private class OpenL implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JFileChooser c = new JFileChooser();
            // Demonstrate "Open" dialog:
            int rVal = c.showOpenDialog(DialogDescription.this);
          if (rVal == JFileChooser.APPROVE_OPTION) {
                filename.setText(c.getSelectedFile().getName());
                dir.setText(c.getCurrentDirectory().toString());
            }
          if (rVal == JFileChooser.CANCEL_OPTION) {
                filename.setText("You pressed cancel");
                dir.setText("");
            }
        }
    }

    private class SaveL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser c = new JFileChooser();
            // Demonstrate "Save" dialog:
            int rVal = c.showSaveDialog(DialogDescription.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                filename.setText(c.getSelectedFile().getName());
                dir.setText(c.getCurrentDirectory().toString());
            }
            if (rVal == JFileChooser.CANCEL_OPTION) {
                filename.setText("You pressed cancel");
                dir.setText("");
            }
        }
    }



    /**
     * To represent {@link #way(Field)}
     * in visual form on a display{@link #<Demo>}.
     */
    @Override
    public void render() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250, 110);
        setVisible(true);
    }

///    public static void run(JFrame frame, int width, int height) {
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(width, height);
//        frame.setVisible(true);
//    }
}
