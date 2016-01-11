package com.number26.challenge;

public class Transaction {

	public static final String TRANSACTION_ID = "transaction_id";
	public static final String AMOUNT = "amount";
	public static final String TYPE = "type";
	public static final String PARENT_ID = "parent_id";
	
	//Transaction id of the transaction
	private long transaction_id;
	//Transaction amount of the transaction
	private double amount;
	//Transaction type of the transaction
	private String type;
	//Parent id of the transaction
	private long parent_id = -1;
	
	//Constructor with parent id value
	public Transaction(long transactionId, double transactionAmount, String transactionType, long transactionParentId){
		transaction_id = transactionId;
		amount = transactionAmount;
		type = transactionType;
		parent_id = transactionParentId;
	}
	//Constructor without parent id value
	public Transaction(long transactionId, double transactionAmount, String transactionType){
		transaction_id = transactionId;
		amount = transactionAmount;
		type = transactionType;
	}
	
	// Returns transaction id of the transaction
	public long getTransactionId(){
		return transaction_id;
	}
	
	//Returns transaction amount of the transaction
	public double getTransactionAmount(){
		return amount;
	}
	
	//Returns transaction type of the transaction
	public String getTransactionType(){
		return type;
	}
	
	//Returns parent id of the transaction
	public long getTransactionParentId(){
		return parent_id;
	}
}
