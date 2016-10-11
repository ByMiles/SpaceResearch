package SpaceResearch;

class Thread_RocksMovement implements Runnable {
    Element_Ship ship;
    Element_Space space;

    Thread_RocksMovement(Element_Ship ship, Element_Space space) {
        this.ship = ship;
        this.space = space;
    }

    @Override
    public void run() {
        while (ship.flying) {
            try {
                Thread.sleep(25);
            } catch (Exception ignored) {
            }

            for (int i = 0; i < space.rock_collection.length; i++) {

                space.rock_collection[i].buffered_movement_x += space.rock_collection[i].movement_x;
                space.rock_collection[i].buffered_movement_y += space.rock_collection[i].movement_y;

                if (space.rock_collection[i].position_x < -space.width || space.rock_collection[i].position_x > space.width * 2)
                    space.rock_collection[i] = new Element_Rock(space.width, space.height);

                if (space.rock_collection[i].position_y < -space.height || space.rock_collection[i].position_y > space.height * 2)
                    space.rock_collection[i] = new Element_Rock(space.width, space.height);

                if (!space.paint_game_over && space.rock_collection[i].hitable && space.rock_collection[i].position_x > -space.rock_collection[i].radius && space.rock_collection[i].position_x < space.rock_collection[i].radius && space.rock_collection[i].position_y > -space.rock_collection[i].radius && space.rock_collection[i].position_y < space.rock_collection[i].radius) {
                    {
                        space.rock_collection[i].collision();
                        ship.decrease_shell(space.rock_collection[i].damage);
                        space.change_Status(0, 10 - (int) (((double) ship.getShell() * 10.) / (double) ship.stats.getMax_shell()));
                        ship.sound_explosion();
                        if (ship.getShell() <= 0)
                            space.paint_game_over = true;
                    }
                }


            }
        }
    }
}
