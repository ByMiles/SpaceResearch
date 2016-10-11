package SpaceResearch;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;


class Main_Menu {

    BufferedImage main_pic, new_pic, load_pic, options_pic;
    BufferedImage[] tutorial_pic;

    BufferedImage space_1, earth;

    Menu_Panel start_panel, new_panel, load_panel, tutorial_panel, options_panel;

    Menu_Fake_Button new_game, load_game, options, tutorial, back, t_next, t_prev;

    Menu_Fake_Button[] stat_slot;

    JLabel[] name_label;

    private MouseAdapter mouseA;

    double[] earth_scales;
    int tutorial_current;

    boolean start_page, new_page, load_page, tutorial_page, option_page;

    Main_Menu(MouseAdapter mouseA) {

        try{
            main_pic = ImageIO.read(getClass().getResourceAsStream("/Main_start_page.png"));
        }catch (Exception s){
            System.out.println("nicht mal dieses");}


        try {

            new_pic = ImageIO.read(getClass().getResourceAsStream("/Main_new_page.png"));
            load_pic = ImageIO.read(getClass().getResourceAsStream("/Main_load_page.png"));
            options_pic = ImageIO.read(getClass().getResourceAsStream("/Main_options_page.png"));
            space_1 = ImageIO.read(getClass().getResourceAsStream("/Space_space.png"));
            earth = ImageIO.read(getClass().getResourceAsStream("/Space_planet_home.png"));

            tutorial_pic = new BufferedImage[4];
            for (int i = 0; i < tutorial_pic.length; i++) {
                String filename = "/Main_tutorial_" + i + ".png";
                tutorial_pic[i] = ImageIO.read(getClass().getResourceAsStream(filename));
            }
        } catch (Exception e) {
            System.out.println("Menu-Bilder konnten nicht geladen werden.");
        }
        tutorial_current = 0;

        name_label = new JLabel[10];

        this.mouseA = mouseA;

        start_page = true;
        new_page = false;
        load_page = false;
        tutorial_page = false;
    }

    private void reset_earth_scales() {
        earth_scales = new double[]{0.5, 0.5, 0.0};
    }

    JPanel set_start_panel() {

        setCurrent_page(true, false, false, false, false);

        reset_earth_scales();

        start_panel = new Menu_Panel();

        new_game = new Menu_Fake_Button(115, 387, 364, 218, mouseA);
        start_panel.add(new_game);

        load_game = new Menu_Fake_Button(115, 736, 364, 218, mouseA);
        start_panel.add(load_game);

        tutorial = new Menu_Fake_Button(1455, 387, 364, 218, mouseA);
        start_panel.add(tutorial);

        options = new Menu_Fake_Button(1455, 736, 364, 218, mouseA);
        start_panel.add(options);

        return start_panel;
    }

    JPanel set_new_panel() {

        setCurrent_page(false, true, false, false, false);

        new_panel = new Menu_Panel();

        createStat_slots(new_panel);

        back = new Menu_Fake_Button(795, 815, 345, 175, mouseA);
        new_panel.add(back);

        return new_panel;
    }

    JPanel set_load_panel() {

        setCurrent_page(false, false, true, false, false);

        load_panel = new Menu_Panel();

        createStat_slots(load_panel);
        back = new Menu_Fake_Button(795, 815, 345, 175, mouseA);
        load_panel.add(back);

        return load_panel;
    }

    JPanel set_tutorial_panel() {

        setCurrent_page(false, false, false, true, false);

        tutorial_panel = new Menu_Panel();

        t_prev = new Menu_Fake_Button(222, 823, 364, 218, mouseA);
        tutorial_panel.add(t_prev);

        back = new Menu_Fake_Button(785, 823, 364, 218, mouseA);
        tutorial_panel.add(back);

        t_next = new Menu_Fake_Button(1346, 823, 364, 218, mouseA);
        tutorial_panel.add(t_next);

        return tutorial_panel;
    }

