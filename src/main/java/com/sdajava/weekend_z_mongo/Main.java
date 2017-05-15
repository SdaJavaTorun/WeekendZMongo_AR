package com.sdajava.weekend_z_mongo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static com.sdajava.weekend_z_mongo.DataToList.*;

public class Main {

    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {

        long startTimeMon = System.currentTimeMillis();

	// write your code here
        entityManagerFactory =
                Persistence.createEntityManagerFactory( "hikePu" );
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        for (int row = 1; row<=100000; row++) {
            int randomName = (int) (Math.random() * nameList.size());
            int randomLast = (int) (Math.random() * lastNamesList.size());

            WorkerMon worker =
                    new WorkerMon(row, nameList.get(randomName),
                            lastNamesList.get(randomLast),
                            (long) Math.floor(row/2));
            entityManager.merge(worker);
        }
        entityManager.getTransaction().commit();
        entityManager.close();

        long endTimeMon = System.currentTimeMillis();
        System.out.println("Czas wykonania Mongo: " + (endTimeMon - startTimeMon));
    }
}
