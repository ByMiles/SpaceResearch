package SpaceResearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class GUI extends JFrame {

    Game_Panel game_panel;
    Main_Panel main_panel;
    static int screenwidth, screenheight;
    static double sizefactor_x, sizefactor_y;

    boolean active_main, active_game;


    GUI() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenwidth = (int) screenSize.getWidth();
        screenheight = (int) screenSize.getHeight();

        setSizefactors();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setSize(screenSize);
        this.setLayout(null);
        this.hasFocus();
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                    escape();
                if (e.getKeyCode() == KeyEvent.VK_S)
                    Saving_Handler.played_sounds[0] = !Saving_Handler.played_sounds[0];
            }

        });

        WindowStateListener wl = e ->
        {
            if (e.getNewState() == MAXIMIZED_BOTH) {
                dispose();
                setUndecorated(true);
                setVisible(true);
            }
        };

        Saving_Handler.load_options();
        main_menu();

        this.addWindowStateListener(wl);
        new Thread(new Thread_SizeObserver(this)).start();

        this.setLocationRelativeTo(null);


    }

    void game_menu(Stats stats) {
        try {
            this.remove(main_panel);
        } catch (Exception ignored) {
        }
        game_panel = new Game_Panel(this, stats);
        this.setContentPane(game_panel);
        this.pack();


        active_game = true;
        active_main = false;

        game_panel.change_to_home();

        this.addKeyListener(game_panel.keyEvent);

        this.setVisible(true);
    }

    void main_menu() {
        try {
            this.remove(game_panel);
        } catch (Exception ignored) {
        }
        main_panel = new Main_Panel(this);
        this.setContentPane(main_panel);
        this.pack();

        active_main = true;
        active_game = false;

        main_panel.change_to_start();

        this.setVisible(true);
    }

    private void escape() {
        JDialog escape_dialog = new JDialog(this);
        escape_dialog.setLayout(new GridLayout(4, 1));
        escape_dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        escape_dialog.setModal(true);


        JLabel escape_label = new JLabel("Action:");
        escape_label.setVerticalAlignment(JLabel.CENTER);
        escape_label.setHorizontalAlignment(JLabel.CENTER);
        escape_label.setFont(new Font("Serif", Font.BOLD, 32));
        escape_dialog.add(escape_label);


        JButton escape_normal = new JButton("Exit Fullscreen");
        escape_normal.setFont(new Font("Serif", Font.BOLD, 32));
        escape_normal.addActionListener(e -> {
            normalize();
            escape_dialog.setVisible(false);
        });
        escape_dialog.add(escape_normal);

        JButton escape_exit = new JButton("QUIT");
        escape_exit.setFont(new Font("Serif", Font.BOLD, 32));
        escape_exit.addActionListener(e -> {
            escape_dialog.setVisible(false);
            System.exit(0);
        });
        escape_dialog.add(escape_exit);

        JButton escape_resume = new JButton("Resume");
        escape_resume.setFont(new Font("Serif", Font.BOLD, 32));
        escape_resume.addActionListener(e -> escape_dialog.setVisible(false));


        escape_dialog.add(escape_resume);

        escape_dialog.pack();
        escape_dialog.setLocationRelativeTo(null);
        escape_dialog.setVisible(true);

    }


    private void normalize() {
        this.dispose();
        this.setUndecorated(false);
        this.setExtendedState(Frame.NORMAL);
        this.setVisible(true);
    }

    static void setSizefactors() {
        sizefactor_x = (double) GUI.screenwidth / 1938.;
        sizefactor_y = (double) GUI.screenheight / 1098.;
    }
}
