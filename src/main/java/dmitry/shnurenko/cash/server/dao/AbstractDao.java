package dmitry.shnurenko.cash.server.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Nonnull;

/**
 * The class is common for all dao classes. Contains general methods for all dao classes.
 *
 * @author Dmitry Shnurenko
 */
public abstract class AbstractDao<T> {

    private final SessionFactory factory;

    protected Session session;

    public AbstractDao(@Nonnull SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * Saves entity to data base.
     *
     * @param entity entity object which will be saved to data base
     */
    protected synchronized void save(T entity) {
        startTransaction();

        session.save(entity);

        commitTransaction();
    }

    /** Opens session and begins transaction. */
    protected void startTransaction() {
        session = factory.openSession();
        session.beginTransaction();
    }

    /** Commits transaction and closes session. */
    protected void commitTransaction() {
        session.getTransaction().commit();
        session.close();
    }
}
