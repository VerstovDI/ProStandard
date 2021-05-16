package ru.selenide.DB.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.selenide.DB.HibernateSessionFactoryUtil;
import ru.selenide.DB.domain.GeneralizedWorkFunctions;


public class GeneralizedWorkFunctionsDAO {
    public GeneralizedWorkFunctions findByCodeKindProfessionalActivity(String code) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(GeneralizedWorkFunctions.class, code);
    }

    public GeneralizedWorkFunctions findByName(String nameGwf) {

        Query<GeneralizedWorkFunctions> query = HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("from GeneralizedWorkFunctions s where s.nameGwf=:nameGwf", GeneralizedWorkFunctions.class);
        query.setParameter("name", nameGwf);
        return query.uniqueResult();
    }


    public void save(GeneralizedWorkFunctions generalizedWorkFunctions) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(generalizedWorkFunctions);
        tx1.commit();
        session.close();
    }

    public void delete(GeneralizedWorkFunctions generalizedWorkFunctions) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(generalizedWorkFunctions);
        tx1.commit();
        session.close();
    }
}
