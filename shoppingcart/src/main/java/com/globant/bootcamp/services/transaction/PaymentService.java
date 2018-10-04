package com.globant.bootcamp.services.transaction;

import com.globant.bootcamp.model.Account;
import com.globant.bootcamp.model.Transaction;

public interface PaymentService {
	void performTransaction(Account account, Transaction transaction) throws PaymentException ;
}
