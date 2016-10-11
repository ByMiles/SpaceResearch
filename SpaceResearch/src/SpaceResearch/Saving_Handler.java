package SpaceResearch;


import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class Saving_Handler {
    static boolean[] played_sounds = new boolean[6];
    // all, mission, collect, collision, speed up, slow down


    static void load_options() {
        String line;
        File file;
        String filename = "";
        try {
            file = new File(Saving_Handler.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            file = file.getParentFile();
            filename = String.valueOf(file) + "\\Savings\\Saving_options.txt";

            FileInputStream is = new FileInputStream(filename);
            InputStreamReader isr = new InputStreamReader( is, "UTF-8" );
            BufferedReader in = new BufferedReader(isr);

            for (int i = 0; (line = in.readLine()) != null; i++) {
                played_sounds[i] = Boolean.parseBoolean(line);
            }
            is.close();
            isr.close();
            in.close();
        } catch (Exception e) {
            System.out.println("Fehler beim optionen laden: " + filename);
        }
    }

    static void save_options() {
        File file;
        try {
            file = new File(Saving_Handler.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            file = file.getParentFile();
            String filename = String.valueOf(file) + "\\Savings\\Saving_options.txt";

            BufferedWriter save_it = new BufferedWriter(new FileWriter(filename));

            for (int i = 0; i < played_sounds.length - 1; i++) {
                save_it.write(String.valueOf(played_sounds[i]));
                save_it.newLine();
            }
            save_it.write(String.valueOf(played_sounds[played_sounds.length - 1]));
            save_it.close();
        } catch (Exception e) {
            System.out.println("Fehler beim speichern");
        }
    }

    static Stats new_game(int filenumber, String name) {
        Stats stats = new Stats(filenumber, name);

        save_game(filenumber, stats);

        return stats;
    }

    static boolean save_game(int filenumber, Stats stats) {
        String name = stats.name;

        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        String levels = "";

        for (int i = 0; i < stats.getLevels().length; i++) {
            for (int j = 0; j < stats.getLevels()[i].length; j++) {
                for (int k = 0; k < stats.getLevels()[i][j].length; k++) {
                    levels += String.valueOf(stats.getLevels()[i][j][k]) + " ";
                }
            }
        }

        String collected_stuff = String.valueOf(stats.getCollected_stuff()[0]) + " " + String.valueOf(stats.getCollected_stuff()[1]) + " " + String.valueOf(stats.getCollected_stuff()[2]) + " " + String.valueOf(stats.getCollected_stuff()[3]) + " " + String.valueOf(stats.getCollected_stuff()[4]) + " " + String.valueOf(stats.getCollected_stuff()[5]);
        String archivements = stats.getArchivment_x(0) + " " + stats.getArchivment_x(1) + " " + stats.getArchivment_x(2) + " " + stats.getArchivment_x(3) + " " + stats.getArchivment_x(4) + " " + stats.getArchivment_x(5) + " " + stats.getArchivment_x(6) + " " + stats.getArchivment_x(7) + " " + stats.getArchivment_x(8) + " " + stats.getArchivment_x(9);

        String stuff_collection = "";

        Stuff_Collection_Collection stu_co_co = stats.getStuff_collection_collection();

        for (Stuff_Collection stu_co : stu_co_co.get_it()) {
            stuff_collection += String.valueOf(stu_co.x) + " " + String.valueOf(stu_co.y) + " ";

            for (Stuff stu : stu_co.stuff_collection) {
                stuff_collection += String.valueOf(stu.stuff_type) + " " + String.valueOf(stu.stuff_x) + " " + String.valueOf(stu.stuff_y) + " ";
            }
            stuff_collection += "!";
        }
        stuff_collection += "!";

        File file;

        try {
            file = new File(Saving_Handler.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            file = file.getParentFile();
            String filename = String.valueOf(file) + "\\Savings\\Saving_"+ filenumber +".txt";

            BufferedWriter save_it = new BufferedWriter(new FileWriter(filename));


            save_it.write(name);  // 0
            save_it.newLine();
            save_it.write(date);  // 1
            save_it.newLine();
            save_it.write(levels);  // 2
            save_it.newLine();
            save_it.write(collected_stuff);  // 3
            save_it.newLine();
            save_it.write(archivements); // 4
            save_it.newLine();
            save_it.write(stuff_collection); // 5

            save_it.close();
        } catch (Exception e) {
            return false;
        }

        return true;

    }

    static Stats load_game(int filenumber) {
        String name;
        int[][][] levels;
        String level_s;
        int[] collected_stuff;
        String co_stu_s;
        int[] archivments;
        String archivments_s;


        String file_adding = "\\Savings\\Saving_" + filenumber + ".txt";
        ArrayList<String> loadlist = new ArrayList<>();
        File file;

        String line;
        try {
            file = new File(Saving_Handler.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            file = file.getParentFile();
            String filename = String.valueOf(file) + file_adding;

            FileInputStream is = new FileInputStream(filename);
            InputStreamReader isr = new InputStreamReader( is, "UTF-8" );
            BufferedReader in = new BufferedReader(isr);

            for (; (line = in.readLine()) != null; ) {
                loadlist.add(line);
                }

            is.close();
            isr.close();
            in.close();
        } catch (Exception e) {
            System.out.println("Fehler beim savings laden");
        }

        name = loadlist.get(0);

        levels = new int[6][4][5];
        level_s = loadlist.get(2);
        int next_int;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 5; k++) {
                    for (int l = 0; l < level_s.length(); l++) {
                        if (level_s.substring(l, l + 1).equals(" ")) {
                            next_int = Integer.parseInt(level_s.substring(0, l));
                            levels[i][j][k] = next_int;
                            level_s = level_s.substring(l + 1);
                            l = level_s.length();
                        }

                    }
                }
            }
        }

        collected_stuff = new int[6];
        co_stu_s = loadlist.get(3);

        for (int i = 0; i < collected_stuff.length; i++) {
            for (int l = 0; l < co_stu_s.length(); l++) {
                if (co_stu_s.substring(l, l + 1).equals(" ")) {
                    next_int = Integer.parseInt(co_stu_s.substring(0, l));
                    collected_stuff[i] = next_int;
                    co_stu_s = co_stu_s.substring(l + 1);
                    l = co_stu_s.length();
                }

            }
        }

        archivments = new int[10];
        archivments_s = loadlist.get(4);

        for (int i = 0; i < archivments.length; i++) {
            for (int l = 0; l < archivments_s.length(); l++) {
                if (archivments_s.substring(l, l + 1).equals(" ")) {
                    next_int = Integer.parseInt(archivments_s.substring(0, l));
                    archivments[i] = next_int;
                    archivments_s = archivments_s.substring(l + 1);
                    l = archivments_s.length();
                }

            }
        }

        Stuff_Collection_Collection stuff_collection_collection = new Stuff_Collection_Collection(false);
        String stu_co_co_s = loadlist.get(5);

        Stuff_Collection stuffcollection;
        Stuff stuff;

        String stu_co_x_s = "";
        String stu_co_y_s = "";

        String stuff_type_s = "";
        String stuff_x_s = "";
        String stuff_y_s = "";

        while (!stu_co_co_s.substring(0, 1).equals("!")) {
            for (int l = 0; l < stu_co_co_s.length(); l++) {
                if (stu_co_co_s.substring(l, l + 1).equals(" ")) {
                    stu_co_x_s = stu_co_co_s.substring(0, l);
                    stu_co_co_s = stu_co_co_s.substring(l + 1);
                    l = stu_co_co_s.length();
                }

            }

            for (int l = 0; l < stu_co_co_s.length(); l++) {
                if (stu_co_co_s.substring(l, l + 1).equals(" ")) {
                    stu_co_y_s = stu_co_co_s.substring(0, l);
                    stu_co_co_s = stu_co_co_s.substring(l + 1);
                    l = stu_co_co_s.length();
                }

            }
            stuffcollection = new Stuff_Collection(false, Integer.parseInt(stu_co_x_s), Integer.parseInt(stu_co_y_s));

            while (!stu_co_co_s.substring(0, 1).equals("!")) {
                for (int l = 0; l < stu_co_co_s.length(); l++) {
                    if (stu_co_co_s.substring(l, l + 1).equals(" ")) {
                        stuff_type_s = stu_co_co_s.substring(0, l);
                        stu_co_co_s = stu_co_co_s.substring(l + 1);
                        l = stu_co_co_s.length();
                    }
                }

                for (int l = 0; l < stu_co_co_s.length(); l++) {
                    if (stu_co_co_s.substring(l, l + 1).equals(" ")) {
                        stuff_x_s = stu_co_co_s.substring(0, l);
                        stu_co_co_s = stu_co_co_s.substring(l + 1);
                        l = stu_co_co_s.length();
                    }
                }

                for (int l = 0; l < stu_co_co_s.length(); l++) {
                    if (stu_co_co_s.substring(l, l + 1).equals(" ")) {
                        stuff_y_s = stu_co_co_s.substring(0, l);
                        stu_co_co_s = stu_co_co_s.substring(l + 1);
                        l = stu_co_co_s.length();
                    }
                }

                stuff = new Stuff(false, Integer.parseInt(stuff_type_s), Integer.parseInt(stuff_x_s), Integer.parseInt(stuff_y_s));
                stuffcollection.stuff_collection.add(stuff);
            }

            stuff_collection_collection.add_old_collection(stuffcollection);
            stu_co_co_s = stu_co_co_s.substring(1);
        }
        return new Stats(filenumber, name, levels, collected_stuff, archivments, stuff_collection_collection);
    }

    static String catch_name(int filenumber) {

        File file;
        String file_adding = "\\Savings\\Saving_" + filenumber + ".txt";
        String c_name = "<html><body><center>";
        String filename ="";

        try {
            file = new File(Saving_Handler.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            file = file.getParentFile();
            filename = String.valueOf(file) + file_adding;
            FileInputStream is = new FileInputStream(filename);
            InputStreamReader isr = new InputStreamReader( is, "UTF-8" );
            BufferedReader in = new BufferedReader(isr);
            c_name += in.readLine() + "<br>" + in.readLine() + "</center></body></html>";

            is.close();
            isr.close();
            in.close();

        } catch (Exception e) {
            System.out.println("Fehler beim Name laden: "+ filename );
        }

        return c_name;
    }

    static Font ARDESTINE() {
        float size;

        if (GUI.sizefactor_x < GUI.sizefactor_y) {
            size = (float) (48. * GUI.sizefactor_x);
        } else
            size = (float) (48. * GUI.sizefactor_y);

        Font ARD = new Font("SERIF", Font.BOLD, 32);


        try {
            ARD = Font.createFont(Font.TRUETYPE_FONT, Saving_Handler.class.getResourceAsStream("/Font_ARDESTINE.ttf")).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, Saving_Handler.class.getResourceAsStream("/Font_ARDESTINE.ttf")));
        } catch (Exception e) {
            System.out.println("schrift nicht einlesbar");
        }

        return ARD;
    }
}
