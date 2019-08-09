package com.mycompany.hibernate_native_demo;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

/**
 *
 * @author dhruv
 */
public class AppMain {

    private static Scanner sc;

    public AppMain() {
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        AppMain appMain = new AppMain();
        System.out.println("1: Retrieve data");
        System.out.println("2: Store data");

        System.out.print("Enter your choice:");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                appMain.getData();
                break;
            case 2:
                appMain.storeData();
        }

    }

    public void getData() {
        @SuppressWarnings("unchecked")
        List<HelloWorld> employees;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            employees = (List<HelloWorld>) session.createQuery(
                    "SELECT C FROM HelloWorld C").list();
            session.getTransaction().commit();
            employees.forEach((h) -> {
                LOGGER.info(h);
            });
        } catch (Exception e) {
            LOGGER.info(e);
        }

    }

    public void storeData() {
        HelloWorld helloWorld = new HelloWorld();
        System.out.print("Enter message:");
        String msg = sc.next();
        helloWorld.setMessage(msg);

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.save(helloWorld);
            session.getTransaction().commit();
            LOGGER.info("Message Added!");
        }catch(Exception e){
            LOGGER.info(e);
        }
        
    }
}
