package com.sample.demo.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpUserInfoResponse  {
    private String resultCode;
    private String resultMessage;
    private String resultDescription;
    private User user;
}
