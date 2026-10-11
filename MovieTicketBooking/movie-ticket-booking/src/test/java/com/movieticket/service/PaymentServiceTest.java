package com.movieticket.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentServiceTest {

    @Test
    void paymentWithValidAmount() {

        PaymentService service = new PaymentService();

        assertTrue(service.processPayment(500));
    }

    @Test
    void paymentWithMinimumValidAmount() {

        PaymentService service = new PaymentService();

        assertTrue(service.processPayment(1));
    }

    @Test
    void paymentWithZeroAmount() {

        PaymentService service = new PaymentService();

        assertFalse(service.processPayment(0));
    }

    @Test
    void paymentWithNegativeAmount() {

        PaymentService service = new PaymentService();

        assertFalse(service.processPayment(-100));
    }
}