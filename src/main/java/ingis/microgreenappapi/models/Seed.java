
package ingis.microgreenappapi.models;

import org.springframework.lang.NonNull;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

//@Entity
public class Seed {

//    @Id
//    @GeneratedValue
//    @ManyToMany
    private int seedId;
    private static int nextId = 1;
    @NotBlank
    @Size(max = 50, message = "Name too long!")
    private String seedName;
    private Number seedingDensity;
    private Boolean seedPresoak;
    private Number blackoutTime;
    private Number harvestTime;
    private Number qty;

    public Seed(String seedName, Number seedingDensity, Boolean seedPresoak,  Number blackoutTime, Number harvestTime, Number qty) {
        this.seedName = seedName;
        this.seedingDensity = seedingDensity;
        this.seedPresoak = seedPresoak;
        this.blackoutTime = blackoutTime;
        this.harvestTime = harvestTime;
        this.qty = qty;
        this.seedId = nextId;
        nextId++;
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
//
    public Number getQty() {
        return qty;
    }
//
    public void setQty(Number qty) {
        this.qty = qty;
    }

    public int getId() {
        return seedId;
    }

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
