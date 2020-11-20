package org.anz.wholesale.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ACCOUNT")
@Data
@Builder
@AllArgsConstructor
public class Account {

    @Id
    @Column(name = "NUMBER")
    private Long number;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Column(name = "BALANCE_DATE")
    private Date balanceDate;

    @Column(name = "CURRENCY")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "OPENING_AVAILABLE_BALANCE")
    private double openingAvailableBalance;

    public Account(){}

    public Account(Long number){
        this.number = number;
    }

}
