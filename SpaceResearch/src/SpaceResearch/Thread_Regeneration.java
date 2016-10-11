package SpaceResearch;

class Thread_Regeneration implements Runnable {
    Element_Ship ship;
    Element_Space space;

    private double buffered_shell;
    private double buffered_supply;

    Thread_Regeneration(Element_Ship ship, Element_Space space) {
        this.ship = ship;
        this.space = space;
        buffered_shell = 0.;
        buffered_supply = 0.;
    }

    @Override
    public void run() {
        while (ship.flying && !space.paint_game_over) {
            try {
                Thread.sleep(5000);
            } catch (Exception ignored) {
            }

            buffered_shell += ship.stats.getMax_shell() * ((double) ship.stats.getShell_regeneration() / 500.);
            ship.increaseShell((int) buffered_shell);
            buffered_shell -= (double) ((int) buffered_shell);
            space.change_Status(0, 10 - (int) (((double) ship.getShell() * 10.) / (double) ship.stats.getMax_shell()));


            buffered_supply += ship.stats.getMax_supply() * ((double) ship.stats.getSupply_regeneration() / 2000.);
            ship.increaseSupply((int) buffered_supply);
            buffered_supply -= (double) ((int) buffered_supply);
            space.change_Status(2, (int) (10 - (double) ship.getSupply() * 10. / (double) ship.stats.getMax_supply()));

        }
    }
}
