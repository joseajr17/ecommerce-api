package com.ecommerceapi.api.dtos.payment;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public record PaymentResponseDTO(UUID id, BigDecimal amount, String status, Date paymentDate) {}
