package com.perkins.springbootweb.mongo;

import com.mongodb.DB;
import com.mongodb.Mongo;
import org.junit.Test;

import java.util.Set;


public class TestMondb {

    @Test
    public void testMongoDB() {
        try {
            Mongo mongo = new Mongo("127.0.0.1", 27017);
            DB db = mongo.getDB("test");
            Set<String> collectionNames = db.getCollectionNames();
            for (String name : collectionNames) {
                System.out.println("collectionName===" + name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
