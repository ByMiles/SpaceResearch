package SpaceResearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Stuff_Collection {
    List<Stuff> stuff_collection;
    int x, y;

    Stuff_Collection(boolean create, int x, int y) {
        if (create) {
            this.x = x;
            this.y = y;
            stuff_collection = Collections.synchronizedList(new ArrayList<Stuff>());
            for (int i = 0; i < 300; i++) {

                stuff_collection.add(new Stuff(true, (int) (Math.random() * 6), this.x, this.y));

            }
        } else {
            this.x = x;
            this.y = y;
            stuff_collection = Collections.synchronizedList(new ArrayList<Stuff>());

        }
    }
}
