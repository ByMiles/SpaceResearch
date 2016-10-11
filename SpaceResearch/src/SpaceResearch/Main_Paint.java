package SpaceResearch;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;


class Main_Paint extends JLabel {
    private Main_Menu main_menu;

    private int width;
    private int height;

    Main_Paint(Main_Menu main_menu) {
        this.width = GUI.screenwidth;
        this.height = GUI.screenheight;
        this.main_menu = main_menu;
        this.setBounds(0, 0, GUI.screenwidth, GUI.screenheight);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (main_menu.start_page) {
            AffineTransform st = new AffineTransform();
            st.translate(width / 2, height * 1.15 / 2);
            st.scale(2, 2);
            st.rotate(main_menu.earth_scales[2]);
            st.translate(-main_menu.space_1.getWidth() / 2, -main_menu.space_1.getHeight() / 2);
            g2d.drawImage(main_menu.space_1, st, null);


            AffineTransform et = new AffineTransform();
            et.translate(width / 2, height * 1.15 / 2);
            et.scale(main_menu.earth_scales[0], main_menu.earth_scales[1]);
            et.rotate(main_menu.earth_scales[2] * 2);
            et.translate(-main_menu.earth.getWidth() / 1.5, -main_menu.earth.getHeight() / 1.5);
            g2d.drawImage(main_menu.earth, et, null);
            main_menu.earth_scales[2] += 0.002;

            g.drawImage(main_menu.main_pic, 0, 0, width, height, null);
        }

        if (main_menu.new_page) {
            g.drawImage(main_menu.new_pic, 0, 0, width, height, null);
        }

        if (main_menu.load_page) {
            g.drawImage(main_menu.load_pic, 0, 0, width, height, null);
        }
        if (main_menu.tutorial_page) {
            g.drawImage(main_menu.tutorial_pic[main_menu.tutorial_current], 0, 0, width, height, null);
        }
        if (main_menu.option_page) {
            g.drawImage(main_menu.options_pic, 0, 0, width, height, null);
        }


        repaint();
    }

    void resizepaint() {
        this.width = GUI.screenwidth;
        this.height = GUI.screenheight;
        this.setBounds(0, 0, this.width, this.height);

    }
}
