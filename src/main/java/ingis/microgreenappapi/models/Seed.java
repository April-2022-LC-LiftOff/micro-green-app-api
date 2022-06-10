package ingis.microgreenappapi.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "Seed")
public class Seed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seedId;

    @NotBlank
    @Size(max = 50, message = "Name too long!")
    private String seedName;

    private float seedingDensity;

    private Boolean seedPresoak;

    private Integer blackoutTime;

    private Integer harvestTime;

    private Integer lot;

    private float qty;

    private String status;

    @OneToMany(mappedBy = "seed")
    private List<OrderDetails> orderDetails = new ArrayList<>();

    public Seed(String seedName, float seedingDensity, Boolean seedPresoak,  Integer blackoutTime, Integer harvestTime, Integer lot ,float qty, String status, OrderDetails orderDetails) {

        this.seedName = seedName;
        this.seedingDensity = seedingDensity;
        this.seedPresoak = seedPresoak;
        this.blackoutTime = blackoutTime;
        this.harvestTime = harvestTime;
        this.qty = qty;
        this.lot = lot;
        this.status = status;
    }

    // model for inventory page
    public Seed() {}

//    public Seed(String seedName, Integer qty) {
//        this.seedName = seedName;
//        this.qty = qty;
//    }


    public Integer getSeedId() {
        return seedId;
    }

    public void setSeedId(Integer seedId) {
        this.seedId = seedId;
    }

    public String getSeedName() {
        return seedName;
    }

    public void setSeedName(String seedName) {
        this.seedName = seedName;
    }

    public float getSeedingDensity() {
        return seedingDensity;
    }

    public void setSeedingDensity(float seedingDensity) {
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

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }

    @JsonIgnore
    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Integer getLot() {
        return lot;
    }

    public void setLot(Integer lot) {
        this.lot = lot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
//
//    public int getId() {
//        return seedId;
//    }
    @Override
    public String toString() {
        return "Seed{" +
                "seedId=" + seedId +
                ", seedName='" + seedName + '\'' +
                ", seedingDensity=" + seedingDensity +
                ", seedPresoak=" + seedPresoak +
                ", blackoutTime=" + blackoutTime +
                ", harvestTime=" + harvestTime +
                ", qty=" + qty +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seed seed = (Seed) o;
        return seedId == seed.seedId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seedId);
    }
}
