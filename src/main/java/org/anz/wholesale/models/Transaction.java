package org.anz.wholesale.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TRANSACTION")
@Data
public class Transaction {

    @Id
    private Long id;

    @Column(name = "VALUE_DATE")
    private Date valueDate;

    @Column(name = "DEBIT_AMOUNT")
    private double debitAmount;

    @Column(name = "CREDIT_AMOUNT")
    private double creditAmount;

    @Column(name = "DEBIT_CREDIT")
    @Enumerated(EnumType.STRING)
    private DebitCredit debitCredit;

    @Column(name = "NARRATIVE")
    private String narrative;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_NUMBER")
    private Account account;

}
