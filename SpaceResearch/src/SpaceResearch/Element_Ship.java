package SpaceResearch;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Element_Ship {
    Stats stats;

    private double movementspeed;
    private double directionspeed;
    private double load;
    private double shell;
    private double connection;
    private double fuel;
    private double supply;
    private double connection_accuracy;
    private double connection_quality;

    private BufferedImage rocket_stand, rocket_straight_1, rocket_straight_2, rocket_right_1, rocket_right_2, rocket_left_1, rocket_left_2;
    BufferedImage planet_home_show;

    int direction;
    private double[][] directions;
    private double[] buffered_direction;
    int[] position;
    double home_planet_show_ankel;

    private boolean rocket_animation;
    int rocket_animation_speed;

    ArrayList<Integer> collected_stuff;

    boolean speed_up, slow_down, turn_left, turn_right;

    boolean flying;

    boolean lost_contact;
    private boolean c_deviation_max;
    private boolean c_deviation_min;
    private boolean c_deviation_increase;
    private boolean c_deviation_decrease;

    Element_Ship(Stats stats) {
        this.stats = stats;

        try {
            rocket_stand = ImageIO.read(getClass().getResourceAsStream("/Ship_rocket_stand.png"));
            rocket_straight_1 = ImageIO.read(getClass().getResourceAsStream("/Ship_rocket_straight_1.png"));
            rocket_straight_2 = ImageIO.read(getClass().getResourceAsStream("/Ship_rocket_straight_2.png"));
            rocket_left_1 = ImageIO.read(getClass().getResourceAsStream("/Ship_rocket_left_1.png"));
            rocket_left_2 = ImageIO.read(getClass().getResourceAsStream("/Ship_rocket_left_2.png"));
            rocket_right_1 = ImageIO.read(getClass().getResourceAsStream("/Ship_rocket_right_1.png"));
            rocket_right_2 = ImageIO.read(getClass().getResourceAsStream("/Ship_rocket_right_2.png"));

            planet_home_show = ImageIO.read(getClass().getResourceAsStream("/Ship_planet_director.png"));
        } catch (Exception e) {
            System.out.println("Fehler beim Raketenbilder laden");
        }

        direction = 0;

        directions = new double[16][2];
        directions[0][0] = 0.; //up
        directions[0][1] = 3.;
        directions[1][0] = -1.14; //up right
        directions[1][1] = 2.76;
        directions[2][0] = -2.13; //right
        directions[2][1] = 2.13;
        directions[3][0] = -2.76; //down right
        directions[3][1] = 1.14;
        directions[4][0] = -3.; //down
        directions[4][1] = 0.;
        directions[5][0] = -2.76; //down left
        directions[5][1] = -1.14;
        directions[6][0] = -2.13; //left
        directions[6][1] = -2.13;
        directions[7][0] = -1.14; //up left
        directions[7][1] = -2.76;
        directions[8][0] = 0.; //up left
        directions[8][1] = -3.;
        directions[9][0] = 1.14; //up left
        directions[9][1] = -2.76;
        directions[10][0] = 2.13; //up left
        directions[10][1] = -2.13;
        directions[11][0] = 2.76; //up left
        directions[11][1] = -1.14;
        directions[12][0] = 3; //up left
        directions[12][1] = 0;
        directions[13][0] = 2.76; //up left
        directions[13][1] = 1.14;
        directions[14][0] = 2.13; //up left
        directions[14][1] = 2.13;
        directions[15][0] = 1.14; //up left
        directions[15][1] = 2.76;

        buffered_direction = new double[]{0., 0.};

        load = 0;
        shell = stats.getMax_shell();
        fuel = stats.getMax_fuel();
        supply = stats.getMax_supply();
        connection = stats.getMax_connection();
        movementspeed = 1;
        directionspeed = stats.getSteering();

        position = new int[]{0, 0};

        home_planet_show_ankel = 0.;

        rocket_animation = false;
        rocket_animation_speed = 5;

        speed_up = slow_down = turn_left = turn_right = false;

        collected_stuff = new ArrayList<>();

        flying = false;

        lost_contact = false;
    }

    int[] getRocketSize() {
        return new int[]{rocket_stand.getWidth(), rocket_stand.getHeight()};
    }

    void reset() {
        movementspeed = 1.5;
        fuel = stats.getMax_fuel();
        supply = stats.getMax_supply();
        shell = stats.getMax_shell();
        direction = 0;
        load = 0;
        lost_contact = false;
        connection_accuracy = 0.;
        c_deviation_min = false;
        c_deviation_max = false;
        c_deviation_increase = false;
        c_deviation_decrease = false;
        connection_quality = 1.5 * (1. - (double) stats.getQuality_of_direction() / ((double) stats.getQuality_of_direction() + 2.));
    }

    // --------------------FUEL ---------------------------

    void consum_fuel_speed_up() {
        if (fuel > 0)
            fuel -= (double) stats.getFuel_consume_speed_up() * movementspeed / 500.;
    }

    void consum_fuel_speed_down() {
        if (fuel > 0)
            fuel -= (double) stats.getFuel_consume_speed_down() * movementspeed / 500.;
    }

    void consum_fuel_accelerate() {
        if (fuel > 0)
            fuel -= ((double) stats.getFuel_consume_steering() * movementspeed / 1000.);
    }

    int getFuel() {
        return (int) fuel;
    }

    // -------------------- SPEED -------------------------

    void increase_movementspeed() {
        if ((int) movementspeed < stats.getMax_speed()) {
            if (movementspeed < 1.)
                movementspeed = 1.;

            else
                movementspeed *= (1.05 * (1 + ((double) stats.getSpeed_up() / 1000.)));

            if ((int) movementspeed > stats.getMax_speed())
                movementspeed = stats.getMax_speed();

        }
    }

    void decrease_movementspeed() {
        if ((int) movementspeed > 1.)
            movementspeed /= (1.1 * (1 + ((double) stats.getSlow_down() / 500.)));

        else
            movementspeed = 1.;
    }

    double getMovementspeed() {
        return movementspeed;
    }

    int getMovement() {
        return (int) (100. / movementspeed);
    }


    //------------------------Steering---------------------

    void setDirection() {

        if (turn_right) {
            if (direction == 15)
                direction = 0;
            else
                direction++;
        }
        if (turn_left) {
            if (direction == 0)
                direction = 15;
            else
                direction--;
        }
    }

    void increaseDirection() {
        buffered_direction[0] += directions[direction][0] * 25. / (double) getMovement();
        buffered_direction[1] += directions[direction][1] * 25. / (double) getMovement();
    }

    void reduceDirection(int done_x, int done_y) {
        buffered_direction[0] -= (double) done_x;
        buffered_direction[1] -= (double) done_y;
    }

    int getDirectionspeed() {
        return (int) (100. * (40. + directionspeed) / directionspeed);
    }

    double[] getDirection() {
        return buffered_direction;
    }


    // --------------- Supply -----------------------------------

    void consum_supply() {
        if (supply > 0)
            supply -= (int) (((9. + (double) stats.getSupply_quality()) / (double) stats.getSupply_quality()) + 0.5);
    }

    void increaseSupply(int regeneration) {
        if (supply < stats.getMax_supply())
            supply += regeneration;
        if (supply > stats.getMax_supply())
            supply = stats.getMax_supply();
    }

    void wasteSupply() {
        if (supply > 0) {
            if (Math.random() > 0.6 - (0.6 - (double) stats.getChance_of_waste() / ((double) stats.getChance_of_waste() + 5.)))
                supply -= (int) (((10. + (double) stats.getSupply_quality()) / (double) stats.getSupply_quality()) + 0.5);

        }
    }

    int getSupply() {
        return (int) supply;
    }


    // -------------------- Load -------------------------------

    void increase_load(int size) {
        load += size * stats.getSpace_per_load();
    }

    int getLoad() {
        return (int) load;
    }


    // ------------------- Connection ----------------------------

    void setConnection(double connection) {
        if ((double) stats.getMax_connection() - connection <= 0.)
            this.connection = 0.;
        else
            this.connection = (double) stats.getMax_connection() - connection;
    }

    int getConnection() {
        return (int) this.connection;
    }

    boolean lose_contact() {
        if (Math.random() < (1. - (double) stats.getChance_of_disconnect() / ((double) stats.getChance_of_disconnect() + 5.)) / 100.)
            lost_contact = true;
        return lost_contact;
    }

    int getLose_duration() {
        return 5000 - (int) (5000. * (double) stats.getDuration_of_disconnect() / ((double) stats.getDuration_of_disconnect() + 10.));
    }

    double getConnectionAccuracy() {
        if (c_deviation_min) {
            connection_accuracy += 0.005;
            if (connection_accuracy >= 0)
                c_deviation_min = false;
            return connection_accuracy;
        }
        if (c_deviation_max) {
            connection_accuracy -= 0.005;
            if (connection_accuracy <= 0)
                c_deviation_max = false;
            return connection_accuracy;
        }
        if (c_deviation_increase && connection_accuracy < connection_quality / 2) {
            connection_accuracy += 0.005;
            if (connection_accuracy >= connection_quality / 2)
                c_deviation_increase = false;
            return connection_accuracy;
        }
        if (c_deviation_increase && connection_accuracy >= connection_quality / 2) {
            connection_accuracy += 0.005;
            if (connection_accuracy >= connection_quality) {
                c_deviation_max = true;
                c_deviation_increase = false;
            }
            return connection_accuracy;
        }

        if (c_deviation_decrease && connection_accuracy > connection_quality / -2) {
            connection_accuracy -= 0.005;
            if (connection_accuracy <= connection_quality / -2)
                c_deviation_decrease = false;
            return connection_accuracy;
        }
        if (c_deviation_decrease && connection_accuracy <= connection_quality / -2) {
            connection_accuracy -= 0.005;
            if (connection_accuracy <= connection_quality) {
                c_deviation_min = true;
                c_deviation_decrease = false;
            }
            return connection_accuracy;
        }
        if (Math.random() < 0.5)
            c_deviation_increase = true;
        else
            c_deviation_decrease = true;
        return connection_accuracy;
    }

    // ------------------- Shell ----------------------------------

    void starting_damage() {
        decrease_shell((int) ((double) stats.getMax_shell() * Math.random() * (0.5 - (0.5 * ((double) stats.getStarting_damage() / ((double) stats.getStarting_damage() + 10.))))));

    }

    void decrease_shell(int damage) {
        if (Math.random() > (0.7 * ((double) stats.getChance_of_avoidance() / ((double) stats.getChance_of_avoidance() + 10.)))) {
            if (this.shell > 0)
                this.shell -= damage;

            if (this.shell < 0)
                this.shell = 0;
        }
    }

    void increaseShell(int regeneration) {
        if (shell < stats.getMax_shell())
            shell += regeneration;
        if (shell > stats.getMax_shell())
            shell = stats.getMax_shell();
    }

    int getShell() {
        return (int) this.shell;
    }


    //---------------- Rocket Animation -------------------------

    BufferedImage rocket() {
        if ((turn_left && turn_right) || (speed_up && !turn_left && !turn_right)) {
            if (rocket_animation)
                return rocket_straight_1;
            return rocket_straight_2;
        }

        if (turn_left) {
            if (rocket_animation)
                return rocket_left_1;
            return rocket_left_2;
        }

        if (turn_right) {
            if (rocket_animation)
                return rocket_right_1;
            return rocket_right_2;
        }
        return rocket_stand;
    }

    void setRocket_animation() {
        rocket_animation = !rocket_animation;
    }


    // ------------------- Sounds -------------------

    void sound_collected() {
        if (Saving_Handler.played_sounds[0] && Saving_Handler.played_sounds[2]) {
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("Sound_collected.wav")));
                clip.start();
                clip.addLineListener(myLineEvent -> {
                    if (myLineEvent.getType() == LineEvent.Type.STOP)
                        clip.close();
                });
            } catch (Exception ignored) {
            }

        }
    }

    void sound_explosion() {
        if (Saving_Handler.played_sounds[0] && Saving_Handler.played_sounds[3]) {
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("Sound_explosion.wav")));
                clip.start();
                clip.addLineListener(myLineEvent -> {
                    if (myLineEvent.getType() == LineEvent.Type.STOP)
                        clip.close();
                });
            } catch (Exception exc) {
                exc.printStackTrace(System.out);
            }
        }
    }

    void sound_speed_up() {
        if (Saving_Handler.played_sounds[0] && Saving_Handler.played_sounds[4]) {
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("Sound_speed_up.wav")));
                clip.start();
                clip.addLineListener(myLineEvent -> {
                    if (myLineEvent.getType() == LineEvent.Type.STOP)
                        clip.close();
                });
            } catch (Exception exc) {
                exc.printStackTrace(System.out);
            }
        }
    }

    void sound_slow_down() {
        if (Saving_Handler.played_sounds[0] && Saving_Handler.played_sounds[5]) {
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("Sound_slow_down.wav")));
                clip.start();
                clip.addLineListener(myLineEvent -> {
                    if (myLineEvent.getType() == LineEvent.Type.STOP)
                        clip.close();

                });


            } catch (Exception exc) {
                exc.printStackTrace(System.out);
            }
        }
    }

    void sound_mission_start() {
        if (Saving_Handler.played_sounds[0] && Saving_Handler.played_sounds[1]) {

            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("Sound_mission_start.wav")));
                clip.start();
                clip.addLineListener(myLineEvent -> {
                    if (myLineEvent.getType() == LineEvent.Type.STOP)
                        clip.close();
                });

            } catch (Exception exc) {
                exc.printStackTrace(System.out);
            }
        }
    }

}
