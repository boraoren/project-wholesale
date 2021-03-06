package org.anz.wholesale.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.anz.wholesale.models.enums.AccountType;
import org.anz.wholesale.util.MoneyJsonSerializer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ACCOUNT")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Column(name = "BALANCE_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "dd/MM/yyyy")
    private LocalDate balanceDate;

    @Column(name = "OPENING_AVAILABLE_BALANCE")
    @JsonSerialize(using = MoneyJsonSerializer.class)
    private double openingAvailableBalance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BASE_ACCOUNT_NUMBER")
    private BaseAccount baseAccount;


}
