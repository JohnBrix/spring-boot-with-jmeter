package com.sample.demo.controllers;

import com.sample.demo.controllers.dto.HttpUserInfoRequest;
import com.sample.demo.controllers.dto.HttpUserInfoResponse;
import com.sample.demo.entity.UserInfoModel;
import com.sample.demo.services.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.sample.demo.constants.UserConstants.*;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/createUserInfo")
    public ResponseEntity<HttpUserInfoResponse> createUserInfo(@RequestBody HttpUserInfoRequest request){
        log.info(USER_INFO_REQUEST,request);

        //Validate Request
        ResponseEntity<HttpUserInfoResponse> validatedRequest = validateRequest(request);
        if (validatedRequest != null) return validatedRequest;


        //Create UserInfo, Return HttpUserInfoResponse and status code
        return responseEntityAndCreateUserInfo(request);
    }

    private ResponseEntity<HttpUserInfoResponse> responseEntityAndCreateUserInfo(HttpUserInfoRequest request) {
        UserInfoModel response;
        try{
            //Save UserInfo
            response = userInfoService.saveUserInfo(request);
            log.info(SAVED,response.getId());

            //Success response
            return new ResponseEntity<>(httpUserInfoResponse(SUCCESS, SUCCESSFULLY_SAVED, USER_CREATED), HttpStatus.CREATED);
        }catch (Exception e){
            log.error(EXCEPTION,e.getMessage());

            //Default Handling In case of out of scope
            return new ResponseEntity<>(httpUserInfoResponse(NEGATIVE_RESULTCODE, NEGATIVE_RESULTMESSAGE, NEGATIVE_RESULT_DESCRIPTION), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<HttpUserInfoResponse> validateRequest(HttpUserInfoRequest request) {
        if (null == request.getFirstName() ||
                null == request.getLastName() ||
                request.getFirstName().isEmpty() ||
                request.getLastName().isEmpty() ){

            return new ResponseEntity<>(httpUserInfoResponse(NEGATIVE_RESULTCODE, NEGATIVE_RESULTMESSAGE, NEGATIVE_RESULT_DESCRIPTION), HttpStatus.BAD_REQUEST);

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