    JPanel set_options_panel() {
        setCurrent_page(false, false, false, false, true);

        options_panel = new Menu_Panel();

        back = new Menu_Fake_Button(785, 823, 364, 218, mouseA);
        options_panel.add(back);

        JCheckBox cb_all = new JCheckBox("MUTE ALL SOUNDS (SHORT-KEY S)", !Saving_Handler.played_sounds[0]);
        cb_all.setFont(Saving_Handler.ARDESTINE());
        cb_all.addItemListener(e -> Saving_Handler.played_sounds[0] = !(e.getStateChange() == ItemEvent.SELECTED));
        cb_all.setBackground(new Color(0, 0, 0, 0));
        JCheckBox cb_mission = new JCheckBox("MUTE MISSION-START SOUND", !Saving_Handler.played_sounds[1]);
        cb_mission.setFont(Saving_Handler.ARDESTINE());
        cb_mission.addItemListener(e -> Saving_Handler.played_sounds[1] = !(e.getStateChange() == ItemEvent.SELECTED));
        cb_mission.setBackground(new Color(0, 0, 0, 0));
        JCheckBox cb_collect = new JCheckBox("MUTE COLLECTING-STUFF SOUND", !Saving_Handler.played_sounds[2]);
        cb_collect.setFont(Saving_Handler.ARDESTINE());
        cb_collect.addItemListener(e -> Saving_Handler.played_sounds[2] = !(e.getStateChange() == ItemEvent.SELECTED));
        cb_collect.setBackground(new Color(0, 0, 0, 0));
        JCheckBox cb_hit = new JCheckBox("MUTE HITTEN-BY-ROCK SOUND", !Saving_Handler.played_sounds[3]);
        cb_hit.setFont(Saving_Handler.ARDESTINE());
        cb_hit.addItemListener(e -> Saving_Handler.played_sounds[3] = !(e.getStateChange() == ItemEvent.SELECTED));
        cb_hit.setBackground(new Color(0, 0, 0, 0));
        JCheckBox cb_speed = new JCheckBox("MUTE SPEED-UP SOUND", !Saving_Handler.played_sounds[4]);
        cb_speed.setFont(Saving_Handler.ARDESTINE());
        cb_speed.addItemListener(e -> Saving_Handler.played_sounds[4] = !(e.getStateChange() == ItemEvent.SELECTED));
        cb_speed.setBackground(new Color(0, 0, 0, 0));
        JCheckBox cb_slow = new JCheckBox("MUTE SLOW-DOWN SOUND", !Saving_Handler.played_sounds[5]);
        cb_slow.setFont(Saving_Handler.ARDESTINE());
        cb_slow.addItemListener(e -> Saving_Handler.played_sounds[5] = !(e.getStateChange() == ItemEvent.SELECTED));
        cb_slow.setBackground(new Color(0, 0, 0, 0));

        JPanel sound_label = new JPanel(new GridLayout(6, 1));
        sound_label.setBackground(new Color(0, 0, 0, 0));
        sound_label.setBounds((int) (550. * GUI.sizefactor_x), (int) (225. * GUI.sizefactor_y), (int) (830. * GUI.sizefactor_x), (int) (525. * GUI.sizefactor_y));
        sound_label.add(cb_all);
        sound_label.add(cb_mission);
        sound_label.add(cb_collect);
        sound_label.add(cb_hit);
        sound_label.add(cb_speed);
        sound_label.add(cb_slow);

        sound_label.setVisible(true);

        options_panel.add(sound_label);

        return options_panel;
    }

    private void createStat_slots(Menu_Panel panel) {
        stat_slot = new Menu_Fake_Button[10];

        stat_slot[0] = new Menu_Fake_Button(130, 440, 285, 135, mouseA);
        stat_slot[1] = new Menu_Fake_Button(475, 440, 285, 135, mouseA);
        stat_slot[2] = new Menu_Fake_Button(823, 440, 285, 135, mouseA);
        stat_slot[3] = new Menu_Fake_Button(1172, 440, 285, 135, mouseA);
        stat_slot[4] = new Menu_Fake_Button(1517, 440, 285, 135, mouseA);
        stat_slot[5] = new Menu_Fake_Button(130, 615, 285, 135, mouseA);
        stat_slot[6] = new Menu_Fake_Button(475, 615, 285, 135, mouseA);
        stat_slot[7] = new Menu_Fake_Button(823, 615, 285, 135, mouseA);
        stat_slot[8] = new Menu_Fake_Button(1172, 615, 285, 135, mouseA);
        stat_slot[9] = new Menu_Fake_Button(1517, 615, 285, 135, mouseA);

        name_label = new JLabel[10];
        for (int i = 0; i < 10; i++) {
            name_label[i] = new JLabel(Saving_Handler.catch_name(i + 1));
            name_label[i].setVerticalAlignment(JLabel.CENTER);
            name_label[i].setHorizontalAlignment(JLabel.CENTER);
            name_label[i].setFont(Saving_Handler.ARDESTINE());
            name_label[i].setForeground(new Color(0, 0, 0));
            name_label[i].addMouseListener(mouseA);
            stat_slot[i].add(name_label[i]);
            panel.add(stat_slot[i]);
        }
    }

    private void setCurrent_page(boolean startpage, boolean new_page, boolean load_page, boolean tutorial_page, boolean option_page) {

        this.start_page = startpage;
        this.new_page = new_page;
        this.load_page = load_page;
        this.tutorial_page = tutorial_page;
        this.option_page = option_page;
    }
}
