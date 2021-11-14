package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Spring helped tie SessionFactory with AccidentHibernate.
 * That's all Spring has done here.
 */
@Repository
public class AccidentHibernate implements Storage {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Accident create(Accident accident) {
        return accident.getId() == 0 ? save(accident) : update(accident);
    }

    private Accident save(Accident accident) {
        tx(session -> session.save(accident));
        return accident;
    }

    private Accident update(Accident accident) {
        tu(session -> session.update(accident));
        return accident;
    }

    @Override
    public Collection<Accident> getAllAccidents() {
        return tx(session -> session.createNamedQuery("Accident_GetAll", Accident.class).list());
    }

    @Override
    public Collection<AccidentType> getAllAccidentTypes() {
        return tx(session -> session.createNamedQuery("AccidentType_GetAll", AccidentType.class).list());
    }

    @Override
    public Collection<Rule> getAllRules() {
        return tx(session -> session.createNamedQuery("Rule_GetAll", Rule.class).list());
    }

    @Override
    public Accident getAccidentById(int id) {
        return tx(session -> session.createNamedQuery("Accident_FindById", Accident.class)
                .setParameter("accidentId", id)
                .uniqueResult());
    }

    @Override
    public AccidentType getAccidentTypeById(int id) {
        return tx(
                session -> session.createNamedQuery("AccidentType_FindById", AccidentType.class)
                        .setParameter("accidentTypeId", id)
                        .uniqueResult()
        );
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    private void tu(final Consumer<Session> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            command.accept(session);
            tx.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
