package SpaceResearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Stuff_Collection_Collection {
    private List<Stuff_Collection> stuff_collection_Collection;

    Stuff_Collection_Collection(boolean create) {
        stuff_collection_Collection = Collections.synchronizedList(new ArrayList<Stuff_Collection>());
        if (create)
            add_new_collection(0, 0);
    }

    void add_new_collection(int x, int y) {
        Stuff_Collection to_add = new Stuff_Collection(true, x, y);
        stuff_collection_Collection.add(to_add);
    }

    void add_old_collection(Stuff_Collection stuff_collection) {
        stuff_collection_Collection.add(stuff_collection);
    }

    synchronized List<Stuff_Collection> get_it() {
        return this.stuff_collection_Collection;
    }
}
