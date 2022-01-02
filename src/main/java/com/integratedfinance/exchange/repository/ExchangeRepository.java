package com.integratedfinance.exchange.repository;

import com.integratedfinance.exchange.entity.Exchange;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRepository extends CrudRepository<Exchange, Long> {
    public List<Exchange> findAllByConversionDate(long conversionDate);
    public Exchange findByTransactionId(long transactionId);
}
