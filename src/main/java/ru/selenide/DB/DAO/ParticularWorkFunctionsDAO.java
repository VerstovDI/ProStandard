package ru.selenide.DB.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.selenide.DB.HibernateSessionFactoryUtil;
import ru.selenide.DB.domain.ParticularWorkFunction;

public class ParticularWorkFunctionsDAO {
    public ParticularWorkFunction findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(ParticularWorkFunction.class, id);
    }

    public ParticularWorkFunction findByName(String name) {
        Query<ParticularWorkFunction> query = HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("from ParticularWorkFunctions s where s.nameWf=:name", ParticularWorkFunction.class);
        query.setParameter("name", name);
        return query.uniqueResult();
    }

    public ParticularWorkFunction findByCode(String code) {
        Query<ParticularWorkFunction> query = HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("from ParticularWorkFunctions s where s.codeWf=:code", ParticularWorkFunction.class);
        query.setParameter("code", code);
        return query.uniqueResult();
    }


    public void save(ParticularWorkFunction ParticularWorkFunction) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(ParticularWorkFunction);
        tx1.commit();
        session.close();
    }

    public void delete(ParticularWorkFunction ParticularWorkFunction) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(ParticularWorkFunction);
        tx1.commit();
        session.close();
    }
}
