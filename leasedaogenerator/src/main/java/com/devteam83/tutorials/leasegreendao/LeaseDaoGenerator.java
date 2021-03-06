package com.devteam83.tutorials.leasegreendao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class LeaseDaoGenerator {

    public static  void main(String[] args)
    {
     try
     {
         Schema schema = new Schema(1, "com.devteam83.tutorials.leasegreendao.model");

         Entity person=schema.addEntity("Person");
         person.addIdProperty();
         person.addStringProperty("name");
         person.addStringProperty("comment");

         Entity lease = schema.addEntity("Lease");
         lease.addIdProperty();
         lease.addStringProperty("item");
         lease.addStringProperty("comment");
         lease.addLongProperty("leasedate");
         lease.addLongProperty("returndate");

         Property personId=lease.addLongProperty("personId").getProperty();
         lease.addToOne(person, personId);

         ToMany personToLease = person.addToMany(lease, personId);
         personToLease.setName("leases");

         new DaoGenerator().generateAll(schema, "../app/src/main/java");
     }catch (Exception e)
     {
         System.out.println("Exception is "+e.toString());
     }

    }
}
