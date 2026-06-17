package com.project.banking.dto;

public record TransferFundDto(Long fromAccountId,
                              Long toAccountId,
                              Double amount) {

}
