package ru.Aidar.ParsingProffStandards.DB.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.Aidar.ParsingProffStandards.DB.HibernateSessionFactoryUtil;
import ru.Aidar.ParsingProffStandards.DB.domain.EmploymentGroupOkved;

public class EmploymentGroupOkvedDAO {

    public EmploymentGroupOkved findById(Integer codeOkved) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(EmploymentGroupOkved.class, codeOkved);
    }

    public EmploymentGroupOkved findByNameOkved(String nameOkved) {
        Query<EmploymentGroupOkved> query = HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("from EmploymentGroupOkved s where s.nameOkved=:nameOkved", EmploymentGroupOkved.class);
        query.setParameter("nameOkz", nameOkved);
        return query.uniqueResult();
    }


    public void save(EmploymentGroupOkved employmentGroupOkved) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(employmentGroupOkved);
        tx1.commit();
        session.close();
    }

    public void delete(EmploymentGroupOkved employmentGroupOkved) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(employmentGroupOkved);
        tx1.commit();
        session.close();
    }
}
