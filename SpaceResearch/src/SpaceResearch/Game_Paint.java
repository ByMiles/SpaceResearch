package SpaceResearch;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

class Game_Paint extends JLabel {
    private Element_Ship ship;
    private Stats stats;
    private Element_Space space;
    private Game_Menu game_menu;
    private double sizefactor_x;
    private double sizefactor_y;

    private double i;

    private int width;
    private int height;

    Game_Paint(Element_Ship ship, Stats stats, Element_Space space, Game_Menu game_menu) {
        this.width = GUI.screenwidth;
        this.height = GUI.screenheight;
        sizefactor_x = GUI.sizefactor_x;
        sizefactor_y = GUI.sizefactor_y;

        this.setBounds(0, 0, GUI.screenwidth, GUI.screenheight);

        this.ship = ship;
        this.stats = stats;
        this.space = space;
        this.game_menu = game_menu;

        i = 0;

        this.setVisible(true);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (space.paint_space) {
            g.drawImage(space.basic_background, 0, 0, width, height, null);

            g.drawImage(space.space_1, space.space_places[0][0], space.space_places[0][1], width, height, null);
            g.drawImage(space.space_2, space.space_places[1][0], space.space_places[1][1], width, height, null);
            g.drawImage(space.space_3, space.space_places[2][0], space.space_places[2][1], width, height, null);
            g.drawImage(space.space_4, space.space_places[3][0], space.space_places[3][1], width, height, null);
            g.drawImage(space.space_5, space.space_places[4][0], space.space_places[4][1], width, height, null);
            g.drawImage(space.space_6, space.space_places[5][0], space.space_places[5][1], width, height, null);
            g.drawImage(space.space_7, space.space_places[6][0], space.space_places[6][1], width, height, null);
            g.drawImage(space.space_8, space.space_places[7][0], space.space_places[7][1], width, height, null);
            g.drawImage(space.space_9, space.space_places[8][0], space.space_places[8][1], width, height, null);


            if (space.planet_places[0][0] + Game_Panel.serosero[0] < width + 100 && space.planet_places[0][0] + Game_Panel.serosero[0] > -width - 100 && space.planet_places[0][1] + Game_Panel.serosero[1] < height + 300 && space.planet_places[0][1] + Game_Panel.serosero[1] > -height - 300) {

                AffineTransform et = new AffineTransform();
                et.translate(space.planet_places[0][0] + Game_Panel.serosero[0], space.planet_places[0][1] + Game_Panel.serosero[1]);
                et.rotate(i);
                et.translate(-space.planet_home.getWidth() / 2, -space.planet_home.getHeight() / 2);
                g2d.drawImage(space.planet_home, et, null);
                i += 0.001;
            }
            AffineTransform tx = new AffineTransform();
            tx.translate(Game_Panel.serosero[0], Game_Panel.serosero[1]);
            tx.rotate(Math.toRadians(22.5 * ship.direction));
            tx.translate(-ship.getRocketSize()[0] / 2, -ship.getRocketSize()[1] / 2);
            g2d.drawImage(ship.rocket(), tx, null);


            if (ship.getConnection() > 0 && !ship.lost_contact) {
                AffineTransform at = new AffineTransform();
                at.translate(this.getWidth() / 2, (this.getHeight() - 200) / 2);
                at.rotate(ship.home_planet_show_ankel);
                at.translate(-ship.planet_home_show.getWidth() / 2, -ship.planet_home_show.getHeight() / 2);
                g2d.drawImage(ship.planet_home_show, at, null);
            }

            for (int j = 0; j < space.rock_collection.length; j++) {
                AffineTransform et = new AffineTransform();
                et.translate(space.rock_collection[j].position_x + Game_Panel.serosero[0], space.rock_collection[j].position_y + Game_Panel.serosero[1]);
                et.rotate(space.rock_collection[j].rotation);
                et.translate(-space.rock_pics[space.rock_collection[j].type].getWidth() / 2, -space.rock_pics[space.rock_collection[j].type].getHeight() / 2);
                g2d.drawImage(space.rock_pics[space.rock_collection[j].type], et, null);
                space.rock_collection[j].rotation += space.rock_collection[j].rotation_speed;
            }

            for (int i = 0; i < stats.getStuff_collection_collection().get_it().size(); i++) {
                if (stats.getStuff_collection_collection().get_it().get(i).x - (space.planet_places[0][0] * -1) < 9000 && stats.getStuff_collection_collection().get_it().get(i).x - (space.planet_places[0][0] * -1) > -9000 && stats.getStuff_collection_collection().get_it().get(i).y - (space.planet_places[0][1] * -1) < 9000 && stats.getStuff_collection_collection().get_it().get(i).y - (space.planet_places[0][1] * -1) > -9000) {
                    for (int j = 0; j < stats.getStuff_collection_collection().get_it().get(i).stuff_collection.size(); j++) {
                        if (stats.getStuff_collection_collection().get_it().get(i).stuff_collection.get(j).stuff_x - (space.planet_places[0][0] * -1) < width * 1.5 && stats.getStuff_collection_collection().get_it().get(i).stuff_collection.get(j).stuff_x - (space.planet_places[0][0] * -1) > -width * 1.5 && stats.getStuff_collection_collection().get_it().get(i).stuff_collection.get(j).stuff_y - (space.planet_places[0][1] * -1) < height * 1.5 && stats.getStuff_collection_collection().get_it().get(i).stuff_collection.get(j).stuff_y - (space.planet_places[0][1] * -1) > -height * 1.5)
                            g.drawImage(space.stuff_pics[stats.getStuff_collection_collection().get_it().get(i).stuff_collection.get(j).stuff_type], stats.getStuff_collection_collection().get_it().get(i).stuff_collection.get(j).stuff_x - (space.planet_places[0][0] * -1), stats.getStuff_collection_collection().get_it().get(i).stuff_collection.get(j).stuff_y - (space.planet_places[0][1] * -1), 20, 20, null);
                    }
                }
            }

            if (space.paint_game_over)
                g.drawImage(space.game_over_pic, (int) ((double) width - 1000. * sizefactor_x) / 2, (int) ((double) height - 800. * sizefactor_y) / 2, (int) (1000. * sizefactor_x), (int) (600. * sizefactor_y), null);


            g.drawImage(space.status_background, 0, height - (int) (200. * sizefactor_y), width, (int) (200. * sizefactor_y), null);
            g.drawImage(space.stuff_pics[0], (int) (120. * sizefactor_x), height - (int) (190. * sizefactor_y), (int) (90. * sizefactor_x), (int) (90. * sizefactor_y), null);
            g.drawImage(space.stuff_pics[1], (int) (435. * sizefactor_x), height - (int) (190. * sizefactor_y), (int) (90. * sizefactor_x), (int) (90. * sizefactor_y), null);
            g.drawImage(space.stuff_pics[2], (int) (750. * sizefactor_x), height - (int) (190. * sizefactor_y), (int) (90. * sizefactor_x), (int) (90. * sizefactor_y), null);
            g.drawImage(space.stuff_pics[3], (int) (1075. * sizefactor_x), height - (int) (190. * sizefactor_y), (int) (90. * sizefactor_x), (int) (90. * sizefactor_y), null);
            g.drawImage(space.stuff_pics[4], (int) (1400. * sizefactor_x), height - (int) (190. * sizefactor_y), (int) (90. * sizefactor_x), (int) (90. * sizefactor_y), null);
            g.drawImage(space.stuff_pics[5], (int) (1735. * sizefactor_x), height - (int) (190. * sizefactor_y), (int) (90. * sizefactor_x), (int) (90. * sizefactor_y), null);
            g.drawImage(space.current_status[0], (int) (115. * sizefactor_x), height - (int) (110. * sizefactor_y), (int) (100. * sizefactor_x), (int) (100. * sizefactor_y), null);
            g.drawImage(space.current_status[1], (int) (430. * sizefactor_x), height - (int) (110. * sizefactor_y), (int) (100. * sizefactor_x), (int) (100. * sizefactor_y), null);
            g.drawImage(space.current_status[2], (int) (745. * sizefactor_x), height - (int) (110. * sizefactor_y), (int) (100. * sizefactor_x), (int) (100. * sizefactor_y), null);
            g.drawImage(space.current_status[3], (int) (1070. * sizefactor_x), height - (int) (110. * sizefactor_y), (int) (100. * sizefactor_x), (int) (100. * sizefactor_y), null);
            g.drawImage(space.current_status[4], (int) (1395. * sizefactor_x), height - (int) (110. * sizefactor_y), (int) (100. * sizefactor_x), (int) (100. * sizefactor_y), null);
            g.drawImage(space.current_status[5], (int) (1730. * sizefactor_x), height - (int) (110. * sizefactor_y), (int) (100. * sizefactor_x), (int) (100. * sizefactor_y), null);

            g.drawImage(space.outer_frame, 0, 0, width, height - (int) (190. * sizefactor_y), null);
        }

        if (game_menu.paint_game_menu) {
            g.drawImage(game_menu.menu_basic, 0, 0, width, height, null);
            g.drawImage(game_menu.menu_submenu[game_menu.current_submenu], (int) (252 * sizefactor_x), (int) (260 * sizefactor_y), (int) (1435 * sizefactor_x), (int) (580 * sizefactor_y), null);
            g.drawImage(game_menu.menu_frame, 0, 0, width, height, null);

        }
        repaint();
    }


    void resizepaint() {
        this.width = GUI.screenwidth;
        this.height = GUI.screenheight;
        this.setBounds(0, 0, this.width, this.height);

        sizefactor_x = GUI.sizefactor_x;
        sizefactor_y = GUI.sizefactor_y;
    }
}
