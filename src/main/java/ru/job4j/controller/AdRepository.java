package ru.job4j.controller;

import org.hibernate.Session;
import ru.job4j.model.Advt;
import ru.job4j.model.Car;

import javax.persistence.metamodel.ListAttribute;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdRepository {

    public List<Advt> getAdvtCurrentMark(Car car, Session session) {
        return session.createQuery("select distinct a from Advt a "
                + "join fetch a.car where a.car = :aMark", Advt.class)
                .setParameter(":aMark", car.getMark()).list();
    }
     public List<Advt> getAdvtWithImage(Session session) {
        return session.createQuery("select distinct a from Advt a "
                + "join fetch a.car c where c.image is not null ", Advt.class).list();
     }

     public List<Advt> getAdvtForDay(Session session, Date date) {
        return session.createQuery("select distinct a from Advt a "
                + "join fetch a.car where a.created :: date = :aDate").setParameter("aDate", date).list();
     }
}