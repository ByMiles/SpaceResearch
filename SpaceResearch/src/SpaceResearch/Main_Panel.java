package SpaceResearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Main_Panel extends JPanel {
    private Main_Paint main_paint;
    private Main_Menu main_menu;
    private GUI gui;

    Main_Panel(GUI gui) {
        this.gui = gui;

        this.setLayout(null);
        this.setPreferredSize(new Dimension(GUI.screenwidth, GUI.screenheight));

        MouseAdapter mouseA = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getSource() == main_menu.new_game) {
                    main_menu.new_game.pressed = true;
                    main_menu.new_game.check();
                }
                if (e.getSource() == main_menu.load_game) {
                    main_menu.load_game.pressed = true;
                    main_menu.load_game.check();
                }
                if (e.getSource() == main_menu.tutorial) {
                    main_menu.tutorial.pressed = true;
                    main_menu.tutorial.check();
                }
                if (e.getSource() == main_menu.options) {
                    main_menu.options.pressed = true;
                    main_menu.options.check();
                }
                if (e.getSource() == main_menu.back) {
                    main_menu.back.pressed = true;
                    main_menu.back.check();
                }
                if (e.getSource() == main_menu.t_prev) {
                    main_menu.t_prev.pressed = true;
                    main_menu.t_prev.check();
                }
                if (e.getSource() == main_menu.t_next) {
                    main_menu.t_next.pressed = true;
                    main_menu.t_next.check();
                }

                for (int i = 0; i < 10; i++) {
                    if (e.getSource() == main_menu.name_label[i] && main_menu.load_page && !main_menu.name_label[i].getText().equals("<html><body><center>EMPTY<br>EMPTY</center></body></html>")) {
                        main_menu.stat_slot[i].pressed = true;
                        main_menu.stat_slot[i].check();
                    }

                    if (e.getSource() == main_menu.name_label[i] && main_menu.new_page) {
                        main_menu.stat_slot[i].pressed = true;
                        main_menu.stat_slot[i].check();
                    }
                }


            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getSource() == main_menu.new_game && main_menu.new_game.entered) {
                    change_to_new();
                }
                if (e.getSource() == main_menu.new_game) {
                    main_menu.new_game.pressed = false;
                    main_menu.new_game.check();
                }

                if (e.getSource() == main_menu.load_game && main_menu.load_game.entered) {
                    change_to_load();
                }
                if (e.getSource() == main_menu.load_game) {
                    main_menu.load_game.pressed = false;
                    main_menu.load_game.check();
                }

                if (e.getSource() == main_menu.tutorial && main_menu.tutorial.entered) {
                    change_to_tutorial();
                }
                if (e.getSource() == main_menu.tutorial) {
                    main_menu.tutorial.pressed = false;
                    main_menu.tutorial.check();
                }

                if (e.getSource() == main_menu.options && main_menu.options.entered) {
                    change_to_options();
                }
                if (e.getSource() == main_menu.options) {
                    main_menu.options.pressed = false;
                    main_menu.options.check();
                }


                if (e.getSource() == main_menu.back && main_menu.back.entered) {
                    if (main_menu.option_page) {
                        Saving_Handler.save_options();
                        gui.requestFocus();
                    }
                    change_to_start();
                }
                if (e.getSource() == main_menu.back) {
                    main_menu.back.pressed = false;
                    main_menu.back.check();
                }

                if (e.getSource() == main_menu.t_next && main_menu.t_next.entered) {
                    if (main_menu.tutorial_current == main_menu.tutorial_pic.length - 1)
                        main_menu.tutorial_current = 0;
                    else
                        main_menu.tutorial_current++;
                }
                if (e.getSource() == main_menu.t_next) {
                    main_menu.t_next.pressed = false;
                    main_menu.t_next.check();
                }

                if (e.getSource() == main_menu.t_prev && main_menu.t_prev.entered) {
                    if (main_menu.tutorial_current == 0)
                        main_menu.tutorial_current = main_menu.tutorial_pic.length - 1;
                    else
                        main_menu.tutorial_current--;
                }
                if (e.getSource() == main_menu.t_prev) {
                    main_menu.t_prev.pressed = false;
                    main_menu.t_prev.check();
                }


                for (int i = 0; i < 10; i++) {
                    if (e.getSource() == main_menu.name_label[i] && main_menu.load_page && main_menu.stat_slot[i].entered && !main_menu.name_label[i].getText().equals("<html><body><center>EMPTY<br>EMPTY</center></body></html>")) {
                        gui.game_menu(Saving_Handler.load_game(i + 1));
                    }

                    if (e.getSource() == main_menu.name_label[i] && main_menu.new_page && main_menu.stat_slot[i].entered) {
                        start_new_game(i + 1);
                    }

                    if (e.getSource() == main_menu.name_label[i]) {
                        main_menu.stat_slot[i].pressed = false;
                        main_menu.stat_slot[i].check();
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (e.getSource() == main_menu.new_game) {
                    main_menu.new_game.entered = true;
                    main_menu.new_game.check();
                }
                if (e.getSource() == main_menu.load_game) {
                    main_menu.load_game.entered = true;
                    main_menu.load_game.check();
                }
                if (e.getSource() == main_menu.tutorial) {
                    main_menu.tutorial.entered = true;
                    main_menu.tutorial.check();
                }
                if (e.getSource() == main_menu.options) {
                    main_menu.options.entered = true;
                    main_menu.options.check();
                }

                if (e.getSource() == main_menu.back) {
                    main_menu.back.entered = true;
                    main_menu.back.check();
                }
                if (e.getSource() == main_menu.t_prev) {
                    main_menu.t_prev.entered = true;
                    main_menu.t_prev.check();
                }
                if (e.getSource() == main_menu.t_next) {
                    main_menu.t_next.entered = true;
                    main_menu.t_next.check();
                }

                for (int i = 0; i < 10; i++) {
                    if (e.getSource() == main_menu.name_label[i] && main_menu.load_page && !main_menu.name_label[i].getText().equals("<html><body><center>EMPTY<br>EMPTY</center></body></html>")) {
                        main_menu.stat_slot[i].entered = true;
                        main_menu.stat_slot[i].check();
                    }

                    if (e.getSource() == main_menu.name_label[i] && main_menu.new_page) {
                        main_menu.stat_slot[i].entered = true;
                        main_menu.stat_slot[i].check();
                    }
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource() == main_menu.new_game) {
                    main_menu.new_game.entered = false;
                    main_menu.new_game.check();
                }
                if (e.getSource() == main_menu.load_game) {
                    main_menu.load_game.entered = false;
                    main_menu.load_game.check();
                }
                if (e.getSource() == main_menu.tutorial) {
                    main_menu.tutorial.entered = false;
                    main_menu.tutorial.check();
                }
                if (e.getSource() == main_menu.options) {
                    main_menu.options.entered = false;
                    main_menu.options.check();
                }

                if (e.getSource() == main_menu.back) {
                    main_menu.back.entered = false;
                    main_menu.back.check();
                }
                if (e.getSource() == main_menu.t_prev) {
                    main_menu.t_prev.entered = false;
                    main_menu.t_prev.check();
                }
                if (e.getSource() == main_menu.t_next) {
                    main_menu.t_next.entered = false;
                    main_menu.t_next.check();
                }


                for (int i = 0; i < 10; i++) {
                    if (e.getSource() == main_menu.name_label[i] && main_menu.load_page && !main_menu.name_label[i].getText().equals("<html><body><center>EMPTY<br>EMPTY</center></body></html>")) {
                        main_menu.stat_slot[i].entered = false;
                        main_menu.stat_slot[i].check();
                    }

                    if (e.getSource() == main_menu.name_label[i] && main_menu.new_page) {
                        main_menu.stat_slot[i].entered = false;
                        main_menu.stat_slot[i].check();
                    }
                }
            }
        };

        main_menu = new Main_Menu(mouseA);

        main_paint = new Main_Paint(this.main_menu);
        this.add(main_paint);

        this.setVisible(true);
    }

    void change_to_start() {
        clearPaint_panel();
        main_paint.add(main_menu.set_start_panel());
        main_paint.revalidate();
    }

    private void change_to_new() {
        clearPaint_panel();
        main_paint.add(main_menu.set_new_panel());
        main_paint.revalidate();
    }

    private void change_to_load() {
        clearPaint_panel();
        main_paint.add(main_menu.set_load_panel());
        main_paint.revalidate();
    }

    private void change_to_tutorial() {
        clearPaint_panel();
        main_paint.add(main_menu.set_tutorial_panel());
        main_paint.revalidate();
    }

    private void change_to_options() {
        clearPaint_panel();
        main_paint.add(main_menu.set_options_panel());
        main_paint.revalidate();
    }

    private void clearPaint_panel() {
        try {
            main_paint.remove(main_menu.start_panel);
        } catch (Exception ignored) {
        }
        try {
            main_paint.remove(main_menu.load_panel);
        } catch (Exception ignored) {
        }
        try {
            main_paint.remove(main_menu.tutorial_panel);
        } catch (Exception ignored) {
        }
        try {
            main_paint.remove(main_menu.options_panel);
        } catch (Exception ignored) {
        }
        try {
            main_paint.remove(main_menu.new_panel);
        } catch (Exception ignored) {
        }
    }

    private void start_new_game(int filenumber) {
        JDialog namer = new JDialog(gui);
        namer.setLayout(new GridLayout(3, 1));
        namer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        namer.setModal(true);


        JLabel name_label = new JLabel("YOUR NAME:");
        name_label.setHorizontalAlignment(JLabel.CENTER);
        name_label.setVerticalAlignment(JLabel.CENTER);
        name_label.setFont(Saving_Handler.ARDESTINE());
        namer.add(name_label);

        JTextField name_field = new JTextField();
        name_field.setHorizontalAlignment(JTextField.CENTER);
        name_field.setFont(Saving_Handler.ARDESTINE());
        namer.add(name_field);

        JButton name_button = new JButton("START");
        name_button.setFont(Saving_Handler.ARDESTINE());
        name_button.addActionListener(e -> {
            gui.game_menu(Saving_Handler.new_game(filenumber, name_field.getText()));
            namer.dispose();
        });
        namer.add(name_button);
        namer.pack();
        namer.setLocationRelativeTo(gui);
        namer.setVisible(true);
    }

    void resizepanel() {
        this.setPreferredSize(new Dimension(GUI.screenwidth, GUI.screenheight));
        gui.pack();
        main_paint.resizepaint();

        if (main_menu.start_page)
            change_to_start();
        if (main_menu.new_page)
            change_to_new();
        if (main_menu.load_page)
            change_to_load();
        if (main_menu.tutorial_page)
            change_to_tutorial();
    }

}
