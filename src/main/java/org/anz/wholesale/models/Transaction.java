package org.anz.wholesale.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.anz.wholesale.models.enums.DebitCredit;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate valueDate;

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
    @JoinColumn(name = "BASE_ACCOUNT_NUMBER")
    private BaseAccount baseAccount;

}
