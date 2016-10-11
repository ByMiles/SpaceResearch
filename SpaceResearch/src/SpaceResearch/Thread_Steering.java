package SpaceResearch;

class Thread_Steering implements Runnable {
    Element_Ship ship;
    Element_Space space;

    Thread_Steering(Element_Ship ship, Element_Space space) {
        this.ship = ship;
        this.space = space;
    }

    @Override
    public void run() {
        while (ship.flying && !space.paint_game_over && ship.getFuel() > 0) {
            try {
                Thread.sleep(ship.getDirectionspeed());
            } catch (Exception ignored) {
            }

            if (ship.turn_left || ship.turn_right) {

                ship.setDirection();
                ship.consum_fuel_accelerate();
                space.change_Status(3, 10 - (int) ((((double) ship.getFuel()) * 10. + 10.) / (double) ship.stats.getMax_fuel()));
            }

        }
    }
}
