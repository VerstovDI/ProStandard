package ru.Aidar.ParsingProffStandards.DB.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.Aidar.ParsingProffStandards.DB.HibernateSessionFactoryUtil;
import ru.Aidar.ParsingProffStandards.DB.domain.RequiredSkill;

public class RequiredSkillsDAO {
    public RequiredSkill findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(RequiredSkill.class, id);
    }

    public void save(RequiredSkill RequiredSkill) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(RequiredSkill);
        tx1.commit();
        session.clear();
        session.close();
    }

    public void delete(RequiredSkill RequiredSkill) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(RequiredSkill);
        tx1.commit();
        session.clear();
        session.close();
    }
}
