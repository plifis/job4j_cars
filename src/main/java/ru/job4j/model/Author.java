package ru.job4j.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany
    private List<Advt> list = new ArrayList<>();


    public Author() {

    }

    public Author(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Advt> getList() {
        return list;
    }

    public void setList(List<Advt> list) {
        this.list = list;
    }

    public void addAdvt(Advt advt) {
        this.list.add(advt);
    }
}
