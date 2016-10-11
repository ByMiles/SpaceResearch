package SpaceResearch;

class Thread_Accelerate implements Runnable {
    Element_Ship ship;
    Element_Space space;

    Thread_Accelerate(Element_Ship ship, Element_Space space) {
        this.ship = ship;
        this.space = space;
    }

    @Override
    public void run() {
        while (ship.flying && !space.paint_game_over && ship.getFuel() > 0) {
            try {
                Thread.sleep(500);
            } catch (Exception ignored) {
            }

            if (ship.speed_up) {
                ship.increase_movementspeed();
                if (ship.getMovementspeed() <= 1.)
                    space.change_Status(4, 10);
                else
                    space.change_Status(4, 10 - (int) (ship.getMovementspeed() * 10. / (double) ship.stats.getMax_speed()));
                ship.consum_fuel_speed_up();
                space.change_Status(3, 10 - (int) ((((double) ship.getFuel()) * 10. + 10.) / (double) ship.stats.getMax_fuel()));
                ship.sound_speed_up();
            }

            if (ship.slow_down) {
                ship.decrease_movementspeed();
                if (ship.getMovementspeed() <= 1.)
                    space.change_Status(4, 10);
                else
                    space.change_Status(4, 10 - (int) (ship.getMovementspeed() * 10. / (double) ship.stats.getMax_speed()));
                ship.consum_fuel_speed_down();
                space.change_Status(3, 10 - (int) ((((double) ship.getFuel()) * 10. + 10.) / (double) ship.stats.getMax_fuel()));
                ship.sound_slow_down();
            }

        }

    }
}
