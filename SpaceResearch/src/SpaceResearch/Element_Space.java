package SpaceResearch;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;


public class Element_Space {
    int width;
    int height;

    int[][] space_places;
    int[][] planet_places;

    Element_Rock[] rock_collection;


    BufferedImage[] stuff_pics;
    private BufferedImage[] status_pics;
    BufferedImage[] current_status;
    BufferedImage[] rock_pics;

    BufferedImage basic_background;
    BufferedImage status_background;
    BufferedImage space_1, space_2, space_3, space_4, space_5, space_6, space_7, space_8, space_9;

    BufferedImage planet_home;

    BufferedImage outer_frame;

    BufferedImage game_over_pic;

    BufferedImage cursor_space;

    boolean paint_space;

    boolean paint_game_over;

    Element_Space() {
        this.width = GUI.screenwidth;
        this.height = GUI.screenheight;

        paint_game_over = false;

        try {
            basic_background = ImageIO.read(getClass().getResourceAsStream("/Space_background.png"));
            status_background = ImageIO.read(getClass().getResourceAsStream("/Status_background.png"));


            space_1 = ImageIO.read(getClass().getResourceAsStream("/Space_space.png"));
            space_2 = ImageIO.read(getClass().getResourceAsStream("/Space_space.png"));
            space_3 = ImageIO.read(getClass().getResourceAsStream("/Space_space.png"));
            space_4 = ImageIO.read(getClass().getResourceAsStream("/Space_space.png"));
            space_5 = ImageIO.read(getClass().getResourceAsStream("/Space_space.png"));
            space_6 = ImageIO.read(getClass().getResourceAsStream("/Space_space.png"));
            space_7 = ImageIO.read(getClass().getResourceAsStream("/Space_space.png"));
            space_8 = ImageIO.read(getClass().getResourceAsStream("/Space_space.png"));
            space_9 = ImageIO.read(getClass().getResourceAsStream("/Space_space.png"));

            stuff_pics = new BufferedImage[6];
            stuff_pics[0] = ImageIO.read(getClass().getResourceAsStream("/Status_shell.png"));
            stuff_pics[1] = ImageIO.read(getClass().getResourceAsStream("/Status_load.png"));
            stuff_pics[2] = ImageIO.read(getClass().getResourceAsStream("/Status_supply.png"));
            stuff_pics[3] = ImageIO.read(getClass().getResourceAsStream("/Status_fuel.png"));
            stuff_pics[4] = ImageIO.read(getClass().getResourceAsStream("/Status_speed.png"));
            stuff_pics[5] = ImageIO.read(getClass().getResourceAsStream("/Status_contact.png"));

            status_pics = new BufferedImage[11];
            status_pics[0] = ImageIO.read(getClass().getResourceAsStream("/Status_100.png"));
            status_pics[1] = ImageIO.read(getClass().getResourceAsStream("/Status_90.png"));
            status_pics[2] = ImageIO.read(getClass().getResourceAsStream("/Status_80.png"));
            status_pics[3] = ImageIO.read(getClass().getResourceAsStream("/Status_70.png"));
            status_pics[4] = ImageIO.read(getClass().getResourceAsStream("/Status_60.png"));
            status_pics[5] = ImageIO.read(getClass().getResourceAsStream("/Status_50.png"));
            status_pics[6] = ImageIO.read(getClass().getResourceAsStream("/Status_40.png"));
            status_pics[7] = ImageIO.read(getClass().getResourceAsStream("/Status_30.png"));
            status_pics[8] = ImageIO.read(getClass().getResourceAsStream("/Status_20.png"));
            status_pics[9] = ImageIO.read(getClass().getResourceAsStream("/Status_10.png"));
            status_pics[10] = ImageIO.read(getClass().getResourceAsStream("/Status_00.png"));

            current_status = new BufferedImage[6];
            reset_Status();

            rock_pics = new BufferedImage[10];
            rock_pics[0] = ImageIO.read(getClass().getResourceAsStream("/Space_small_rock_1.png"));
            rock_pics[1] = ImageIO.read(getClass().getResourceAsStream("/Space_small_rock_2.png"));
            rock_pics[2] = ImageIO.read(getClass().getResourceAsStream("/Space_small_rock_3.png"));
            rock_pics[3] = ImageIO.read(getClass().getResourceAsStream("/Space_small_rock_4.png"));
            rock_pics[4] = ImageIO.read(getClass().getResourceAsStream("/Space_small_rock_5.png"));
            rock_pics[5] = ImageIO.read(getClass().getResourceAsStream("/Space_small_rock_6.png"));
            rock_pics[6] = ImageIO.read(getClass().getResourceAsStream("/Space_middle_rock_1.png"));
            rock_pics[7] = ImageIO.read(getClass().getResourceAsStream("/Space_middle_rock_2.png"));
            rock_pics[8] = ImageIO.read(getClass().getResourceAsStream("/Space_middle_rock_3.png"));
            rock_pics[9] = ImageIO.read(getClass().getResourceAsStream("/Space_big_rock.png"));

            planet_home = ImageIO.read(getClass().getResourceAsStream("/Space_planet_home.png"));

            game_over_pic = ImageIO.read(getClass().getResourceAsStream("/Space_game_over.png"));

            outer_frame = ImageIO.read(getClass().getResourceAsStream("/Space_frame.png"));

            cursor_space = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        } catch (Exception e) {
            System.out.println("Fehler Element_Space laden");
        }

        paint_space = false;

        space_places = new int[9][2];

        space_places[0][0] = 0 - width;
        space_places[0][1] = 0 - height;

        space_places[1][0] = 0;
        space_places[1][1] = 0 - height;

        space_places[2][0] = width + 1;
        space_places[2][1] = 0 - height;

        space_places[3][0] = 0 - width;
        space_places[3][1] = 0;

        space_places[4][0] = 0;
        space_places[4][1] = 0;

        space_places[5][0] = width + 1;
        space_places[5][1] = 0;

        space_places[6][0] = 0 - width;
        space_places[6][1] = height + 1;

        space_places[7][0] = 0;
        space_places[7][1] = height + 1;

        space_places[8][0] = width + 1;
        space_places[8][1] = height + 1;


        planet_places = new int[1][2];

        planet_places[0][0] = 0;
        planet_places[0][1] = 0;

        rock_collection = new Element_Rock[20];

        for (int i = 0; i < 20; i++) {
            rock_collection[i] = new Element_Rock(width, height);
        }
    }

