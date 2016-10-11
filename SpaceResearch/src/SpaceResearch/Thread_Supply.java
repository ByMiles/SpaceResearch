package SpaceResearch;

class Thread_Supply implements Runnable {
    Element_Ship ship;
    Element_Space space;

    Thread_Supply(Element_Ship ship, Element_Space space) {
        this.ship = ship;
        this.space = space;
    }

    @Override
    public void run() {
        while (ship.flying && !space.paint_game_over) {
            try {
                Thread.sleep(10000);
            } catch (Exception ignored) {
            }

            ship.consum_supply();
            ship.wasteSupply();
            space.change_Status(2, (int) (10 - (double) ship.getSupply() * 10. / (double) ship.stats.getMax_supply()));
            if (ship.getSupply() <= 0)
                space.paint_game_over = true;
        }

    }
}
