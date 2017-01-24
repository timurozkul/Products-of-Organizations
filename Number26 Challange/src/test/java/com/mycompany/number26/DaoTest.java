/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.number26;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 *
 * @author timurozkul
 */
public class DaoTest {
    
    private Dao dao;
    
     public DaoTest() {
     } 
    
    @Before
    public void setUp() {
        
       ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
         dao = ctx.getBean("DaoBean", Dao.class);
    }
    
    @Test 
    public void testAddTransaction(){
        
        /*
         Adding three Transactions to HashMap and prove that it exist and its 
        Transaction_id is corresponding with the object.
         */
        Transactions t1 =  new Transactions();
        t1.setTransaction_id(6001);
        t1.setParent_id(6001);
        t1.setType("Car");
        t1.setAmount(50000);
        
        Assert.assertTrue(t1.getTransaction_id() == 6001);
        
        //////////////////////////////////////
            
        Transactions t2 =  new Transactions();
        t2.setTransaction_id(6002);
        t2.setParent_id(6002);
        t2.setType("Jewelry");
        t2.setAmount(800);
        
        Assert.assertTrue(t2.getTransaction_id() == 6002);
        
         //////////////////////////////////////
        
        Transactions t3 =  new Transactions();
        t3.setTransaction_id(6003);
        t3.setParent_id(6002);
        t3.setType("Jewelry");
        t3.setAmount(200);
        
        Assert.assertTrue(t3.getTransaction_id() == 6003);
        
    }
    
    @Test
    public void testAddTransactionMethod(){
    
        /*
        Calling three times the addTransactions method and to see if the
        response is "ok" or "bad" according to the test. 
        Each test has its own set up for better reference.
         */
         JSONObject objOk = new JSONObject();
         JSONObject objBad = new JSONObject();
         objOk.put("status","ok");
         objBad.put("status","bad");
         
        Transactions t1 =  new Transactions();
        t1.setTransaction_id(5023);
        t1.setParent_id(5002);
        t1.setType("Spa");
        t1.setAmount(500);
        
        Assert.assertEquals(objOk, dao.addTransaction(t1));
        
        //////////////////////////////////////
              
        Transactions t2 =  new Transactions();
        
         Assert.assertEquals(objBad, dao.addTransaction(t2));
         
         //////////////////////////////////////
        
        Transactions t3 =  new Transactions();
        t3.setTransaction_id(5004);
        t3.setParent_id(5003);
        t3.setType("Toys");
        t3.setAmount(120);
        
         Assert.assertEquals(objOk, dao.addTransaction(t3));
    }
    
    @Test
    public void getByIdTest(){
        
        /*
         Testing the method for retrieving transaction information by 
         transaction ID's
         */
        
        Transactions t1 =  new Transactions();
        t1.setTransaction_id(1001);
        t1.setParent_id(1001);
        t1.setType("Car");
        t1.setAmount(50000);       
        dao.addTransaction(t1);
        
        JSONObject obj1 = new JSONObject();
        obj1.put("amount", t1.getAmount());
        obj1.put("type", t1.getType());
        obj1.put("parent_id", t1.getParent_id());
        
       Assert.assertEquals(obj1, dao.getById(1001));
       
        //////////////////////////////////////
            
        Transactions t2 =  new Transactions();
        t2.setTransaction_id(1002);
        t2.setParent_id(1002);
        t2.setType("Jewelry");
        t2.setAmount(800);
        dao.addTransaction(t2);
        
        JSONObject obj2 = new JSONObject();
        obj2.put("amount", t2.getAmount());
        obj2.put("type", t2.getType());
        obj2.put("parent_id", t2.getParent_id());
        
       Assert.assertEquals(obj2, dao.getById(1002));
       
         //////////////////////////////////////
        
        Transactions t3 =  new Transactions();
        t3.setTransaction_id(1003);
        t3.setParent_id(1002);
        t3.setType("Jewelry");
        t3.setAmount(200);
        dao.addTransaction(t3);
        
        JSONObject obj3 = new JSONObject();
        obj3.put("amount", t3.getAmount());
        obj3.put("type", t3.getType());
        obj3.put("parent_id", t3.getParent_id());
        
       Assert.assertEquals(obj3, dao.getById(1003));
                
    }
    
