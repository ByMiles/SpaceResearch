package SpaceResearch;

class Thread_SizeObserver implements Runnable {
    private GUI gui;

    private int state_old;

    Thread_SizeObserver(GUI gui) {
        this.gui = gui;

        GUI.screenwidth = (int) gui.getContentPane().getSize().getWidth();
        GUI.screenheight = (int) gui.getContentPane().getSize().getHeight();

        state_old = gui.getExtendedState();
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(50);
            } catch (Exception ignored) {
            }

            int width_new = (int) gui.getContentPane().getSize().getWidth();
            int height_new = (int) gui.getContentPane().getSize().getHeight();
            int state_new = gui.getExtendedState();


            if (GUI.screenwidth != width_new || GUI.screenheight != height_new || state_old != state_new) {
                GUI.screenwidth = width_new;
                GUI.screenheight = height_new;
                state_old = state_new;

                GUI.setSizefactors();

                if (gui.active_main)
                    gui.main_panel.resizepanel();

                if (gui.active_game)
                    gui.game_panel.resizepanel();

            }

        }
    }
}
