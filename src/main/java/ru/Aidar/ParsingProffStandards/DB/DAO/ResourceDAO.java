package ru.Aidar.ParsingProffStandards.DB.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.Aidar.ParsingProffStandards.DB.HibernateSessionFactoryUtil;
import ru.Aidar.ParsingProffStandards.DB.domain.Resource;

public class ResourceDAO {
    public Resource findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Resource.class, id);
    }

    public void save(Resource resource) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(resource);
        tx1.commit();
        session.close();
    }

    public void delete(Resource resource) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(resource);
        tx1.commit();
        session.close();
    }
}
