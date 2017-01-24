package com.mycompany.number26;

import javax.inject.Inject;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


public class Controller {
        
    public Controller() {
    }
    
     private Dao dao;

    @Inject
    public Controller(Dao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/transactionservice/transaction/{transaction_id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public JSONObject createTransaction(@PathVariable("transaction_id") long transaction_id, @RequestBody Transactions transaction) {
        
        return dao.addTransaction(transaction);
    }
    
    @RequestMapping(value = "/transactionservice/transaction/{transaction_id}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getTransaction(@PathVariable("transaction_id") int id) {
        
        return dao.getById(id);
    }
    
    @RequestMapping(value = "/transactionservice/types/{type}", method = RequestMethod.GET)
    @ResponseBody
    public long[] getTransactionsByType(@PathVariable("type") String type) {
        
        return dao.getByType(type);
    }
    
    @RequestMapping(value = "/transactionservice/sum/{transaction_id}", method = RequestMethod.GET)
    @ResponseBody
    public  JSONObject getSumTransactions(@PathVariable("transaction_id") int id) {
       
        return dao.getSumById(id);
    }
    
}
