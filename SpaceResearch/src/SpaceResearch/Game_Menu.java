package SpaceResearch;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;


class Game_Menu extends Menu_Panel {

    Stats stats;

    private JPanel go_panel;

    Menu_Fake_Button status;
    Menu_Fake_Button research;

    Menu_Fake_Button improve_fuel;
    Menu_Fake_Button improve_hold;
    Menu_Fake_Button improve_engine;
    Menu_Fake_Button improve_supply;
    Menu_Fake_Button improve_radio;
    Menu_Fake_Button improve_shell;

    Menu_Fake_Button main_menu;
    Menu_Fake_Button new_mission;
    Menu_Fake_Button save_game;

    Menu_Fake_Button improve_1;
    Menu_Fake_Button improve_2;
    Menu_Fake_Button improve_3;
    Menu_Fake_Button improve_4;

    Menu_Fake_Button go_try_again;
    Menu_Fake_Button go_main_menu;
    Menu_Fake_Button go_exit;

    private Menu_Text_Label price_1, price_2, price_3, price_4;

    private Menu_Text_Label[] statistic_label;

    private Menu_Text_Label level_1, level_2, level_3, level_4, not_affordable, save_success;

    BufferedImage menu_basic;
    BufferedImage menu_frame;
    BufferedImage[] menu_submenu;


    BufferedImage cursor_home;

    private MouseAdapter mouseA;

    boolean paint_game_menu;

    int current_submenu;

    Game_Menu(Stats stats, MouseAdapter mouseA) {

        super();
        this.stats = stats;

        this.mouseA = mouseA;

        menu_submenu = new BufferedImage[8];

        try {
            menu_basic = ImageIO.read(getClass().getResourceAsStream("/Game_basic_page.png"));
            menu_frame = ImageIO.read(getClass().getResourceAsStream("/Game_basic_frame.png"));

            menu_submenu[0] = ImageIO.read(getClass().getResourceAsStream("/Game_submenu_shell.png"));
            menu_submenu[1] = ImageIO.read(getClass().getResourceAsStream("/Game_submenu_load.png"));
            menu_submenu[2] = ImageIO.read(getClass().getResourceAsStream("/Game_submenu_supply.png"));
            menu_submenu[3] = ImageIO.read(getClass().getResourceAsStream("/Game_submenu_fuel.png"));
            menu_submenu[4] = ImageIO.read(getClass().getResourceAsStream("/Game_submenu_speed.png"));
            menu_submenu[5] = ImageIO.read(getClass().getResourceAsStream("/Game_submenu_contact.png"));
            menu_submenu[6] = ImageIO.read(getClass().getResourceAsStream("/Game_submenu_status.png"));
            menu_submenu[7] = ImageIO.read(getClass().getResourceAsStream("/Game_submenu_blanko.png"));

            cursor_home = ImageIO.read(getClass().getResourceAsStream("/Game_rocket_mouse.png"));
        } catch (Exception ignored) {
        }

        paint_game_menu = true;

        setFakeButtons();

        this.current_submenu = 6;

        this.setVisible(true);
    }

    private void setFakeButtons() {
        try {
            this.remove(status);
            this.remove(research);
            this.remove(improve_engine);
            this.remove(improve_fuel);
            this.remove(improve_hold);
            this.remove(improve_radio);
            this.remove(improve_shell);
            this.remove(improve_supply);
            this.remove(main_menu);
            this.remove(new_mission);
            this.remove(save_game);
        } catch (Exception ignored) {
        }

        status = new Menu_Fake_Button(265, 10, 230, 230, mouseA);
        research = new Menu_Fake_Button(1445, 10, 230, 230, mouseA);

        improve_fuel = new Menu_Fake_Button(15, 265, 220, 175, mouseA);
        improve_hold = new Menu_Fake_Button(15, 460, 220, 175, mouseA);
        improve_supply = new Menu_Fake_Button(15, 655, 220, 175, mouseA);

        improve_shell = new Menu_Fake_Button(1705, 265, 220, 175, mouseA);
        improve_engine = new Menu_Fake_Button(1705, 460, 220, 175, mouseA);
        improve_radio = new Menu_Fake_Button(1705, 655, 220, 175, mouseA);

        main_menu = new Menu_Fake_Button(265, 855, 230, 230, mouseA);
        new_mission = new Menu_Fake_Button(525, 855, 900, 225, mouseA);
        save_game = new Menu_Fake_Button(1450, 855, 230, 230, mouseA);

        this.add(research);
        this.add(status);

        this.add(improve_engine);
        this.add(improve_fuel);
        this.add(improve_shell);

        this.add(improve_supply);
        this.add(improve_hold);
        this.add(improve_radio);

        this.add(main_menu);
        this.add(new_mission);
        this.add(save_game);
    }

