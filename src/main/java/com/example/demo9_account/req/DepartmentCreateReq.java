package com.example.demo9_account.req;

import lombok.Data;

@Data

public class DepartmentCreateReq {
    private String department;

    // Getter for department
    public String getDepartment() {
        return department;
    }

    // Setter for department
    public void setDepartment(String department) {
        this.department = department;
    }
}
