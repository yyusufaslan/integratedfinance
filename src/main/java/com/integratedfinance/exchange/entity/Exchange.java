package com.integratedfinance.exchange.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "exchange")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exchange extends TrackableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exchange_generator")
    @SequenceGenerator(name = "exchange_generator", sequenceName = "exchange_seq")
    @Column(name = "id")
    private Long id;

    private long userId;

    private double sourceAmount;

    private String sourceCurrency;

    private String targetCurrency;

    @CreatedDate
    private long conversionDate;

    private long transactionId;

}
