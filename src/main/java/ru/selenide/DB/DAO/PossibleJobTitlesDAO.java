package ru.selenide.DB.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.selenide.DB.HibernateSessionFactoryUtil;
import ru.selenide.DB.domain.PossibleJobTitles;

public class PossibleJobTitlesDAO {
    public PossibleJobTitles findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(PossibleJobTitles.class, id);
    }

    public PossibleJobTitles findByTitle(String title) {

        Query<PossibleJobTitles> query = HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("from PossibleJobTitles s where s.title=:title", PossibleJobTitles.class);
        query.setParameter("title", title);
        return query.uniqueResult();
    }


    public void save(PossibleJobTitles PossibleJobTitles) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(PossibleJobTitles);
        tx1.commit();
        session.close();
    }

    public void delete(PossibleJobTitles PossibleJobTitles) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(PossibleJobTitles);
        tx1.commit();
        session.close();
    }
}
