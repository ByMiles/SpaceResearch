package SpaceResearch;

import javax.swing.*;
import java.awt.*;

class Menu_Text_Label extends JLabel {
    Menu_Text_Label(int price, int pocket, double x, double y, double width, double height) {
        this.setFont(Saving_Handler.ARDESTINE());
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setBounds((int) (x * GUI.sizefactor_x), (int) (y * GUI.sizefactor_y), (int) (width * GUI.sizefactor_x), (int) (height * GUI.sizefactor_y));
        this.set_Text(price, pocket);
    }

    Menu_Text_Label(String content, double x, double y, double width, double height) {
        this.setFont(Saving_Handler.ARDESTINE());
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setBounds((int) (x * GUI.sizefactor_x), (int) (y * GUI.sizefactor_y), (int) (width * GUI.sizefactor_x), (int) (height * GUI.sizefactor_y));
        this.setForeground(new Color(0, 0, 0));
        this.set_Text(content);
    }

    Menu_Text_Label(ImageIcon icon, double x, double y, double width, double height) {
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setBounds((int) (x * GUI.sizefactor_x), (int) (y * GUI.sizefactor_y), (int) (width * GUI.sizefactor_x), (int) (height * GUI.sizefactor_y));
        this.setIcon(icon);
    }

    private void unaffordable() {
        this.setForeground(new Color(100, 100, 100, 200));
    }

    private void affordable() {
        this.setForeground(new Color(0, 0, 0));
    }

    private void set_Text(int price, int pocket) {
        this.setText("<html><body><center>" + price + "<br> (" + pocket + ")</center></html></body>");
        if (price <= pocket)
            affordable();
        else
            unaffordable();
    }

    private void set_Text(String content) {
        this.setText(content);
    }
}
