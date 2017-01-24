/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.number26;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.json.simple.JSONObject;

/**
 *
 * @author timurozkul
 */
public class Dao {
    
    //HashMap for efficient locating of a value based on a key and inserting 
    //deleting values based on a key. 
    HashMap<Long, Transactions> transactionsMap = new HashMap<>();
    Collection<Transactions> transactionkeys = transactionsMap.values();
    

    
    /*    public static final String FILENAME = "Transactions.txt";
        public static final String DELIMITER = "::";
    
        public void loadTransactions() throws FileNotFoundException{
    
             Scanner read = new Scanner(new BufferedReader(new FileReader(FILENAME)));
    
            while (read.hasNext()) {
    
                String recordLine = read.nextLine();
                String[] recordProperties = recordLine.split(DELIMITER);
    
                if (recordProperties.length == 4) {
    
                    Transactions trans = new Transactions();
    
                    trans.setTransaction_id(Long.parseLong(recordProperties[0]));
                    trans.setParent_id(Long.parseLong(recordProperties[1]));
                    trans.setType(recordProperties[2]);
                    trans.setAmount(Double.parseDouble(recordProperties[3]));
    
                    transactionsMap.put(trans.getTransaction_id(), trans);
                }
            }
        }
    
        public void writeTransactionsFile() throws IOException{
    
            PrintWriter write = new PrintWriter(new FileWriter(FILENAME));
    
            for (Long key : transactionsMap.keySet()) {
    
                Transactions newTrans = transactionsMap.get(key);
    
                write.println(newTrans.getTransaction_id() + DELIMITER
                + newTrans.getParent_id() + DELIMITER
                + newTrans.getType() + DELIMITER
                + newTrans.getAmount());
            }
            write.flush();
            write.close();
        }*/
    
    //Adds transactions to the hashMap
    public JSONObject addTransaction(Transactions newTrans){
        
        transactionsMap.put(newTrans.getTransaction_id(), newTrans);
        
        //Produces "ok" JSON object if the containsKey() finds the supposedly 
        //new created Transaction object. and "bad" if it doesnt 
        JSONObject obj = new JSONObject();
        if(transactionsMap.containsKey(newTrans.getTransaction_id()) && newTrans.getTransaction_id() != 0){
            obj.put("status","ok");
        }else{obj.put("status","bad");}
       
        return obj;
    }
    
    //
    public JSONObject getById(long id){
        //The simple json.simple jar files imported for speed, apparently it is
        //nearly 10x faster then json
        JSONObject obj = new JSONObject();
        
        //Enhanced for loop for readability and clarity
        //It doesnt require indexing to access hashmap since they are not in 
        //order.
        
        for(Transactions trans: transactionkeys){
            if(trans.getTransaction_id() == id){
               obj.put("amount", trans.getAmount());
               obj.put("type", trans.getType());
               obj.put("parent_id", trans.getParent_id());
            }
        }
             return obj; 
    }
    
    //Gets all transaction ids that share the same Type
    public long[] getByType(String type){
      
        //Creates a list with only transaction ID's of the ones matching the 
        //given parameter. Since the Hashmap is not in order the transacation 
        //IDs will be unordered hence the sort method.
        List<Long> idList = transactionkeys.stream()
                .filter(transaction -> transaction.getType().equalsIgnoreCase(type))
                .map(transaction -> transaction.getTransaction_id())
                .sorted()
                .collect(Collectors.toList());
        
        //Converting the list to an array, according to spec.
         long[] transIds = new long[idList.size()];
         for(int i = 0; i <idList.size(); i++){
             transIds[i] = idList.get(i);
         }
         return transIds;
    }
    
    //Gets the sum of all transactions that are transitively linked by their 
    //parentId.
    public JSONObject getSumById(long id){
        JSONObject obj = new JSONObject();
        //require the parent ID of the transaction ID for finding the other
        //transactions IDs
        Long ParentId = transactionsMap.get(id).getParent_id();
         
        List<Double> sumList= transactionkeys.stream()
                .filter(transaction -> transaction.getParent_id()== ParentId)
                .map(transaction -> transaction.getAmount())
                .collect(Collectors.toList());
              
        //Adding all the sums from the list.
        double totalSum = 0;
        for (double sum:sumList){
            totalSum = totalSum + sum;
        }
        obj.put("sum", totalSum);
        return obj;
}
    /*
     Asymptotic Behaviour
     Due to the simplistic nature of the objective in the challange there 
     aren't any large 'n' factors other than the number of inputs (coming from 
     the hashmap). Whenever a 'n' has a large growth factor all other mechanisms
     relating will become a lot more sensetive becauses at those points where 
     our input(Transaction data) come in contact with other 'n's (streams & 
     loops), thats where 'n' grows. Hence all our 'n' factors growth is relative
     to our input (Data from hashmap).
    
     In my code I have used streams which are better performers then collections
     but only when increased in tasks, so for our case it seems optimal. Also 
     the use of enhance for loops over tradional ones is favored. If it weren't 
     for the spec requirement I would avoid using and array for that exact
     reason. Regarding hashmap it appears to be a good performer in respect to
     other types data storage system, although I have yet to find a good
     documentation for that conclusion.
    
    */
}