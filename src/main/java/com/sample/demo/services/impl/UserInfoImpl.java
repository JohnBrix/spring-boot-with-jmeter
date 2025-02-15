package com.sample.demo.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.demo.controllers.dto.HttpUserInfoRequest;
import com.sample.demo.entity.UserInfoModel;
import com.sample.demo.repository.UserRepository;
import com.sample.demo.services.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserInfoImpl implements UserInfoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public UserInfoModel saveUserInfo(HttpUserInfoRequest request){
        //Notes: Add findById here.
        UserInfoModel userInfoModelRequest = objectMapper.convertValue(request,UserInfoModel.class);

        log.info(userInfoModelRequest.toString());

        return userRepository.save(userInfoModelRequest);
    }

}
