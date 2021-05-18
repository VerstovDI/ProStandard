package ru.selenide.DB.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.selenide.DB.HibernateSessionFactoryUtil;
import ru.selenide.DB.domain.LaborAction;

public class LaborActionsDAO {
    public LaborAction findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(LaborAction.class, id);
    }

    public void save(LaborAction LaborAction) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(LaborAction);
        tx1.commit();
        session.close();
    }

    public void delete(LaborAction LaborAction) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(LaborAction);
        tx1.commit();
        session.close();
    }
}
