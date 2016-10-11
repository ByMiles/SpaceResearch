package SpaceResearch;

import java.util.ArrayList;

class Stats {
    int filenumber;
    String name;
    private int collected_stuff[];
    private int[] achievements;
    private int[][] achievement_level;

    private Stuff_Collection_Collection stuff_collection_collection;

    private String[] ranks;

    private int[][][] levels;


    Stats(int filenumber, String name) {
        this.filenumber = filenumber;
        this.name = name;
        collected_stuff = new int[]{0, 0, 0, 0, 0, 0};
        achievements = new int[10];                       //[
        stuff_collection_collection = new Stuff_Collection_Collection(true);

        levels = new int[6][4][5];

        // max_shell:
        levels[0][0][0] = 0; // next level
        levels[0][0][1] = 1; // first coast
        levels[0][0][2] = 2; // increase coast
        levels[0][0][3] = 1; // increase stats
        levels[0][0][4] = 100; // value

        // collision avoidance:
        levels[0][1][0] = 0; // next level
        levels[0][1][1] = 10; // first coast
        levels[0][1][2] = 4; // increase coast
        levels[0][1][3] = 2; // increase stats
        levels[0][1][4] = 1; // value

        // regeneration:
        levels[0][2][0] = 0; // next level
        levels[0][2][1] = 20; // first coast
        levels[0][2][2] = 4; // increase coast
        levels[0][2][3] = 1; // increase stats
        levels[0][2][4] = 0; // value

        // damage from start:
        levels[0][3][0] = 0; // next level
        levels[0][3][1] = 5; // first coast
        levels[0][3][2] = 5; // increase coast
        levels[0][3][3] = 5; // increase stats
        levels[0][3][4] = 1; // value

        // max load:
        levels[1][0][0] = 0; // next level
        levels[1][0][1] = 1; // first coast
        levels[1][0][2] = 3; // increase coast
        levels[1][0][3] = 500; // increase stats
        levels[1][0][4] = 1000; // value

        // space per load:
        levels[1][1][0] = 0; // next level
        levels[1][1][1] = 10; // first coast
        levels[1][1][2] = 4; // increase coast
        levels[1][1][3] = 1; // increase stats
        levels[1][1][4] = 100; // value

        // radius_of_catch:
        levels[1][2][0] = 0; // next level
        levels[1][2][1] = 10; // first coast
        levels[1][2][2] = 5; // increase coast
        levels[1][2][3] = 3; // increase stats
        levels[1][2][4] = 5; // value

        // chance of lost:
        levels[1][3][0] = 0; // next level
        levels[1][3][1] = 10; // first coast
        levels[1][3][2] = 2; // increase coast
        levels[1][3][3] = 10; // increase stats
        levels[1][3][4] = 1; // value

        // max supply:
        levels[2][0][0] = 0; // next level
        levels[2][0][1] = 1; // first coast
        levels[2][0][2] = 2; // increase coast
        levels[2][0][3] = 100; // increase stats
        levels[2][0][4] = 100; // value

        // quality:
        levels[2][1][0] = 0; // next level
        levels[2][1][1] = 10; // first coast
        levels[2][1][2] = 4; // increase coast
        levels[2][1][3] = 1; // increase stats
        levels[2][1][4] = 1; // value

        // regeneration:
        levels[2][2][0] = 0; // next level
        levels[2][2][1] = 20; // first coast
        levels[2][2][2] = 4; // increase coast
        levels[2][2][3] = 1; // increase stats
        levels[2][2][4] = 0; // value

        // chance of waste:
        levels[2][3][0] = 0; // next level
        levels[2][3][1] = 10; // first coast
        levels[2][3][2] = 2; // increase coast
        levels[2][3][3] = 1; // increase stats
        levels[2][3][4] = 1; // value

        // max fuel:
        levels[3][0][0] = 0; // next level
        levels[3][0][1] = 1; // first coast
        levels[3][0][2] = 2; // increase coast
        levels[3][0][3] = 1; // increase stats
        levels[3][0][4] = 100; // value

        // consume speed up:
        levels[3][1][0] = 0; // next level
        levels[3][1][1] = 5; // first coast
        levels[3][1][2] = 2; // increase coast
        levels[3][1][3] = 1; // increase stats
        levels[3][1][4] = 100; // value

        // consume slow down:
        levels[3][2][0] = 0; // next level
        levels[3][2][1] = 5; // first coast
        levels[3][2][2] = 2; // increase coast
        levels[3][2][3] = 1; // increase stats
        levels[3][2][4] = 100; // value

        // consume steering:
        levels[3][3][0] = 0; // next level
        levels[3][3][1] = 10; // first coast
        levels[3][3][2] = 2; // increase coast
        levels[3][3][3] = 1; // increase stats
        levels[3][3][4] = 100; // value

        // max speed:
        levels[4][0][0] = 0; // next level
        levels[4][0][1] = 1; // first coast
        levels[4][0][2] = 2; // increase coast
        levels[4][0][3] = 1; // increase stats
        levels[4][0][4] = 3; // value

        // speed up:
        levels[4][1][0] = 0; // next level
        levels[4][1][1] = 10; // first coast
        levels[4][1][2] = 2; // increase coast
        levels[4][1][3] = 5; // increase stats
        levels[4][1][4] = 10; // value

        // slow down:
        levels[4][2][0] = 0; // next level
        levels[4][2][1] = 5; // first coast
        levels[4][2][2] = 3; // increase coast
        levels[4][2][3] = 10; // increase stats
        levels[4][2][4] = 20; // value

        // steering:
        levels[4][3][0] = 0; // next level
        levels[4][3][1] = 10; // first coast
        levels[4][3][2] = 3; // increase coast
        levels[4][3][3] = 10; // increase stats
        levels[4][3][4] = 10; // value

        // max contact:
        levels[5][0][0] = 0; // next level
        levels[5][0][1] = 1; // first coast
        levels[5][0][2] = 1; // increase coast
        levels[5][0][3] = 1000; // increase stats
        levels[5][0][4] = 1000; // value

        // chance of disconnect:
        levels[5][1][0] = 0; // next level
        levels[5][1][1] = 10; // first coast
        levels[5][1][2] = 2; // increase coast
        levels[5][1][3] = 1; // increase stats
        levels[5][1][4] = 5; // value

        // disconnect duration:
        levels[5][2][0] = 0; // next level
        levels[5][2][1] = 10; // first coast
        levels[5][2][2] = 2; // increase coast
        levels[5][2][3] = 1; // increase stats
        levels[5][2][4] = 5; // value

        // quality of direction:
        levels[5][3][0] = 0; // next level
        levels[5][3][1] = 10; // first coast
        levels[5][3][2] = 3; // increase coast
        levels[5][3][3] = 1; // increase stats
        levels[5][3][4] = 1; // value

        ranks = new String[]{"EARTHLUBBER", "SPACENOOB", "FLYINGFART", "SPACEROOKI", "MOONMAN", "SPACEMARINE", "SPACESEAL", "HIGHFLYER", "MANKINDS HOPE", "UNIVERSEPIONEER", "GALAXY EXPLORER", "ADDICTED"};

        achievement_levels();
    }

