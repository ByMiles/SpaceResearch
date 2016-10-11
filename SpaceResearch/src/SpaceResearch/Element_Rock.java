package SpaceResearch;

import java.util.Timer;
import java.util.TimerTask;

class Element_Rock {
    int type;
    int position_x;
    int position_y;
    int movement_x;
    int movement_y;
    int buffered_movement_x;
    int buffered_movement_y;
    int damage;
    int radius;
    double rotation_speed;
    double rotation;

    volatile boolean hitable;

    Element_Rock(int screenwidth, int screenheight) {
        type = (int) (Math.random() * 10);

        double start = Math.random();
        if (start < 0.25) {
            position_x = -screenwidth;
            position_y = (int) (Math.random() * (double) screenheight * 2.) - screenheight;
        } else if (start < 0.5) {
            position_x = screenwidth * 2;
            position_y = (int) (Math.random() * (double) screenheight * 2.) - screenheight;
        } else if (start < 0.75) {
            position_y = -screenheight;
            position_x = (int) (Math.random() * (double) screenwidth) - screenwidth;
        } else {
            position_y = screenheight * 2;
            position_x = (int) (Math.random() * (double) screenwidth) - screenwidth;
        }

        movement_x = (int) (Math.random() * 5.) - 2;
        movement_y = (int) (Math.random() * 5.) - 2;

        switch (type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                damage = 5 + ((int) ((Math.random() + 0.5) * 10));
                radius = 10;
                break;
            case 6:
            case 7:
            case 8:
                damage = 25 + ((int) ((Math.random() + 0.5) * 20));
                radius = 20;
            case 9:
                damage = 100 + ((int) ((Math.random() + 0.5) * 100));
                radius = 40;
        }

        rotation_speed = (Math.random() - 0.5) / 50;
        rotation = 0;

        hitable = true;
    }

    void collision() {
        hitable = false;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                hitable = true;
            }
        }, 5000);
    }
}
