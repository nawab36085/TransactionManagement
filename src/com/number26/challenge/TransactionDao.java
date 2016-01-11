package com.number26.challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TransactionDao {
	private static Map<String, Transaction> transactions = new HashMap<String, Transaction>();
	
	//To add a new transaction in the transaction record.
	public JSONObject addTransaction(long traId, Transaction transaction) throws JSONException{
		String key = String.valueOf(traId);
		transactions.put(key, transaction);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", "ok");
		return jsonObj;
	}
	
	//To retrieve a particular transaction from the transaction record.
	public JSONObject getTransaction(String traId) throws JSONException{
		JSONObject jsonObj = null;
		Transaction transaction = transactions.get(traId);
		if(transaction != null){
			jsonObj = new JSONObject();
			jsonObj.put(Transaction.AMOUNT, transaction.getTransactionAmount());
			jsonObj.put(Transaction.TYPE, transaction.getTransactionType());
			jsonObj.put(Transaction.PARENT_ID, transaction.getTransactionParentId());
		}
		return jsonObj;
	}
	
	//To get all the transactions present in the transaction record.
	public JSONArray getTransactions() throws JSONException{
		//Transaction transaction = transactions.get(traId);
		JSONArray jsonArray = new JSONArray();
		Iterator<Entry<String, Transaction>> it_tra = transactions.entrySet().iterator();
		Map.Entry<String, Transaction> entry;
		while(it_tra.hasNext()){
			entry = it_tra.next();
			Transaction transaction = entry.getValue();
			JSONObject jsonObj = new JSONObject();
			jsonObj.put(Transaction.TRANSACTION_ID, transaction.getTransactionId());
			jsonObj.put(Transaction.AMOUNT, transaction.getTransactionAmount());
			jsonObj.put(Transaction.TYPE, transaction.getTransactionType());
			jsonObj.put(Transaction.PARENT_ID, transaction.getTransactionParentId());
			jsonArray.put(jsonObj);
		}
		return jsonArray;
	}
	
	//To get all the transactions of a particular type from the transaction record.
	public ArrayList<Long> getTransactions(String type){
		ArrayList<Long> ids = new ArrayList<Long>();
		Iterator<Entry<String, Transaction>> it_tra = transactions.entrySet().iterator();
		Map.Entry<String, Transaction> entry;
		while(it_tra.hasNext()){
			entry = it_tra.next();
			Transaction transaction = entry.getValue();
			
			if(transaction.getTransactionType().equals(type)){
				ids.add(transaction.getTransactionId());
			}
		}
		return ids;
	}
	
	//To get the sum of all the transactions, which share the parent id with the provide transaction id.
	public JSONObject getSum(String id) throws JSONException{
		double sum = 0;
		Iterator<Entry<String, Transaction>> it_tra = transactions.entrySet().iterator();
		Map.Entry<String, Transaction> entry;
		while(it_tra.hasNext()){
			entry = it_tra.next();
			Transaction transaction = entry.getValue();
			
			if(transaction.getTransactionParentId() == Long.parseLong(id)){
				sum = sum + transaction.getTransactionAmount();
			}
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sum", sum);
		return jsonObj;
	}
}
