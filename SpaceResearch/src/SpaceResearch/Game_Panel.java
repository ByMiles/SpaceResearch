package SpaceResearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.Timer;

class Game_Panel extends JPanel {
    static int[] serosero = new int[2];

    private Timer back_home;
    private Timer rocket_animation;

    private Game_Menu game_menu;

    Element_Ship ship;
    Element_Space space;

    private Game_Paint game_paint;
    KeyListener keyEvent;

    private Cursor cursor_space;
    private Cursor cursor_home;

    Game_Panel(GUI gui, Stats stats) {
        setSerosero();

        ship = new Element_Ship(stats);
        space = new Element_Space();
        this.setPreferredSize(new Dimension(GUI.screenwidth, GUI.screenheight));
        this.setLayout(null);

        MouseAdapter mouseA = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getSource() == game_menu.new_mission) {
                    game_menu.new_mission.pressed = true;
                    game_menu.new_mission.check();
                }
                if (e.getSource() == game_menu.save_game) {
                    game_menu.save_game.pressed = true;
                    game_menu.save_game.check();
                }
                if (e.getSource() == game_menu.main_menu) {
                    game_menu.main_menu.pressed = true;
                    game_menu.main_menu.check();
                }
                if (e.getSource() == game_menu.improve_1) {
                    game_menu.improve_1.pressed = true;
                    game_menu.improve_1.check();
                }
                if (e.getSource() == game_menu.improve_2) {
                    game_menu.improve_2.pressed = true;
                    game_menu.improve_2.check();
                }
                if (e.getSource() == game_menu.improve_3) {
                    game_menu.improve_3.pressed = true;
                    game_menu.improve_3.check();
                }
                if (e.getSource() == game_menu.improve_4) {
                    game_menu.improve_4.pressed = true;
                    game_menu.improve_4.check();
                }
                if (e.getSource() == game_menu.go_try_again) {
                    game_menu.go_try_again.pressed = true;
                    game_menu.go_try_again.check();
                }
                if (e.getSource() == game_menu.go_main_menu) {
                    game_menu.go_main_menu.pressed = true;
                    game_menu.go_main_menu.check();
                }
                if (e.getSource() == game_menu.go_exit) {
                    game_menu.go_exit.pressed = true;
                    game_menu.go_exit.check();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getSource() == game_menu.new_mission && game_menu.new_mission.entered) {
                    game_menu.game_saved(Saving_Handler.save_game(stats.filenumber, stats));
                    change_to_space();
                }
                if (e.getSource() == game_menu.new_mission) {
                    game_menu.new_mission.pressed = false;
                    game_menu.new_mission.check();
                }

                if (e.getSource() == game_menu.save_game && game_menu.save_game.entered) {
                    game_menu.game_saved(Saving_Handler.save_game(stats.filenumber, stats));
                }
                if (e.getSource() == game_menu.save_game) {
                    game_menu.save_game.pressed = false;
                    game_menu.save_game.check();
                }

                if (e.getSource() == game_menu.main_menu && game_menu.main_menu.entered) {
                    Saving_Handler.save_game(stats.filenumber, stats);
                    gui.main_menu();
                }
                if (e.getSource() == game_menu.main_menu) {
                    game_menu.main_menu.pressed = false;
                    game_menu.main_menu.check();
                }

