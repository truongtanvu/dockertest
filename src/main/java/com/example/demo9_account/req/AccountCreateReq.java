package com.example.demo9_account.req;

import lombok.Data;

@Data
public class AccountCreateReq {
    private String username;
    private Integer departmentId;

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for departmentId
    public Integer getDepartmentId() {
        return departmentId;
    }

    // Setter for departmentId
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
