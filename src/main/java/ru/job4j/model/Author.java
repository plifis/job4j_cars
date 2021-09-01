package ru.job4j.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id && Objects.equals(user, author.user) && Objects.equals(list, author.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, list);
    }
}
