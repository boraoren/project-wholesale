package org.anz.wholesale.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TRANSACTION")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    private Long id;

    @Column(name = "VALUE_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "MMM dd, yyyy")
    private Date valueDate;

    @Column(name = "DEBIT_AMOUNT")
    private Double debitAmount;

    @Column(name = "CREDIT_AMOUNT")
    private Double creditAmount;

    @Column(name = "DEBIT_CREDIT")
    @Enumerated(EnumType.STRING)
    private DebitCredit debitCredit;

    @Column(name = "NARRATIVE")
    private String narrative;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_NUMBER")
    private Account account;

}
