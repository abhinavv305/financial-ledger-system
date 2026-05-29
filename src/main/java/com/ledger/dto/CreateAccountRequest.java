package com.ledger.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter

public class CreateAccountRequest{

    @NotBlank
    private String ownerName;

    @DecimalMin(value = "0.00")
    private BigDecimal initialBalance;
}