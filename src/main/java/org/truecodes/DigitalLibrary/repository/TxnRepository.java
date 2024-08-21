package org.truecodes.DigitalLibrary.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.truecodes.DigitalLibrary.model.Txn;
import org.truecodes.DigitalLibrary.model.TxnStatus;

public interface TxnRepository extends JpaRepository<Txn, Integer> {

    Txn findByUserPhoneNoAndBookBookNoAndTxnStatus(String phoneNo, String bookNo, TxnStatus status);
}
