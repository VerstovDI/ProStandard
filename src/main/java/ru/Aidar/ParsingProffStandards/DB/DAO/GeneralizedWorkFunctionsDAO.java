package ru.Aidar.ParsingProffStandards.DB.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.Aidar.ParsingProffStandards.DB.HibernateSessionFactoryUtil;
import ru.Aidar.ParsingProffStandards.DB.domain.GeneralizedWorkFunction;


public class GeneralizedWorkFunctionsDAO {
    public GeneralizedWorkFunction findByCodeKindProfessionalActivity(String code) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(GeneralizedWorkFunction.class, code);
    }

    public GeneralizedWorkFunction findByName(String nameGwf) {

        Query<GeneralizedWorkFunction> query = HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("from GeneralizedWorkFunctions s where s.nameGwf=:nameGwf", GeneralizedWorkFunction.class);
        query.setParameter("name", nameGwf);
        return query.uniqueResult();
    }


    public void save(GeneralizedWorkFunction generalizedWorkFunction) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(generalizedWorkFunction);
        tx1.commit();
        session.clear();
        session.close();
    }

    public void delete(GeneralizedWorkFunction generalizedWorkFunction) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(generalizedWorkFunction);
        tx1.commit();
        session.clear();
        session.close();
    }
}
