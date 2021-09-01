package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Advt;
import ru.job4j.model.Author;
import ru.job4j.model.User;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class HbStore implements Store{
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    private static final class LAZY {
        private static final Store INST = new HbStore();
    }

    public static Store instOf() {
        return LAZY.INST;
    }


    /**
     * Метод Обертка
     * @param command функция которую необходимо исполнить
     * @param <T> тип обхекта который будет возвращен
     * @return результат выполнения
     */
    private <T> T wrapperMethod(Function<Session, T> command) {
        Session session = sf.openSession();
        session.beginTransaction();
        try {
            T rsl = command.apply(session);
            session.getTransaction().commit();
            return rsl;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    /**
     * Метод сохранения (обновления) сущности обобщенного типа T
     * @param t экземпляр обощенного типа
     * @param <T> обощенный тип
     * @return возрвщаем true, если сущность удалось сохранить или обновить
     */
    @Override
    public <T> boolean save(T t) {
        return this.wrapperMethod(session -> {
                    session.saveOrUpdate(t);
                    return true;
                });
    }

    /**
     * Метод сохранения сущности класса Advt (объявления)
     * @param advt экземпляр класса Advt
     * @return возвращаем true, если сущность удалось сохранить
     */
    public boolean updateAdvt(Advt advt) {
        return this.wrapperMethod(session -> session.merge(advt) != null);
        }

    /**
     * Метод получения эксземпляра сущности по идентификатору
     * @param cl класс сущности экземпляр, которой требуется вернуть
     * @param id идентификатор объекта, который требуется вернуть
     * @param <T> обощенный тип передаваемого клсса и возвращаемого объекта
     * @return экземпляр класса найденного по id, либо  null
     */
    @Override
    public <T> T getRowById(Class<T> cl, Integer id) {
        return this.wrapperMethod(session -> session.get(cl, id));
    }


    /**
     * Метод поиска автора по идентификатору
     * @param id идентификатор автора, которого требуется найти
     * @return найденный экземпляр класса Author, либо null
     */
    @Override
    public Author getAuthorById(int id) {
        return this.wrapperMethod(session -> {
               List<Author> rsl = session.createQuery("from Author a join fetch a.list where a.id = :aId")
                       .setParameter("aId", id).list();
                if (rsl.size() > 0) {
                    return rsl.get(0);
                } else {
                    return null;
                }
        });
    }


    /**
     * Метод возвращает объявления всех авторов одним списком
     * @return список всех объявлений всех авторов
     */
    @Override
    public List<Advt> getAllAdvts() {
        return this.wrapperMethod(session ->
                session.createQuery("select distinct a FROM Author a join fetch a.list").list());
    }

    /**
     * Метод поиска пользователя по имени
     * @param name имя пользователя, которого необходимо найти в хранилище
     * @return экземпляр класса User, содержащий name, либо null
     */
    @Override
    public User findUserByName(String name) {
        return this.wrapperMethod(session -> {
            List<User> rsl = session.createQuery("from User where name =: uId")
                    .setParameter("uId", name).list();
            if (rsl.size() > 0) {
                return rsl.get(0);
            } else {
                return null;
            }
        });
    }


    /**
     * Метод поиска пользователя по адресу электронной почты
     * @param email адрес электронной почты, которому необходимо найти соответсующий экземпляр класса User
     * @return экземпляр класса User, содержащий email, либо null
     */
    @Override
    public User findUserByEmail(String email) {
        return this.wrapperMethod(session -> {
            List<User> rsl =  session.createQuery("from User where email =: uEmail")
                    .setParameter("uEmail", email).list();
            if (rsl.size() > 0) {
                return rsl.get(0);
            } else {
                return null;
            }
        });
    }


    /**
     * Метод поиска автора по пользователю
     * @param user экземпляр класса User, которому необходимо найти соответствующий экземпляр класса Author
     * @return экземпляр класса Author, содержащий user, либо null
     */
    @Override
    public Author findAuthorByUser(User user) {
        return this.wrapperMethod(session -> {
            List<Author> rsl = session.createQuery("from Author a join fetch a.list where user_id =: aUser")
                    .setParameter("aUser", user.getId()).list();
            if (rsl.size() > 0) {
                return rsl.get(0);
            } else {
                return null;
            }
        });
    }


    /**
     * Метод поиска объявлений определенной марки автомобиля
     * @param mark марка автомобиля, который требуется найти
     * @return список объявлений содержащих mark
     */
    @Override
    public List<Advt> getAdvtCurrentMark(String mark) {
        return this.wrapperMethod(session ->
                session.createQuery("select distinct a from Author a "
                + "join fetch a.list ad join fetch ad.car c where c.mark like :aMark")
                .setParameter("aMark", mark).list());
    }


    /**
     * Метод поиска объявлений с фотографией
     * @return список объявлений с фотографией
     */
    @Override
    public List<Advt> getAdvtWithImage() {
        return this.wrapperMethod(session ->
                session.createQuery("select distinct a from Author a "
                + "join fetch a.list ad join fetch ad.car c where c.image is not null").list());
    }


    /**
     * Метод поиска объвлений за указанный день в формате экземпляра класса java.util.Date
     * @param date дата за которую требуется найти объявления
     * @return список объявлений за дату date
     */
    @Override
    public List<Advt> getAdvtForDay(Date date) {
        return this.wrapperMethod(session -> session.createQuery("select distinct a from Author a "
                + "join fetch a.list ad where ad.created = :aDate").setParameter("aDate", date).list());
    }


    /**
     * Метод удаления экземпляра класса Advt по идентификатору id
     * @param id идентификатор объявления, которое требуется удалить
     */
    @Override
    public void deleteAdvt(int id) {
        this.wrapperMethod(session -> {
            Advt advt = this.getRowById(Advt.class, id);
            session.delete(advt);
            return true;
        }
        );
    }

    @Override
    public void close() {
        this.sf.close();
    }
}
