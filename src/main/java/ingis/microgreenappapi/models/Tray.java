package ingis.microgreenappapi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Tray {

    @Id
    @GeneratedValue
//    @ManyToOne
    private int trayId;
    private static int nextId = 1;
//
    private String trayType;
    private String size;
    private Integer qty;

    public Tray( String trayType, String size, Integer qty) {
        this.trayType = trayType;
        this.size = size;
        this.qty = qty;
        this.trayId = nextId;
        nextId++;
    }

    public Tray() {}

    public String getTrayType() {
        return trayType;
    }

    public void setTrayType(String trayType) {
        this.trayType = trayType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public int getTrayId() {
        return trayId;
    }
//
    @Override
    public String toString() {
        return "Tray{" +
                "trayType='" + trayType + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tray tray = (Tray) o;
        return trayId == tray.trayId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(trayId);
    }
}
