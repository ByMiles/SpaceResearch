package SpaceResearch;

class Thread_Load implements Runnable {
    Element_Ship ship;
    Element_Space space;

    Thread_Load(Element_Ship ship, Element_Space space) {
        this.ship = ship;
        this.space = space;
    }

    @Override
    public void run() {
        while (ship.flying && !space.paint_game_over) {
            int duration;
            if (ship.getMovement() * 5 > 500)
                duration = 500;
            else
                duration = ship.getMovement() * 5;

            try {
                Thread.sleep(duration);
            } catch (Exception ignored) {
            }

            int planet_x_now = space.planet_places[0][0];
            int planet_y_now = space.planet_places[0][1];

            int try_x = -(planet_x_now - planet_x_now % 6000);
            int try_y = -(planet_y_now - planet_y_now % 6000);

            int try_x_plus_6000 = try_x + 6000;
            int try_x_minu_6000 = try_x - 6000;

            int try_y_plus_6000 = try_y + 6000;
            int try_y_minu_6000 = try_y - 6000;

            boolean existing[] = new boolean[8];

            for (Stuff_Collection collection : ship.stats.getStuff_collection_collection().get_it()) {
                if (try_x == collection.x && try_y_minu_6000 == collection.y) {
                    existing[0] = true;
                }
                if (try_x_plus_6000 == collection.x && try_y_minu_6000 == collection.y) {
                    existing[1] = true;
                }
                if (try_x_plus_6000 == collection.x && try_y == collection.y) {
                    existing[2] = true;
                }
                if (try_x_plus_6000 == collection.x && try_y_plus_6000 == collection.y) {
                    existing[3] = true;
                }
                if (try_x == collection.x && try_y_plus_6000 == collection.y) {
                    existing[4] = true;
                }
                if (try_x_minu_6000 == collection.x && try_y_plus_6000 == collection.y) {
                    existing[5] = true;
                }
                if (try_x_minu_6000 == collection.x && try_y == collection.y) {
                    existing[6] = true;
                }
                if (try_x_minu_6000 == collection.x && try_y_minu_6000 == collection.y) {
                    existing[7] = true;
                }

            }


            if (!existing[0]) {
                ship.stats.getStuff_collection_collection().add_new_collection(try_x, try_y_minu_6000);


            }
            if (!existing[1]) {
                ship.stats.getStuff_collection_collection().add_new_collection(try_x_plus_6000, try_y_minu_6000);

            }
            if (!existing[2]) {
                ship.stats.getStuff_collection_collection().add_new_collection(try_x_plus_6000, try_y);

            }
            if (!existing[3]) {
                ship.stats.getStuff_collection_collection().add_new_collection(try_x_plus_6000, try_y_plus_6000);

            }
            if (!existing[4]) {
                ship.stats.getStuff_collection_collection().add_new_collection(try_x, try_y_plus_6000);

            }
            if (!existing[5]) {
                ship.stats.getStuff_collection_collection().add_new_collection(try_x_minu_6000, try_y_plus_6000);

            }
            if (!existing[6]) {
                ship.stats.getStuff_collection_collection().add_new_collection(try_x_minu_6000, try_y);

            }
            if (!existing[7]) {
                ship.stats.getStuff_collection_collection().add_new_collection(try_x_minu_6000, try_y_minu_6000);


            }

            //System.out.println(ship.getLoad() + 1 * ship.stats.getSpace_per_load());
            if (ship.getLoad() + ship.stats.getSpace_per_load() <= ship.stats.getMax_load()) {

                java.util.List<Stuff_Collection> stu_co = ship.stats.getStuff_collection_collection().get_it();
                int position_x = ship.position[0];
                int position_y = ship.position[1];

                for (Stuff_Collection aStu_co : stu_co) {
                    if (Math.abs(aStu_co.x - position_x) < 3000 && Math.abs(aStu_co.y - position_y) < 3000) {

                        for (int j = 0; j < aStu_co.stuff_collection.size(); j++) {
                            if (aStu_co.stuff_collection.get(j).stuff_x - position_x - Game_Panel.serosero[0] < 5 * ship.stats.getRadius_of_catch() && aStu_co.stuff_collection.get(j).stuff_x - position_x - Game_Panel.serosero[0] > -7 * ship.stats.getRadius_of_catch() && aStu_co.stuff_collection.get(j).stuff_y - position_y - Game_Panel.serosero[1] < 5 * ship.stats.getRadius_of_catch() && aStu_co.stuff_collection.get(j).stuff_y - position_y - Game_Panel.serosero[1] > -7 * ship.stats.getRadius_of_catch()) {
                                ship.collected_stuff.add(aStu_co.stuff_collection.get(j).stuff_type);
                                aStu_co.stuff_collection.remove(j);
                                ship.increase_load(1);
                                space.change_Status(1, 10 - (int) ((double) ship.getLoad() * 10. / (double) ship.stats.getMax_load()));
                                ship.sound_collected();
                                j--;
                            }
                        }
                    }
                }
            }
        }
    }
}


