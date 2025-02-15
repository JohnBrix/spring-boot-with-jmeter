package com.sample.demo.controllers.dto;

import lombok.Data;

@Data
public class HttpUserInfoRequest {
    private String firstName;
    private String lastName;
    private Integer age;

}
