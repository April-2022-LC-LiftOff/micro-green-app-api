package ingis.microgreenappapi.models;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Planting_Medium")
public class PlantingMedium {
    @Id
    @GeneratedValue
    private int mediumId;
    private static int nextId = 1;


    private String mediumType;

    private Double qty;

    @OneToOne
    @JoinColumn(name = "trayId")
    private Tray tray;

    @ManyToOne
    @JoinColumn(name="orderId")
    private CustomerOrder customerOrder;


    public PlantingMedium() {
    }

    public PlantingMedium(int mediumId, String mediumType, Double qty, Tray tray, CustomerOrder customerOrder) {
        this.mediumId = mediumId;
        this.mediumType = mediumType;
        this.qty = qty;
        this.tray = tray;
        this.customerOrder = customerOrder;
    }

    public int getMediumId() {
        return mediumId;
    }

    public void setMediumId(int mediumId) {
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

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }
}
