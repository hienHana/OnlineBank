package com.silvercoinbank.service;


import java.util.List;

import com.silvercoinbank.domain.Transaction;

public interface TransactionService {
	public Transaction depositTransaction(long accountId, double amount, String transComments, String whoMakesTrans);
	public Transaction withdrawTransaction(long accountId, double amount, String transComments, String whoMakesTrans);
	public Transaction transferTransaction(long fromAccountId, long toAccountId, double amount, String transComments, String whoMakesTrans);
	public Transaction findTransactionById(long id);
	public boolean isTransactionExisted(long id);
	public List<Transaction> findAllTransactions();
	
	public List<Long> getTransIdByTransFromAccountId(long accountId);
	
	public List<Long> getTransIdByTransToAccountId(long accountId);
}
