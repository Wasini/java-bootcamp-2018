package com.globant.bootcamp.services.transaction;

import com.globant.bootcamp.model.Account;
import com.globant.bootcamp.model.Transaction;

/**
 * This class is used to validate and perform payment transactions
 * for different payment methods.
 *
 * <p>Methods of this class throw a <tt>PaymentException</tt> with
 * an PaymentErrorCode when a transaction is impossible to complete
 *
 * @author Rodrigo Grazini
 */
public interface PaymentService {
	/**
	 * Tries to complete a transaction using the mediums provided by
	 * the {@link Account} interface
	 *
	 * <p>The method uses the {@link Transaction} to get the payment method,
	 * currency, value to move, destination, etc..
	 *
	 * @param account User Account from whom the currency is being moved
	 * @param transaction The transaction payload
	 * @throws PaymentException If transaction fails for insufficient funds or payment method is not supported
	 */
	void performTransaction(Account account, Transaction transaction) throws PaymentException ;
}
