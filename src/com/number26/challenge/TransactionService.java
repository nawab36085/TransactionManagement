package com.number26.challenge;

import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/transactionservice")
public class TransactionService {
	
	TransactionDao transactionDao = new TransactionDao();

	//getTransactions() method to handle the GET request to provide all the transactions.
	@GET
	@Path("/transactions")
	@Produces(MediaType.TEXT_HTML)
	public String getTransactions() throws JSONException {
		return transactionDao.getTransactions().toString();
	}
	
	//getTrsancation method to handle GET request to provide a transaction identified by the id
	@GET
	@Path("/transaction/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String getTransaction(@PathParam("id") String id) throws JSONException {
		JSONObject transaction = transactionDao.getTransaction(id);
		if(transaction == null){
			transaction = new JSONObject();
			transaction.put("Status", "Error: There is no transaction with id="+id);
		}
		return transaction.toString();
	}
	
	//getTranactions method to handle GET request to provide all the transactions identified by a type
	@GET
	@Path("/type/{type}")
	@Produces(MediaType.TEXT_HTML)
	public String getTransactions(@PathParam("type") String type) {
		return transactionDao.getTransactions(type).toString();
	}
	
	//getSum method to handle GET request to provide sum of the amounts of all the transactions sharing the same parent id
	@GET
	@Path("/sum/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String getSum(@PathParam("id") String id) throws JSONException {
		return transactionDao.getSum(id).toString();
	}


	//updateTransaction method to handle PUT request to create a new transaction
	@PUT
	@Path("/transaction/{id}")
	@Produces(MediaType.TEXT_HTML)
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.MULTIPART_FORM_DATA})
	public String updateTransaction(@PathParam("id") String id,
			@FormParam("amount") String amount,
			@FormParam("type") String type,
			@FormParam("parent_id") String parent_id) throws IOException, JSONException {
		long tempTraId;
		double amt;
		long parentId;
		Transaction transaction;
		if(id != null && id.equals("") == false){
			try{
			tempTraId = Long.parseLong(id);
			}
			catch(Exception NumberFormatException){
					JSONObject jsonStatus = new JSONObject();
					jsonStatus.put("Status", "Error: Transaction Id for the transaction is not valid. Transaction will not be recorded.");
					return jsonStatus.toString();
			}
		}
		else{
			JSONObject jsonStatus = new JSONObject();
			jsonStatus.put("Status", "Error: No transaction id specified for the transaction. Transaction will not be recorded.");
			return jsonStatus.toString();
		}
		if(amount != null && amount.equals("") == false){
			try{
				amt = Double.parseDouble(amount);
			}
			catch(Exception NumberFormatException){
				JSONObject jsonStatus = new JSONObject();
				jsonStatus.put("Status", "Error: Amount value specified for the transaction is not valid.Transaction will not be recorded.");
				return jsonStatus.toString();
			}
		}
		else{
			JSONObject jsonStatus = new JSONObject();
			jsonStatus.put("Status", "Error: No amount specified for the transaction. Transaction will not be recorded.");
			return jsonStatus.toString();
		}
		
		if(type == null || type.equals("") == true){
			JSONObject jsonStatus = new JSONObject();
			jsonStatus.put("Status", "Error: No type specified for the transaction. Transaction will not be recorded.");
			return jsonStatus.toString();
		}
		
		if(parent_id != null && parent_id.equals("") == false){
			try{
				parentId = Long.parseLong(parent_id);
			}
			catch(Exception NumberFormatException){
				JSONObject jsonStatus = new JSONObject();
				jsonStatus.put("Status", "Error: The format of the parent Id value for the transaction is not valid. Transaction will not be recorded.");
				return jsonStatus.toString();
			}
			transaction = new Transaction(tempTraId, amt, type, parentId);
		}
		else{
			transaction = new Transaction(tempTraId, amt, type);
		}
			
		return transactionDao.addTransaction(tempTraId, transaction).toString();
	}
	
	//createTransaction method to handle POST request to create a new transaction
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createTransaction(@FormParam("id") String id,
			@FormParam("amount") String amount,
			@FormParam("type") String type,
			@FormParam("parent_id") String parent_id) throws IOException, JSONException {
		long tempTraId;
		double amt;
		long parentId;
		Transaction transaction;
		if(id != null && id.equals("") == false){
			try{
			tempTraId = Long.parseLong(id);
			}
			catch(Exception NumberFormatException){
				return "Transaction Id for the transaction is not valid. Transaction will not be recorded.";
			}
		}
		else{
			
			return "No transaction id specified for the transaction. Transaction will not be recorded.";
		}
		if(amount != null && amount.equals("") == false){
			try{
				amt = Double.parseDouble(amount);
			}
			catch(Exception NumberFormatException){
				return "Amount value specified for the transaction is not valid.Transaction will not be recorded.";
			}
		}
		else
			return "No amount specified for the transaction. Transaction will not be recorded.";
		
		if(type == null || type.equals("") == true){
			return "No type specified for the transaction. Transaction will not be recorded.";
		}
		
		if(parent_id != null && parent_id.equals("") == false){
			try{
				parentId = Long.parseLong(parent_id);
			}
			catch(Exception NumberFormatException){
				return "The format of the parent Id value for the transaction is not valid. Transaction will not be recorded.";
			}
			transaction = new Transaction(tempTraId, amt, type, parentId);
		}
		else{
			transaction = new Transaction(tempTraId, amt, type);
		}
		
		return transactionDao.addTransaction(tempTraId, transaction).toString();
	}

}