                if (e.getSource() == game_menu.improve_1 && game_menu.improve_1.entered) {
                    game_menu.check_affordable(stats.improve(game_menu.current_submenu, 0));
                    game_menu.setPrices();
                }
                if (e.getSource() == game_menu.improve_1) {
                    game_menu.improve_1.pressed = false;
                    game_menu.improve_1.check();
                }
                if (e.getSource() == game_menu.improve_2 && game_menu.improve_2.entered) {
                    game_menu.check_affordable(stats.improve(game_menu.current_submenu, 1));
                    game_menu.setPrices();
                }
                if (e.getSource() == game_menu.improve_2) {
                    game_menu.improve_2.pressed = false;
                    game_menu.improve_2.check();
                }
                if (e.getSource() == game_menu.improve_3 && game_menu.improve_3.entered) {
                    game_menu.check_affordable(stats.improve(game_menu.current_submenu, 2));
                    game_menu.setPrices();
                }
                if (e.getSource() == game_menu.improve_3) {
                    game_menu.improve_3.pressed = false;
                    game_menu.improve_3.check();
                }
                if (e.getSource() == game_menu.improve_4 && game_menu.improve_4.entered) {
                    game_menu.check_affordable(stats.improve(game_menu.current_submenu, 3));
                    game_menu.setPrices();
                }
                if (e.getSource() == game_menu.improve_4) {
                    game_menu.improve_4.pressed = false;
                    game_menu.improve_4.check();
                }
                if (e.getSource() == game_menu.go_try_again && game_menu.go_try_again.entered) {
                    gui.game_menu(stats);
                }
                if (e.getSource() == game_menu.go_try_again) {
                    game_menu.go_try_again.pressed = false;
                    game_menu.go_try_again.check();
                }
                if (e.getSource() == game_menu.go_main_menu && game_menu.go_main_menu.entered) {
                    gui.main_menu();
                }
                if (e.getSource() == game_menu.go_main_menu) {
                    game_menu.go_main_menu.pressed = false;
                    game_menu.go_main_menu.check();
                }
                if (e.getSource() == game_menu.go_exit && game_menu.go_exit.entered) {
                    System.exit(0);
                }
                if (e.getSource() == game_menu.go_exit) {
                    game_menu.go_exit.pressed = false;
                    game_menu.go_exit.check();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (e.getSource() == game_menu.status) {
                    game_menu.current_submenu = 6;
                    game_menu.setStatuses();
                }
                if (e.getSource() == game_menu.research) {
                    game_menu.current_submenu = 7;
                    game_menu.clearSubmenu();
                }
                if (e.getSource() == game_menu.improve_fuel) {
                    game_menu.current_submenu = 3;
                    game_menu.setPrices();
                }  // Fuel
                if (e.getSource() == game_menu.improve_hold) {
                    game_menu.current_submenu = 1;
                    game_menu.setPrices();
                }  // Load
                if (e.getSource() == game_menu.improve_supply) {
                    game_menu.current_submenu = 2;
                    game_menu.setPrices();
                }  // supply
                if (e.getSource() == game_menu.improve_shell) {
                    game_menu.current_submenu = 0;
                    game_menu.setPrices();
                }  // shell
                if (e.getSource() == game_menu.improve_engine) {
                    game_menu.current_submenu = 4;
                    game_menu.setPrices();
                }  //speed
                if (e.getSource() == game_menu.improve_radio) {
                    game_menu.current_submenu = 5;
                    game_menu.setPrices();
                }// contact

                if (e.getSource() == game_menu.new_mission) {
                    game_menu.new_mission.entered = true;
                    game_menu.new_mission.check();
                }
                if (e.getSource() == game_menu.save_game) {
                    game_menu.save_game.entered = true;
                    game_menu.save_game.check();
                }
                if (e.getSource() == game_menu.main_menu) {
                    game_menu.main_menu.entered = true;
                    game_menu.main_menu.check();
                }

                if (e.getSource() == game_menu.improve_1) {
                    game_menu.improve_1.entered = true;
                    game_menu.improve_1.check();
                }
                if (e.getSource() == game_menu.improve_2) {
                    game_menu.improve_2.entered = true;
                    game_menu.improve_2.check();
                }
                if (e.getSource() == game_menu.improve_3) {
                    game_menu.improve_3.entered = true;
                    game_menu.improve_3.check();
                }
                if (e.getSource() == game_menu.improve_4) {
                    game_menu.improve_4.entered = true;
                    game_menu.improve_4.check();
                }
                if (e.getSource() == game_menu.go_try_again) {
                    game_menu.go_try_again.entered = true;
                    game_menu.go_try_again.check();
                }
                if (e.getSource() == game_menu.go_main_menu) {
                    game_menu.go_main_menu.entered = true;
                    game_menu.go_main_menu.check();
                }
                if (e.getSource() == game_menu.go_exit) {
                    game_menu.go_exit.entered = true;
                    game_menu.go_exit.check();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource() == game_menu.new_mission) {
                    game_menu.new_mission.entered = false;
                    game_menu.new_mission.check();
                }
                if (e.getSource() == game_menu.save_game) {
                    game_menu.save_game.entered = false;
                    game_menu.save_game.check();
                }
                if (e.getSource() == game_menu.main_menu) {
                    game_menu.main_menu.entered = false;
                    game_menu.main_menu.check();
                }


                if (e.getSource() == game_menu.improve_1) {
                    game_menu.improve_1.entered = false;
                    game_menu.improve_1.check();
                }
                if (e.getSource() == game_menu.improve_2) {
                    game_menu.improve_2.entered = false;
                    game_menu.improve_2.check();
                }
                if (e.getSource() == game_menu.improve_3) {
                    game_menu.improve_3.entered = false;
                    game_menu.improve_3.check();
                }
                if (e.getSource() == game_menu.improve_4) {
                    game_menu.improve_4.entered = false;
                    game_menu.improve_4.check();
                }
                if (e.getSource() == game_menu.go_try_again) {
                    game_menu.go_try_again.entered = false;
                    game_menu.go_try_again.check();
                }
                if (e.getSource() == game_menu.go_main_menu) {
                    game_menu.go_main_menu.entered = false;
                    game_menu.go_main_menu.check();
                }
                if (e.getSource() == game_menu.go_exit) {
                    game_menu.go_exit.entered = false;
                    game_menu.go_exit.check();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        };

        keyEvent = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP)
                    ship.speed_up = true;
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                    ship.slow_down = true;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                    ship.turn_right = true;
                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                    ship.turn_left = true;

            }


            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP)
                    ship.speed_up = false;
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                    ship.slow_down = false;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                    ship.turn_right = false;
                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                    ship.turn_left = false;
            }
        };

        game_menu = new Game_Menu(stats, mouseA);

        cursor_home = Toolkit.getDefaultToolkit().createCustomCursor(
                game_menu.cursor_home, new Point(0, 0), "nice cursor");

        game_paint = new Game_Paint(ship, stats, space, game_menu);
        this.add(game_paint);

        cursor_space = Toolkit.getDefaultToolkit().createCustomCursor(space.cursor_space, new Point(0, 0), "blank cursor");

        this.setVisible(true);
    }

    void change_to_home() {
        game_menu.paint_game_menu = true;
        space.paint_space = false;
        try {
            correct_mouse();
        } catch (Exception ignored) {
        }


        game_menu.removePrices();
        game_paint.add(game_menu);
        game_menu.current_submenu = 6;
        game_menu.setStatuses();
        game_paint.revalidate();
        game_paint.setCursor(cursor_home);

        this.setVisible(true);
    }

    private void change_to_space() {
        try {
            game_paint.remove(game_menu);
        } catch (Exception ignored) {
        }
        space.paint_space = true;
        game_menu.paint_game_menu = false;
        game_paint.revalidate();
        game_paint.setCursor(cursor_space);
        mission_start();
        try {
            correct_mouse();
        } catch (Exception ignored) {
        }
    }

    private void correct_mouse() throws Exception {
        Robot robot = new Robot();

        robot.mouseMove(GUI.screenwidth / 2, GUI.screenheight / 2);
    }

    private void mission_start() {
        ship.reset();
        space.reset_Status();
        ship.sound_mission_start();
        ship.flying = true;
        new Thread(new Thread_Movement(ship, space)).start();

        new Thread(new Thread_Accelerate(ship, space)).start();

        new Thread(new Thread_Steering(ship, space)).start();

        new Thread(new Thread_Supply(ship, space)).start();

        new Thread(new Thread_Load(ship, space)).start();

        new Thread(new Thread_Connection(ship, space)).start();

        new Thread(new Thread_RocksMovement(ship, space)).start();

        new Thread(new Thread_Regeneration(ship, space)).start();

        ship.starting_damage();
        space.change_Status(0, 10 - (int) (((double) ship.getShell() * 10.) / (double) ship.stats.getMax_shell()));

        back_home = new Timer();
        back_home.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (ship.position[0] > -50 && ship.position[1] > -50 && ship.position[0] < 50 && ship.position[1] < 50) {
                    back_home.cancel();
                    mission_stop();
                    ship.stats.sort_stuff(ship.collected_stuff);
                    change_to_home();
                }
                if (space.paint_game_over) {
                    back_home.cancel();
                    game_over();
                }
            }
        }, 10000, 200);

        rocket_animation = new Timer();
        rocket_animation.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ship.setRocket_animation();
            }
        }, 0, ship.rocket_animation_speed);

    }

    private void mission_stop() {
        ship.flying = false;
        ship.stats.create_Archivments(this.ship);
        rocket_animation.cancel();
    }

    private void game_over() {
        rocket_animation.cancel();
        game_paint.add(game_menu.setGame_over());
        game_paint.revalidate();
        game_paint.setCursor(cursor_home);
    }

    void resizepanel() {
        setSerosero();

        game_paint.resizepaint();
        game_menu.resizemenu();
        space.resizeSpace();
        try {
            game_menu.resizeGame_over(game_paint);
        } catch (Exception ignored) {
        }
    }

    private void setSerosero() {
        serosero[0] = GUI.screenwidth / 2;
        serosero[1] = (GUI.screenheight - 200) / 2;
    }
}
