package ingis.microgreenappapi.data;

import ingis.microgreenappapi.models.Seed;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SeedData {

    private static final Map<Integer, Seed> seeds = new HashMap<>();

    public static Collection<Seed> getAll() {
        return seeds.values();
    }

    public static Seed getById(int id) {
        return seeds.get(id);
    }

    public static void add(Seed seed) {

        seeds.put(seed.getId(), seed);
    }

    public static void remove(int id) {

        seeds.remove(id);
    }

}
