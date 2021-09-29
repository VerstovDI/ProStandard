package ru.Aidar.ParsingProffStandards.DB.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.Aidar.ParsingProffStandards.DB.HibernateSessionFactoryUtil;
import ru.Aidar.ParsingProffStandards.DB.domain.EmploymentGroupOkz;

public class EmploymentGroupOkzDAO {

    public EmploymentGroupOkz findById(Integer codeOkz) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(EmploymentGroupOkz.class, codeOkz);
    }

    public EmploymentGroupOkz findByNameOkz(String nameOkz) {
        Query<EmploymentGroupOkz> query = HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("from EmploymentGroupOkz s where s.nameOkz=:nameOkz", EmploymentGroupOkz.class);
        query.setParameter("nameOkz", nameOkz);
        return query.uniqueResult();
    }


    public void save(EmploymentGroupOkz employmentGroupOkz) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(employmentGroupOkz);
        tx1.commit();
        session.clear();
        session.close();
    }

    public void delete(EmploymentGroupOkz employmentGroupOkz) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(employmentGroupOkz);
        tx1.commit();
        session.clear();
        session.close();
    }
}