    void reset_Status() {
        paint_game_over = false;
        current_status[0] = status_pics[0];  // shell => full
        current_status[1] = status_pics[10]; // load => empty
        current_status[2] = status_pics[0];  // supply => full
        current_status[3] = status_pics[0];  // fuel => full
        current_status[4] = status_pics[10]; // speed => empty
        current_status[5] = status_pics[0];  // contact => full
    }

    void change_Status(int which_info, int new_status) {
        current_status[which_info] = status_pics[new_status];
    }

    void resizeSpace() {
        this.width = GUI.screenwidth;
        this.height = GUI.screenheight;

        space_places[0][0] = 0 - width;
        space_places[0][1] = 0 - height;

        space_places[1][0] = 0;
        space_places[1][1] = 0 - height;

        space_places[2][0] = width + 1;
        space_places[2][1] = 0 - height;

        space_places[3][0] = 0 - width;
        space_places[3][1] = 0;

        space_places[4][0] = 0;
        space_places[4][1] = 0;

        space_places[5][0] = width + 1;
        space_places[5][1] = 0;

        space_places[6][0] = 0 - width;
        space_places[6][1] = height + 1;

        space_places[7][0] = 0;
        space_places[7][1] = height + 1;

        space_places[8][0] = width + 1;
        space_places[8][1] = height + 1;
    }
}
