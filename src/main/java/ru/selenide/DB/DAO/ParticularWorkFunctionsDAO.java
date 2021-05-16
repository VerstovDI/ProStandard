package ru.selenide.DB.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.selenide.DB.HibernateSessionFactoryUtil;
import ru.selenide.DB.domain.ParticularWorkFunctions;

public class ParticularWorkFunctionsDAO {
    public ParticularWorkFunctions findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(ParticularWorkFunctions.class, id);
    }

    public ParticularWorkFunctions findByName(String name) {
        Query<ParticularWorkFunctions> query = HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("from ParticularWorkFunctions s where s.nameWf=:name", ParticularWorkFunctions.class);
        query.setParameter("name", name);
        return query.uniqueResult();
    }

    public ParticularWorkFunctions findByCode(String code) {
        Query<ParticularWorkFunctions> query = HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("from ParticularWorkFunctions s where s.codeWf=:code", ParticularWorkFunctions.class);
        query.setParameter("code", code);
        return query.uniqueResult();
    }


    public void save(ParticularWorkFunctions ParticularWorkFunctions) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(ParticularWorkFunctions);
        tx1.commit();
        session.close();
    }

    public void delete(ParticularWorkFunctions ParticularWorkFunctions) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(ParticularWorkFunctions);
        tx1.commit();
        session.close();
    }
}
