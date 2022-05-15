package ingis.microgreenappapi.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Seed")
public class Seed {
    @Id
    @GeneratedValue
    private int seedId;
    private static int nextId = 1;

    private String seedName;
    private Number seedingDensity;
    private Boolean seedPresoak;
    private Number blackoutTime;
    private Number harvestTime;
    private Number qty;

    @ManyToOne
    @JoinColumn(name="orderDetailsId")
    private OrderDetails orderDetails;

    public Seed() {
    }

    public Seed(int seedId, String seedName, Number seedingDensity, Boolean seedPresoak, Number blackoutTime, Number harvestTime, Number qty, OrderDetails orderDetails) {
        this.seedId = seedId;
        this.seedName = seedName;
        this.seedingDensity = seedingDensity;
        this.seedPresoak = seedPresoak;
        this.blackoutTime = blackoutTime;
        this.harvestTime = harvestTime;
        this.qty = qty;
        this.orderDetails = orderDetails;
    }

    public int getSeedId() {
        return seedId;
    }

    public void setSeedId(int seedId) {
        this.seedId = seedId;
    }

    public String getSeedName() {
        return seedName;
    }

    public void setSeedName(String seedName) {
        this.seedName = seedName;
    }

    public Number getSeedingDensity() {
        return seedingDensity;
    }

    public void setSeedingDensity(Number seedingDensity) {
        this.seedingDensity = seedingDensity;
    }

    public Boolean getSeedPresoak() {
        return seedPresoak;
    }

    public void setSeedPresoak(Boolean seedPresoak) {
        this.seedPresoak = seedPresoak;
    }

    public Number getBlackoutTime() {
        return blackoutTime;
    }

    public void setBlackoutTime(Number blackoutTime) {
        this.blackoutTime = blackoutTime;
    }

    public Number getHarvestTime() {
        return harvestTime;
    }

    public void setHarvestTime(Number harvestTime) {
        this.harvestTime = harvestTime;
    }

    public Number getQty() {
        return qty;
    }

    public void setQty(Number qty) {
        this.qty = qty;
    }

//    public OrderDetails getOrderDetails() {
//        return orderDetails;
//    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }
}
