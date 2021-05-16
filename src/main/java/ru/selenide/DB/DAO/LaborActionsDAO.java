package ru.selenide.DB.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.selenide.DB.HibernateSessionFactoryUtil;
import ru.selenide.DB.domain.LaborActions;

public class LaborActionsDAO {
    public LaborActions findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(LaborActions.class, id);
    }

    public void save(LaborActions LaborActions) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(LaborActions);
        tx1.commit();
        session.close();
    }

    public void delete(LaborActions LaborActions) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(LaborActions);
        tx1.commit();
        session.close();
    }
}