    Stats(int filenumber, String name, int[][][] levels, int[] collected_stuff, int[] achievements, Stuff_Collection_Collection stuff_collection_collection) {
        this.filenumber = filenumber;
        this.name = name;
        this.levels = levels;
        this.collected_stuff = collected_stuff;
        this.achievements = achievements;
        this.stuff_collection_collection = stuff_collection_collection;


        ranks = new String[]{"EARTHLUBBER", "SPACENOOB", "FLYINGFART", "SPACEROOKI", "MOONMAN", "SPACEMARINE", "SPACESEAL", "HIGHFLYER", "MANKINDS HOPE", "UNIVERSEPIONEER", "GALAXY EXPLORER", "ADDICTED"};

        achievement_levels();
    }

    int getMax_shell() {
        return levels[0][0][4];
    }

    int getChance_of_avoidance() {
        return levels[0][1][4];
    }

    int getShell_regeneration() {
        return levels[0][2][4];
    }

    int getStarting_damage() {
        return levels[0][3][4];
    }

    int getMax_load() {
        return levels[1][0][4];
    }

    int getSpace_per_load() {
        return levels[1][1][4];
    }

    int getRadius_of_catch() {
        return levels[1][2][4];
    }

    private int getChance_of_lost() {
        return levels[1][3][4];
    }

    int getMax_supply() {
        return levels[2][0][4];
    }

    int getSupply_quality() {
        return levels[2][1][4];
    }

    int getSupply_regeneration() {
        return levels[2][2][4];
    }

    int getChance_of_waste() {
        return levels[2][3][4];
    }

    int getMax_fuel() {
        return levels[3][0][4];
    }

    int getFuel_consume_speed_up() {
        return levels[3][1][4];
    }

    int getFuel_consume_speed_down() {
        return levels[3][2][4];
    }

    int getFuel_consume_steering() {
        return levels[3][3][4];
    }

    int getMax_speed() {
        return levels[4][0][4];
    }

    int getSpeed_up() {
        return levels[4][1][4];
    }

    int getSlow_down() {
        return levels[4][2][4];
    }

    int getSteering() {
        return levels[4][3][4];
    }

    int getMax_connection() {
        return levels[5][0][4];
    }

    int getChance_of_disconnect() {
        return levels[5][1][4];
    }

    int getDuration_of_disconnect() {
        return levels[5][2][4];
    }

    int getQuality_of_direction() {
        return levels[5][3][4];
    }

    int[][][] getLevels() {
        return levels;
    }

