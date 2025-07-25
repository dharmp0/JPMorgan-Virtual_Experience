package com.jpmc.midascore.component;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TransactionHandler {
    static final Logger logger = LoggerFactory.getLogger(TransactionHandler.class);
    private final DatabaseConduit databaseConduit;
    private final IncentiveQuerier incentiveQuerier;

    public TransactionHandler(DatabaseConduit databaseConduit, IncentiveQuerier incentiveQuerier) {
        this.databaseConduit = databaseConduit;
        this.incentiveQuerier = incentiveQuerier;
    }

    public void handleTransaction(Transaction transaction) {
        if (databaseConduit.isValid(transaction)) {
            databaseConduit.save(transaction);
        } else {
            System.out.println("Invalid transaction - ignored: " + transaction);
        }

        // Optional: print after every transaction
        // Remove this later
        databaseConduit.printAllUserBalances();
    }
}
