package ingis.microgreenappapi.data;

import ingis.microgreenappapi.models.PlantingMedium;
import ingis.microgreenappapi.models.Seed;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PlantingMediumData {
    private static final Map<Integer, PlantingMedium> medium = new HashMap<>();

    public static Collection<PlantingMedium> getAll() {
        return medium.values();
    }

    public static PlantingMedium getById(int id) {
        return medium.get(id);
    }

//    public static void add(PlantingMedium medium) {
//        medium.put(medium.getId(), medium);
//    }

    public static void remove(int id) {
        medium.remove(id);
    }
}
