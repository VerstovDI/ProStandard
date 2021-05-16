package ru.selenide.DB.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.selenide.DB.HibernateSessionFactoryUtil;
import ru.selenide.DB.domain.EducationalRequirements;

public class EducationalRequirementsDAO {
    public EducationalRequirements findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(EducationalRequirements.class, id);
    }

    public EducationalRequirements findByRequirement(String requirement) {

        Query<EducationalRequirements> query = HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("from EducationalRequirements s where s.requirement=:requirement", EducationalRequirements.class);
        query.setParameter("requirement", requirement);
        return query.uniqueResult();
    }


    public void save(EducationalRequirements EducationalRequirements) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(EducationalRequirements);
        tx1.commit();
        session.close();
    }

    public void delete(EducationalRequirements EducationalRequirements) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(EducationalRequirements);
        tx1.commit();
        session.close();
    }
}
