package SpaceResearch;

class Thread_Connection implements Runnable {
    Element_Ship ship;
    Element_Space space;

    Thread_Connection(Element_Ship ship, Element_Space space) {
        this.ship = ship;
        this.space = space;
    }

    @Override
    public void run() {
        while (ship.flying && !space.paint_game_over) {
            try {
                Thread.sleep(50);
            } catch (Exception ignored) {
            }

            double distance = Math.sqrt(Math.pow((double) ship.position[0], 2.) + Math.pow((double) ship.position[1], 2.));
            ship.setConnection(distance);
            if (distance < ship.stats.getMax_connection()) {
                double skalar = (75. * (double) (ship.position[0])) + (75. * ((double) (ship.position[1])));
                double length_a = Math.sqrt(75. * 75. + 75. * 75.);
                double length_b = Math.sqrt((double) (ship.position[0]) * (double) (ship.position[0]) + (double) (ship.position[1]) * (double) (ship.position[1]));


                ship.home_planet_show_ankel = (Math.acos(skalar / (length_a * length_b))) + ship.getConnectionAccuracy();
                if ((double) (ship.position[0]) > (double) (ship.position[1])) {
                    ship.home_planet_show_ankel *= -1;
                }
                space.change_Status(5, 10 - (int) (((double) ship.getConnection() * 10. + 100) / (double) ship.stats.getMax_connection()));
            } else {
                space.change_Status(5, 10);
            }

            if (ship.lose_contact()) {
                try {
                    Thread.sleep(ship.getLose_duration());
                } catch (Exception ignored) {
                }
                ship.lost_contact = false;
            }
        }
    }
}