    int getPocket(int typ) {
        return collected_stuff[typ];
    }

    Stuff_Collection_Collection getStuff_collection_collection() {
        return this.stuff_collection_collection;
    }


    boolean improve(int current_submenu, int improve_typ) {
        if (levels[current_submenu][improve_typ][0] == 0) {
            if (levels[current_submenu][improve_typ][1] <= collected_stuff[current_submenu]) {
                collected_stuff[current_submenu] -= levels[current_submenu][improve_typ][1];
                levels[current_submenu][improve_typ][0]++;

                levels[current_submenu][improve_typ][4] += levels[current_submenu][improve_typ][3];
                achievements[6]++;

                return true;
            }
        } else if (levels[current_submenu][improve_typ][0] * levels[current_submenu][improve_typ][1] * levels[current_submenu][improve_typ][2] <= collected_stuff[current_submenu]) {
            collected_stuff[current_submenu] -= levels[current_submenu][improve_typ][0] * levels[current_submenu][improve_typ][1] * levels[current_submenu][improve_typ][2];
            levels[current_submenu][improve_typ][0]++;
            levels[current_submenu][improve_typ][4] += levels[current_submenu][improve_typ][3];
            achievements[6]++;

            return true;
        }

        return false;
    }


    void sort_stuff(ArrayList<Integer> collected_stuff) {
        achievements[0] = 0;
        achievements[1] = 0;
        for (int i = 0; i < collected_stuff.size(); ) {
            double random = Math.random() - (0.5 - (0.5 * (double) getChance_of_lost() / (5. + (double) getChance_of_lost())));

            if (random > 0) {
                this.collected_stuff[collected_stuff.get(i)]++;
                this.achievements[0]++;
                this.achievements[5]++;
            } else {
                achievements[1]++;
            }

            collected_stuff.remove(i);
        }
    }

    private String getRank(int i) {
        return ranks[i];
    }

    void create_Archivments(Element_Ship ship) {
        achievements[2] = getMax_supply() - ship.getSupply();
        achievements[3] = getMax_fuel() - ship.getFuel();
        achievements[4] = getMax_shell() - ship.getShell();

        achievements[7] += achievements[2];
    }

    String getArchivment_x(int x) {
        return String.valueOf(achievements[x]);
    }


    int[] getCollected_stuff() {
        return collected_stuff;
    }

    private void achievement_levels() {
        achievement_level = new int[5][100];

        achievement_level[0][0] = 5;
        achievement_level[1][0] = 3;
        achievement_level[2][0] = 50;
        achievement_level[4][0] = 3;

        for (int i = 1; i < achievement_level[0].length; i++) {
            if (achievement_level[0][i - 1] < 100) {
                achievement_level[0][i] = achievement_level[0][i - 1] + 5 * (i + 1);
                continue;
            }
            if (achievement_level[0][i - 1] < 500) {
                achievement_level[0][i] = achievement_level[0][i - 1] + 10 * (i + 1);
                continue;
            }

            achievement_level[0][i] = achievement_level[0][i - 1] + 15 * (i + 1);
        }

        for (int i = 1; i < achievement_level[1].length; i++) {
            achievement_level[1][i] = achievement_level[1][i - 1] + 5;
            achievement_level[4][i] = achievement_level[4][i - 1] + (i + 1) * 4;
        }

        for (int i = 1; i < achievement_level[2].length; i++) {
            if (achievement_level[2][i - 1] < 1000) {
                achievement_level[2][i] = achievement_level[2][i - 1] + 50 * (i + 1);
                continue;
            }
            if (achievement_level[2][i - 1] < 5000) {
                achievement_level[2][i] = achievement_level[2][i - 1] + 100 * (i + 1);
                continue;
            }

            achievement_level[2][i] = achievement_level[2][i - 1] + 150 * (i + 1);
        }

        for (int i = 0; i < achievement_level[3].length; i++) {
            achievement_level[3][i] = i + 1;
        }


    }

    String getAchieveText(int achievement) {
        String text = "";

        for (int i = 0; i < achievement_level[achievement - 5].length; i++) {
            if (achievements[achievement] < achievement_level[achievement - 5][i]) {
                text = String.valueOf(i) + " (" + achievements[achievement] + ")";
                break;
            }
        }

        return text;
    }

    String getRankLevel() {
        String text;
        int lvl = 0;
        int rank = 0;

        for (int achievement = 0; achievement < achievements.length - 6; achievement++) {
            for (int i = 0; i < achievement_level[achievement].length; i++) {
                if (achievements[achievement] < achievement_level[achievement][i]) {
                    lvl += i;
                    break;
                }
            }
        }

        achievements[9] = lvl;

        for (int i = 0; i < achievement_level[4].length; i++) {
            if (achievements[9] < achievement_level[4][i] || i == 10) {
                rank = i;
                break;
            }
        }

        text = getRank(rank);

        return text;

    }
}
