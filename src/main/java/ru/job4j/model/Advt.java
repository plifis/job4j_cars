package ru.job4j.model;


import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "advts")
public class Advt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean isSale;
    private Date created;

    @OneToOne
    @JoinColumn("car_id")
    private Car car;

    public Advt() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSale() {
        return isSale;
    }

    public void setSale(boolean sale) {
        isSale = sale;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Advt advt = (Advt) o;
        return id == advt.id && isSale == advt.isSale && Objects.equals(car, advt.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isSale, car);
    }
}
