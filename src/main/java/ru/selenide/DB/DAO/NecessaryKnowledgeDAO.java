package ru.selenide.DB.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.selenide.DB.HibernateSessionFactoryUtil;
import ru.selenide.DB.domain.NecessaryKnowledge;

public class NecessaryKnowledgeDAO {
    public NecessaryKnowledge findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(NecessaryKnowledge.class, id);
    }

    public void save(NecessaryKnowledge NecessaryKnowledge) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(NecessaryKnowledge);
        tx1.commit();
        session.close();
    }

    public void delete(NecessaryKnowledge NecessaryKnowledge) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(NecessaryKnowledge);
        tx1.commit();
        session.close();
    }
}