    @Test
    public void getByTypeTest(){
        
        /*
         Testing the method for retrieving transaction ID's when given the
         Type. The stream in the DAO file has sort method to sort them from 
        smallest to largest number. Case senstitivity is also checked.
         */
        
        Transactions t1 =  new Transactions();
        t1.setTransaction_id(99004);
        t1.setParent_id(99004);
        t1.setType("Others");
        t1.setAmount(120);
        dao.addTransaction(t1);
        
        Transactions t2 =  new Transactions();
        t2.setTransaction_id(99011);
        t2.setParent_id(99004);
        t2.setType("Toys");
        t2.setAmount(720);
        dao.addTransaction(t2);
        
        Transactions t3 =  new Transactions();
        t3.setTransaction_id(99005);
        t3.setParent_id(99004);
        t3.setType("Toys");
        t3.setAmount(130);
        dao.addTransaction(t3);
        
        Transactions t4 =  new Transactions();
        t4.setTransaction_id(99006);
        t4.setParent_id(99006);
        t4.setType("Toys");
        t4.setAmount(270);
        dao.addTransaction(t4);
        
        Transactions t5 =  new Transactions();
        t5.setTransaction_id(99007);
        t5.setParent_id(99007);
        t5.setType("Toys");
        t5.setAmount(240);
        dao.addTransaction(t5);
        
        Transactions t6 =  new Transactions();
        t6.setTransaction_id(99008);
        t6.setParent_id(99008);
        t6.setType("Travel");
        t6.setAmount(1920);
        dao.addTransaction(t6);
        
        Transactions t7 =  new Transactions();
        t7.setTransaction_id(99009);
        t7.setParent_id(99009);
        t7.setType("Travel");
        t7.setAmount(7920);
        dao.addTransaction(t7);
        
      
        Assert.assertArrayEquals(new long[] {99005, 99006, 99007, 99011}, dao.getByType("Toys"));
        Assert.assertArrayEquals(new long[] {99008, 99009}, dao.getByType("travel"));
        Assert.assertArrayEquals(new long[] {99004}, dao.getByType("Others"));
    }
    
    @Test
    public void getSumByIdTest(){
        
        /*
         Testing the method getSumByID if it retrieves the sum of all 
         transactions that are associated by the parent Id of the given 
         parameter. Same Types are existant with different Parent Id
              
         */
        
        Transactions t1 =  new Transactions();
        t1.setTransaction_id(3001);
        t1.setParent_id(3001);
        t1.setType("Toys");
        t1.setAmount(120);
        dao.addTransaction(t1);
        
        Transactions t2 =  new Transactions();
        t2.setTransaction_id(3002);
        t2.setParent_id(3001);
        t2.setType("Toys");
        t2.setAmount(720);
        dao.addTransaction(t2);
        
        Transactions t3 =  new Transactions();
        t3.setTransaction_id(3003);
        t3.setParent_id(3001);
        t3.setType("Toys");
        t3.setAmount(130);
        dao.addTransaction(t3);
        
        Transactions t4 =  new Transactions();
        t4.setTransaction_id(11001);
        t4.setParent_id(11001);
        t4.setType("Food");
        t4.setAmount(270);
        dao.addTransaction(t4);
        
        Transactions t5 =  new Transactions();
        t5.setTransaction_id(11002);
        t5.setParent_id(11001);
        t5.setType("Food");
        t5.setAmount(240);
        dao.addTransaction(t5);
        
        Transactions t6 =  new Transactions();
        t6.setTransaction_id(11003);
        t6.setParent_id(11002);
        t6.setType("Food");
        t6.setAmount(1920);
        dao.addTransaction(t6);
        
        Transactions t7 =  new Transactions();
        t7.setTransaction_id(8009);
        t7.setParent_id(8009);
        t7.setType("Travel");
        t7.setAmount(7920);
        dao.addTransaction(t7);
        
         Transactions t8 =  new Transactions();
        t8.setTransaction_id(8010);
        t8.setParent_id(8010);
        t8.setType("Travel");
        t8.setAmount(80);
        dao.addTransaction(t8);
        
        JSONObject obj1 = new JSONObject();
        obj1.put("sum", 970.0);
        Assert.assertEquals(obj1, dao.getSumById(3001));
        Assert.assertEquals(obj1, dao.getSumById(3002));
         Assert.assertEquals(obj1, dao.getSumById(3003));
         
        JSONObject obj2 = new JSONObject();
        obj2.put("sum", 510.0);
        Assert.assertEquals(obj2, dao.getSumById(11001));
        Assert.assertEquals(obj2, dao.getSumById(11002));
        //Third food type has different Parent ID
        
        JSONObject obj3 = new JSONObject();
        obj3.put("sum", 7920.0);
        Assert.assertEquals(obj3, dao.getSumById(8009));
        //Second Travel type has a different Parent ID
        
    }
    
}
