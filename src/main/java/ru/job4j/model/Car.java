package ru.job4j.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private String mark;
    private String body;
    private String image;

    public Car() {

    }

    public Car(String description, String mark, String body, String image) {
        this.description = description;
        this.mark = mark;
        this.body = body;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && Objects.equals(description, car.description) && Objects.equals(mark, car.mark) && Objects.equals(body, car.body) && Objects.equals(image, car.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, mark, body, image);
    }
}
