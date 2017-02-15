/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Drug;
import model.Drugs;
import model.Patient;
import model.Patients;
import org.dom4j.Entity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author joosiika
 */
public class PatientHIB {
    SessionFactory sf;
    final StandardServiceRegistry reg;

    private Session session;
    private Transaction transaction;
    
    public PatientHIB() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("db.properties"));
        } catch (Exception e) {
        }
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", properties.getProperty("hibernate.connection.url"));
        configuration.setProperty("hibernate.connection.username", properties.getProperty("hibernate.connection.username"));
        configuration.setProperty("hibernate.connection.password", properties.getProperty("hibernate.connection.password"));
        reg = new StandardServiceRegistryBuilder().configure("hospitaldb.cfg.xml").applySettings(configuration.getProperties()).build();
        sf = null;

        try {
            sf = configuration.buildSessionFactory(reg);
        }
        catch (Exception e) {
            System.err.println("Session failed to initialize.");
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(reg);
            System.exit(-1);
        }
    }
    
    @Override
    public void finalize() {
        boolean success = false;
        do {
            try {
                if (reg != null) {
                    StandardServiceRegistryBuilder.destroy(reg);
                }
                success = true;
            } catch (Exception e) {
                System.out.println("DB connection not shutting down. Retrying...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        } while (!success);
        System.out.println("DB connection shut down.");
    }

    //@Override
    public Patient readPatient(String SSN) {
        Patient patient = null;
        session = sf.openSession();
        try {
            session.beginTransaction();
            session.load(patient = new Patient(), SSN);
            session.getTransaction().commit();
            Hibernate.initialize(patient.getDiagnoses());
            Hibernate.initialize(patient.getPrescriptions());
        } catch (Exception e) {
            System.out.println("Caught an error while reading resources.");
        } finally {
            session.close();
        }
        return patient;
    }

    //@Override
    public Patients readPatients() {
        List<Patient> result = null;
        session = sf.openSession();
        try {
            session.beginTransaction();
            result = session.createQuery("from potilas").getResultList();
            session.getTransaction().commit();
            for (Patient patient : result) {
                Hibernate.initialize(patient.getDiagnoses());
                Hibernate.initialize(patient.getPrescriptions());
            }
        } catch (Exception e) {
            System.out.println("Caught an error while reading resources.");
            e.printStackTrace();
        } finally {
            session.close();
        }
        Patients patients = new Patients(result);
        return patients;
    }
}
