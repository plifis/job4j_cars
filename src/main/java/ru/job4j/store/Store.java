package ru.job4j.store;

import ru.job4j.model.Advt;
import ru.job4j.model.Author;
import ru.job4j.model.User;

import java.util.Date;
import java.util.List;

public interface Store {
    <T> T getRowById(Class<T> cl, Integer id);
    Author getAuthorById(int id);
    List<Advt> getAllAdvts();
    <T> boolean save(T t);
    boolean updateAdvt(Advt advt);
    User findUserByName(String name);
    User findUserByEmail(String email);
    Author findAuthorByUser(User user);
    List<Advt> getAdvtCurrentMark(String mark);
    List<Advt> getAdvtWithImage();
    List<Advt> getAdvtForDay(Date date);

    void deleteAdvt(int id);

    void close();
}
