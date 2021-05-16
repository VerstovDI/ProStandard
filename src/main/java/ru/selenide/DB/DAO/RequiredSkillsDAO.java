package ru.selenide.DB.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.selenide.DB.HibernateSessionFactoryUtil;
import ru.selenide.DB.domain.RequiredSkills;

public class RequiredSkillsDAO {
    public RequiredSkills findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(RequiredSkills.class, id);
    }

    public void save(RequiredSkills RequiredSkills) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(RequiredSkills);
        tx1.commit();
        session.close();
    }

    public void delete(RequiredSkills RequiredSkills) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(RequiredSkills);
        tx1.commit();
        session.close();
    }
}
