package com.sample.demo.controllers;

import com.sample.demo.controllers.dto.HttpUserInfoRequest;
import com.sample.demo.controllers.dto.HttpUserInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.sample.demo.constants.UserConstants.*;

@Slf4j
@RestController
public class UserController {

    @PostMapping
    public ResponseEntity<HttpUserInfoResponse> createUserInfo(@RequestBody HttpUserInfoRequest request){
        log.info(USER_INFO_REQUEST,request);

        if (null == request.getFirstName() ||
                null == request.getLastName() ||
                request.getFirstName().isEmpty() ||
                request.getLastName().isEmpty() ){

            return new ResponseEntity<>(httpUserInfoResponse(NEGATIVE_RESULTCODE,NEGATIVE_RESULTMESSAGE,NEGATIVE_RESULT_DESCRIPTION),HttpStatus.BAD_REQUEST);

        }
        return null;
    }

    public HttpUserInfoResponse httpUserInfoResponse(String resultcode,
                                                     String resultmessage,
                                                     String resultDescription){
         HttpUserInfoResponse response = new HttpUserInfoResponse(resultcode,resultmessage,resultDescription);
        log.info(HTTP_USERINFO_RESPONSE,response);

        return response;
    }
}
