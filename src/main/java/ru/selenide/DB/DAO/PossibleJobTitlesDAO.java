package ru.selenide.DB.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.selenide.DB.HibernateSessionFactoryUtil;
import ru.selenide.DB.domain.PossibleJobTitle;

public class PossibleJobTitlesDAO {
    public PossibleJobTitle findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(PossibleJobTitle.class, id);
    }

    public PossibleJobTitle findByTitle(String title) {

        Query<PossibleJobTitle> query = HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("from PossibleJobTitles s where s.title=:title", PossibleJobTitle.class);
        query.setParameter("title", title);
        return query.uniqueResult();
    }


    public void save(PossibleJobTitle PossibleJobTitle) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(PossibleJobTitle);
        tx1.commit();
        session.close();
    }

    public void delete(PossibleJobTitle PossibleJobTitle) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(PossibleJobTitle);
        tx1.commit();
        session.close();
    }
}
