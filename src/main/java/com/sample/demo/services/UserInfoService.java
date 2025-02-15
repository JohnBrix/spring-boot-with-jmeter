package com.sample.demo.services;

import com.sample.demo.controllers.dto.HttpUserInfoRequest;
import com.sample.demo.entity.UserInfoModel;

public interface UserInfoService {

     UserInfoModel saveUserInfo(HttpUserInfoRequest request);
}
