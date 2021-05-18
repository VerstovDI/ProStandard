package ru.selenide.DB;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.selenide.DB.domain.*;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Standard.class);
                configuration.addAnnotatedClass(Resource.class);
                configuration.addAnnotatedClass(EmploymentGroupOkz.class);
                configuration.addAnnotatedClass(EmploymentGroupOkved.class);
                configuration.addAnnotatedClass(GeneralizedWorkFunction.class);
                configuration.addAnnotatedClass(PossibleJobTitle.class);
                configuration.addAnnotatedClass(EducationalRequirement.class);
                configuration.addAnnotatedClass(ParticularWorkFunction.class);
                configuration.addAnnotatedClass(LaborAction.class);
                configuration.addAnnotatedClass(RequiredSkill.class);
                configuration.addAnnotatedClass(NecessaryKnowledge.class);


                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}