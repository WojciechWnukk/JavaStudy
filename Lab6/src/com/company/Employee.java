package com.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Employee {
    private String name;
    private Integer salary;
    private Integer age;
    private String role;

}
