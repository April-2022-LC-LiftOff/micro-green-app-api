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
    private Integer seedingDensity;
    private Boolean seedPresoak;
    private Integer blackoutTime;
    private Integer harvestTime;
    private Integer qty;

    private String status;

    @ManyToOne
    @JoinColumn(name="orderDetailsId")
    private OrderDetails orderDetails;

    public Seed() {
    }

    public Seed(int seedId, String seedName, Integer seedingDensity, Boolean seedPresoak, Integer blackoutTime, Integer harvestTime, Integer qty, String status, OrderDetails orderDetails) {
        this.seedId = seedId;
        this.seedName = seedName;
        this.seedingDensity = seedingDensity;
        this.seedPresoak = seedPresoak;
        this.blackoutTime = blackoutTime;
        this.harvestTime = harvestTime;
        this.qty = qty;
        this.status = status;
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

    public Integer getSeedingDensity() {
        return seedingDensity;
    }

    public void setSeedingDensity(Integer seedingDensity) {
        this.seedingDensity = seedingDensity;
    }

    public Boolean getSeedPresoak() {
        return seedPresoak;
    }

    public void setSeedPresoak(Boolean seedPresoak) {
        this.seedPresoak = seedPresoak;
    }

    public Integer getBlackoutTime() {
        return blackoutTime;
    }

    public void setBlackoutTime(Integer blackoutTime) {
        this.blackoutTime = blackoutTime;
    }

    public Integer getHarvestTime() {
        return harvestTime;
    }

    public void setHarvestTime(Integer harvestTime) {
        this.harvestTime = harvestTime;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //    public OrderDetails getOrderDetails() {
//        return orderDetails;
//    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }
}