    void clearSubmenu() {
        removePrices();
        removeStatuses();
    }

    void removePrices() {
        try {
            this.remove(price_1);
            this.remove(price_2);
            this.remove(price_3);
            this.remove(price_4);
            this.remove(level_1);
            this.remove(level_2);
            this.remove(level_3);
            this.remove(level_4);
            this.remove(improve_1);
            this.remove(improve_2);
            this.remove(improve_3);
            this.remove(improve_4);

        } catch (Exception ignored) {
        }
    }

    void setPrices() {
        clearSubmenu();

        level_1 = new Menu_Text_Label("LEVEL " + stats.getLevels()[current_submenu][0][0], 275, 400, 300, 100);
        this.add(level_1);

        level_2 = new Menu_Text_Label("LEVEL " + stats.getLevels()[current_submenu][1][0], 645, 400, 300, 100);
        this.add(level_2);

        level_3 = new Menu_Text_Label("LEVEL " + stats.getLevels()[current_submenu][2][0], 995, 400, 300, 100);
        this.add(level_3);

        level_4 = new Menu_Text_Label("LEVEL " + stats.getLevels()[current_submenu][3][0], 1355, 400, 300, 100);
        this.add(level_4);

        if (stats.getLevels()[current_submenu][0][0] == 0)
            price_1 = new Menu_Text_Label(stats.getLevels()[current_submenu][0][1], stats.getPocket(current_submenu), 275, 500, 300, 100);
        else
            price_1 = new Menu_Text_Label(stats.getLevels()[current_submenu][0][0] * stats.getLevels()[current_submenu][0][1] * stats.getLevels()[current_submenu][0][2], stats.getPocket(current_submenu), 275, 500, 300, 100);
        this.add(price_1);

        if (stats.getLevels()[current_submenu][1][0] == 0)
            price_2 = new Menu_Text_Label(stats.getLevels()[current_submenu][1][1], stats.getPocket(current_submenu), 645, 500, 300, 100);
        else
            price_2 = new Menu_Text_Label(stats.getLevels()[current_submenu][1][0] * stats.getLevels()[current_submenu][1][1] * stats.getLevels()[current_submenu][1][2], stats.getPocket(current_submenu), 645, 500, 300, 100);
        this.add(price_2);

        if (stats.getLevels()[current_submenu][2][0] == 0)
            price_3 = new Menu_Text_Label(stats.getLevels()[current_submenu][2][1], stats.getPocket(current_submenu), 995, 500, 300, 100);
        else
            price_3 = new Menu_Text_Label(stats.getLevels()[current_submenu][2][0] * stats.getLevels()[current_submenu][2][1] * stats.getLevels()[current_submenu][2][2], stats.getPocket(current_submenu), 995, 500, 300, 100);
        this.add(price_3);

        if (stats.getLevels()[current_submenu][3][0] == 0)
            price_4 = new Menu_Text_Label(stats.getLevels()[current_submenu][3][1], stats.getPocket(current_submenu), 1355, 500, 300, 100);
        else
            price_4 = new Menu_Text_Label(stats.getLevels()[current_submenu][3][0] * stats.getLevels()[current_submenu][3][1] * stats.getLevels()[current_submenu][3][2], stats.getPocket(current_submenu), 1355, 500, 300, 100);
        this.add(price_4);

        improve_1 = new Menu_Fake_Button(329, 680, 213, 90, mouseA);
        this.add(improve_1);
        improve_2 = new Menu_Fake_Button(683, 680, 213, 90, mouseA);
        this.add(improve_2);
        improve_3 = new Menu_Fake_Button(1041, 680, 213, 90, mouseA);
        this.add(improve_3);
        improve_4 = new Menu_Fake_Button(1396, 680, 213, 90, mouseA);
        this.add(improve_4);
    }

    void check_affordable(boolean affordable) {
        if (!affordable) {
            add_not_affordable();

            Timer timer = new Timer(2000, e -> remove_not_affordable());

            timer.start();
            timer.setRepeats(false);
        }
    }

