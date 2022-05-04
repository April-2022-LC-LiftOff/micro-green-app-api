package ingis.microgreenappapi.data;

import ingis.microgreenappapi.models.Tray;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TrayData {
    private static final Map<Integer, Tray> trays = new HashMap<>();

    public static Collection<Tray> getAll() {
        return trays.values();
    }

    public static Tray getById(int id) {
        return trays.get(id);
    }

    public static void add(Tray tray) {
        trays.put(tray.getTrayId(), tray);
    }

    public static void remove(int id) {
        trays.remove(id);
    }
}
