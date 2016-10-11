package SpaceResearch;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

class Thread_Movement implements Runnable {
    Element_Ship ship;
    Element_Space space;
    private Instant previous;
    private Instant now;


    Thread_Movement(Element_Ship ship, Element_Space space) {
        this.ship = ship;
        this.space = space;
    }

    @Override
    public void run() {
        while (ship.flying) {

            try {
                Thread.sleep(ship.getMovement() - ChronoUnit.MILLIS.between(previous, now));
            } catch (Exception ignored) {
            }

            previous = Instant.now();

            if (ship.getMovement() < 100) {

                ship.increaseDirection();
                int direction_x = (int) ship.getDirection()[0];
                int direction_y = (int) ship.getDirection()[1];
                ship.reduceDirection(direction_x, direction_y);

                int height_pic = space.height;
                int width_pic = space.width;

                int konst = 10;

                for (int[] position : space.space_places) {
                    position[0] += direction_x;
                    position[1] += direction_y;

                    if (position[0] > width_pic - konst)
                        position[0] -= width_pic * 2;

                    if (position[0] < -width_pic + konst)
                        position[0] += width_pic * 2;

                    if (position[1] > height_pic - konst)
                        position[1] -= height_pic * 2;

                    if (position[1] < -height_pic + konst)
                        position[1] += height_pic * 2;
                }

                for (int[] position : space.planet_places) {
                    position[0] += direction_x;
                    position[1] += direction_y;
                }


                ship.position[0] -= direction_x;
                ship.position[1] -= direction_y;

                for (int i = 0; i < space.rock_collection.length; i++) {
                    space.rock_collection[i].position_x += (direction_x + space.rock_collection[i].buffered_movement_x);
                    space.rock_collection[i].buffered_movement_x = 0;
                    space.rock_collection[i].position_y += (direction_y + space.rock_collection[i].buffered_movement_y);
                    space.rock_collection[i].buffered_movement_y = 0;

                }
            } else {
                for (int i = 0; i < space.rock_collection.length; i++) {
                    space.rock_collection[i].position_x += (space.rock_collection[i].buffered_movement_x);
                    space.rock_collection[i].buffered_movement_x = 0;
                    space.rock_collection[i].position_y += (space.rock_collection[i].buffered_movement_y);
                    space.rock_collection[i].buffered_movement_y = 0;

                }
            }
            now = Instant.now();
        }
    }
}