    private void add_not_affordable() {
        try {
            this.remove(not_affordable);
        } catch (Exception ignored) {
        }
        not_affordable = new Menu_Text_Label(new ImageIcon("Game_not_affordable.png"), 780, 450, 380, 200);
        this.add(not_affordable);
    }

    private void remove_not_affordable() {
        try {
            this.remove(not_affordable);
        } catch (Exception ignored) {
        }
    }

    void game_saved(boolean success) {
        add_save_success(success);

        Timer timer = new Timer(2000, e -> remove_save_success());

        timer.start();
        timer.setRepeats(false);
    }

    private void add_save_success(boolean success) {
        if (success) {
            try{
            ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Game_save_success.png")));
            save_success = new Menu_Text_Label(icon, 780, 450, 380, 200);
            }
            catch(Exception ss){
                System.out.println("Fehler save_success");
            }
        }
        else {
            try {
                ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Game_save_failed.png")));
                save_success = new Menu_Text_Label(icon, 780, 450, 380, 200);
            } catch (Exception ss) {
                System.out.println("Fehler save_failed");
            }
        }
            this.add(save_success);
    }

    private void remove_save_success() {
        try {
            this.remove(save_success);
        } catch (Exception ignored) {
        }
    }

    void setStatuses() {
        clearSubmenu();

        statistic_label = new Menu_Text_Label[10];

        statistic_label[0] = new Menu_Text_Label(stats.getArchivment_x(0), 670, 420, 250, 32);
        this.add(statistic_label[0]);

        statistic_label[1] = new Menu_Text_Label(stats.getArchivment_x(1), 670, 492, 250, 32);
        this.add(statistic_label[1]);

        statistic_label[2] = new Menu_Text_Label(stats.getArchivment_x(2), 670, 565, 250, 32);
        this.add(statistic_label[2]);

        statistic_label[3] = new Menu_Text_Label(stats.getArchivment_x(3), 670, 632, 250, 32);
        this.add(statistic_label[3]);

        statistic_label[4] = new Menu_Text_Label(stats.getArchivment_x(4), 670, 700, 250, 32);
        this.add(statistic_label[4]);

        statistic_label[5] = new Menu_Text_Label(stats.getAchieveText(5), 1287, 445, 355, 32);
        this.add(statistic_label[5]);

        statistic_label[6] = new Menu_Text_Label(stats.getAchieveText(6), 1287, 500, 355, 32);
        this.add(statistic_label[6]);

        statistic_label[7] = new Menu_Text_Label(stats.getAchieveText(7), 1287, 553, 355, 32);
        this.add(statistic_label[7]);

        statistic_label[8] = new Menu_Text_Label(stats.getAchieveText(8), 1287, 609, 355, 32);
        this.add(statistic_label[8]);

        statistic_label[9] = new Menu_Text_Label(stats.getRankLevel(), 1285, 708, 360, 32);
        this.add(statistic_label[9]);
    }

    private void removeStatuses() {
        try {
            for (int i = 0; i < 10; i++) {
                this.remove(statistic_label[i]);
            }
        } catch (Exception ignored) {
        }
    }

    JPanel setGame_over() {
        go_panel = new JPanel(null);
        go_panel.setBackground(new Color(0, 0, 0, 0));
        go_panel.setBounds((int) ((double) GUI.screenwidth - 1000. * GUI.sizefactor_x) / 2, (int) ((double) GUI.screenheight - 800. * GUI.sizefactor_y) / 2, (int) (1000. * GUI.sizefactor_x), (int) (600. * GUI.sizefactor_y));

        go_try_again = new Menu_Fake_Button(490, 210, 160, 160, mouseA);
        go_panel.add(go_try_again);

        go_main_menu = new Menu_Fake_Button(89, 138, 207, 130, mouseA);
        go_panel.add(go_main_menu);

        go_exit = new Menu_Fake_Button(90, 345, 207, 130, mouseA);
        go_panel.add(go_exit);

        go_panel.setVisible(true);

        return go_panel;
    }

    void resizeGame_over(Game_Paint panel) {
        panel.remove(go_panel);
        panel.add(setGame_over());

    }

    void resizemenu() {
        this.setBounds(0, 0, GUI.screenwidth, GUI.screenheight - 1);

        setFakeButtons();

        switch (current_submenu) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                setPrices();
                break;
            case 7:
                clearSubmenu();
                break;
            case 6:
                setStatuses();
                break;
        }
    }
}




