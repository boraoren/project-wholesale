package org.anz.wholesale.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.anz.wholesale.models.enums.Currency;

import javax.persistence.*;

@Entity
@Table(name = "BASE_ACCOUNT")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseAccount {

    @Id
    @Column(name = "NUMBER")
    private Long number;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CURRENCY")
    @Enumerated(EnumType.STRING)
    private Currency currency;

}
