package com.garits.payment;

import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<PaymentRetail, Integer> {
}
