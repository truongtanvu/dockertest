package com.example.demo9_account.dto;

import lombok.Data;

@Data
public class AccountDTO {
    private Integer id;

    private String username;

    private String password;

    private String fullName;

    private String department;
}
