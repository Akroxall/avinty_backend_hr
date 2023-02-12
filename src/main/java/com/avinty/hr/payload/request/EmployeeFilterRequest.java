package com.avinty.hr.payload.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeFilterRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fullName;
    private String email;
}
