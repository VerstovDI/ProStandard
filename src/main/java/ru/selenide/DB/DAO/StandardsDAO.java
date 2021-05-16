package ru.selenide.DB.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.selenide.DB.HibernateSessionFactoryUtil;
import ru.selenide.DB.domain.Standard;

public class StandardsDAO {


    public Standard findByCodeKindProfessionalActivity(String code) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Standard.class, code);
    }

    public Standard findByNameProfessionalStandart(String name) {

        Query<Standard> query = HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("from Standard s where s.nameProfessionalStandart=:name", Standard.class);
        query.setParameter("name", name);
        return query.uniqueResult();
    }



    public void save(Standard standard) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(standard);
        tx1.commit();
        session.close();
    }

    public void delete(Standard standard) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(standard);
        tx1.commit();
        session.close();
    }
}
