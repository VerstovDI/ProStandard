package ru.Aidar.ParsingProffStandards.DB.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.Aidar.ParsingProffStandards.DB.HibernateSessionFactoryUtil;
import ru.Aidar.ParsingProffStandards.DB.domain.EducationalRequirement;

public class EducationalRequirementsDAO {
    public EducationalRequirement findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(EducationalRequirement.class, id);
    }

    public EducationalRequirement findByRequirement(String requirement) {

        Query<EducationalRequirement> query = HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("from EducationalRequirements s where s.requirement=:requirement", EducationalRequirement.class);
        query.setParameter("requirement", requirement);
        return query.uniqueResult();
    }


    public void save(EducationalRequirement EducationalRequirement) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(EducationalRequirement);
        tx1.commit();
        session.clear();
        session.close();
    }

    public void delete(EducationalRequirement EducationalRequirement) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(EducationalRequirement);
        tx1.commit();
        session.clear();
        session.close();
    }
}
