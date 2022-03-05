package ru.Aidar.ParsingProffStandards.DB.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.Aidar.ParsingProffStandards.DB.HibernateSessionFactoryUtil;
import ru.Aidar.ParsingProffStandards.DB.domain.Standard;

import java.util.List;

public class StandardsDAO {


    public List<Standard> findByCodeKindProfessionalActivity(String code) {
        Query<Standard> query = HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().createQuery("from Standard s where s.codeKindProfessionalActivity=:code", Standard.class);
        query.setParameter("code",code);
        return query.getResultList();
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
        session.clear();
        session.close();
    }

    public void delete(Standard standard) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(standard);
        tx1.commit();
        session.clear();
        session.close();
    }
}
