package com.sample.demo.controllers;

import com.sample.demo.controllers.dto.HttpUserInfoRequest;
import com.sample.demo.controllers.dto.HttpUserInfoResponse;
import com.sample.demo.controllers.dto.User;
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

        //Validate Request and return BAD_REQUEST
        ResponseEntity<HttpUserInfoResponse> validatedResults = validateRequest(request);
        if (validatedResults != null) return validatedResults;

        //SaveUserInfo, if throw exception return INTERNAL_SERVER_ERROR.
        ResponseEntity<HttpUserInfoResponse> saveUserInfoResults = saveUserInfo(request);
        if (saveUserInfoResults != null) return saveUserInfoResults;

        //Success and return CREATED.
        return new ResponseEntity<>(successResponse(), HttpStatus.CREATED);
    }

    private ResponseEntity<HttpUserInfoResponse> saveUserInfo(HttpUserInfoRequest request) {
        UserInfoModel saveUserInfoResults;

        try{
            saveUserInfoResults  = userInfoService.saveUserInfo(request);
        }catch (Exception e){
            log.error(e.getMessage());
            return buildInternalServer();
        }

        log.info(USER_CREATED,saveUserInfoResults.toString());
        return null;
    }

    private static HttpUserInfoResponse successResponse() {
        return HttpUserInfoResponse.builder()
                .resultMessage(SUCCESS)
                .resultCode(SUCCESS_ZERO)
                .resultDescription(SUCCESS)
                .user(new User()) //TODO to be update later
                .build();
    }

    private ResponseEntity<HttpUserInfoResponse> validateRequest(HttpUserInfoRequest request) {
        if (null == request.getFirstName() ||
                null == request.getLastName() ||
                request.getFirstName().isEmpty() ||
                request.getLastName().isEmpty() ){

            return buildBadRequest();
        }
        return null;
    }

    private ResponseEntity<HttpUserInfoResponse> buildBadRequest() {
        return new ResponseEntity<>(httpUserInfoResponse(NEGATIVE_RESULTCODE, BAD_REQUEST, EMPTY_PARAMETER), HttpStatus.BAD_REQUEST);
    }
    private ResponseEntity<HttpUserInfoResponse> buildInternalServer() {
        return new ResponseEntity<>(httpUserInfoResponse(NEGATIVE_RESULTCODE, INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public HttpUserInfoResponse httpUserInfoResponse(String resultcode,
                                                     String resultmessage,
                                                     String resultDescription){
         HttpUserInfoResponse response = HttpUserInfoResponse.builder()
                 .resultCode(resultcode)
                 .resultMessage(resultmessage)
                 .resultDescription(resultDescription)
                 .build();

        log.info(HTTP_USERINFO_RESPONSE,response);

        return response;
    }
}
