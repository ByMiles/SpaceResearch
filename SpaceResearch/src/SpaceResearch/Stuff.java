package SpaceResearch;

class Stuff {
    int stuff_x;
    int stuff_y;
    int stuff_type;

    Stuff(boolean create, int stuff_type, int x, int y) {
        if (create) {
            stuff_x = x + (int) ((Math.random()) * 6000) - 3000 + Game_Panel.serosero[0];

            stuff_y = y + (int) ((Math.random()) * 6000) - 3000 + Game_Panel.serosero[1];

            this.stuff_type = stuff_type;
        } else {
            this.stuff_type = stuff_type;
            this.stuff_x = x;
            this.stuff_y = y;
        }

    }

}
