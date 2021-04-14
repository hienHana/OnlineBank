package com.silvercoinbank.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silvercoinbank.domain.Transaction;
import com.silvercoinbank.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	TransactionRepository transRepository;

	@Override
	public Transaction findTransactionById(long id) {
		if(transRepository.existsById(id)) {
			return transRepository.findById(id).get();
		}
		return null;
	}

	@Override
	public boolean isTransactionExisted(long id) {
		return transRepository.existsById(id);
	}


	@Override
	public Transaction depositTransaction(long accountId, double amount, String transComments, String whoMakesTrans) {
		Transaction trans = new Transaction();
		trans.setTransToAccountNo(accountId);
		trans.setTransComments(transComments);
		trans.setTransAmount(amount);
		trans.setTransDateTime(LocalDateTime.now());
		trans.setTransType("deposit");
		trans.setWhoMakesTrans(whoMakesTrans);
		transRepository.save(trans);
		return trans;
	}

	@Override
	public Transaction withdrawTransaction(long accountId, double amount, String transComments, String whoMakesTrans) {
		Transaction trans = new Transaction();
		trans.setTransFromAccountNo(accountId);
		trans.setTransComments(transComments);
		trans.setTransAmount(amount);
		trans.setTransDateTime(LocalDateTime.now());
		trans.setTransType("withdraw");
		trans.setWhoMakesTrans(whoMakesTrans);
		transRepository.save(trans);
		return trans;
	}

	@Override
	public Transaction transferTransaction(long fromAccountId, long toAccountId, double amount, String transComments, String whoMakesTrans) {
		Transaction trans = new Transaction();
		trans.setTransToAccountNo(toAccountId);
		trans.setTransFromAccountNo(fromAccountId);	
		trans.setTransComments(transComments);
		trans.setTransAmount(amount);
		trans.setTransDateTime(LocalDateTime.now());
		trans.setTransType("transfer");
		trans.setWhoMakesTrans(whoMakesTrans);
		transRepository.save(trans);
		return trans;
	}

	@Override
	public List<Long> getTransIdByTransFromAccountId(long accountId) {
		return transRepository.getTransIdByTransFromAccountId(accountId);
	}

	@Override
	public List<Long>  getTransIdByTransToAccountId(long accountId) {
		return transRepository.getTransIdByTransToAccountId(accountId);
	}

	@Override
	public List<Transaction> findAllTransactions() {
		return transRepository.findAll();
	}

}
