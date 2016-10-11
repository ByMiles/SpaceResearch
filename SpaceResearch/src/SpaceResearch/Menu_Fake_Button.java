package SpaceResearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

class Menu_Fake_Button extends JPanel {

    boolean entered;
    boolean pressed;

    Menu_Fake_Button(double x, double y, double width, double height, MouseAdapter mouseA) {
        this.setBackground(new Color(100, 100, 100, 0));
        this.setBounds((int) (x * GUI.sizefactor_x), (int) (y * GUI.sizefactor_y), (int) (width * GUI.sizefactor_x), (int) (height * GUI.sizefactor_y));

        this.addMouseListener(mouseA);

        entered = pressed = false;
    }

    void check() {
        if (entered && pressed)
            this.setBackground(new Color(0, 150, 150, 100));
        else
            this.setBackground(new Color(0, 0, 0, 0));
    }
}
