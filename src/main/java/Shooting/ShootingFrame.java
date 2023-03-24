package Shooting;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ShootingFrame extends JFrame {
    public ShootingPanel panel;

    public ShootingFrame() {

        panel = new ShootingPanel();
        this.addKeyListener(new Keyboard());

        this.add(panel);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                Shooting.loop = false;
            }
        });
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Shooting");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true); // ウィンドウの表示　非表示決めます
    }

}
