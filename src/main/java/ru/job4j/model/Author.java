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

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Advt> list = new ArrayList<>();


    public Author() {

    }

    public Author(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
