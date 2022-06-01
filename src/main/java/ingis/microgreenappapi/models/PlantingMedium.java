package ingis.microgreenappapi.models;


import javax.persistence.*;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "PlantingMedium")
public class PlantingMedium {
    @Id
    @GeneratedValue
    private Integer mediumId;

    @NotBlank
    @Size(max = 50, message = "Description to long!")
    private String mediumType;

    private Double qty;

    @OneToOne
    @JoinColumn(name = "trayId")
    private Tray tray;


    public PlantingMedium() {
    }

    public PlantingMedium(String mediumType, Double qty, Tray tray) {
        this.mediumType = mediumType;
        this.qty = qty;
        this.tray = tray;
    }

    //    public PlantingMedium(String mediumType, Integer qty, Tray trayId) {
//        this.mediumType = mediumType;
//        this.qty = Double.valueOf(qty);
//        this.mediumId = nextId;
//        nextId++;
//    }


    public void setMediumId(Integer mediumId) {
        this.mediumId = mediumId;
    }

    public String getMediumType() {
        return mediumType;
    }

    public void setMediumType(String mediumType) {
        this.mediumType = mediumType;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Tray getTray() {
        return tray;
    }

    public void setTray(Tray tray) {
        this.tray = tray;
    }

    public int getMediumId()
    {
        return mediumId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlantingMedium that = (PlantingMedium) o;
        return mediumId == that.mediumId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mediumId);
    }

    @Override
    public String toString() {
        return "PlantingMedium{" +
                "mediumType='" + mediumType + '\'' +
                '}';

    }
}


