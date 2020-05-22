package com.bsu.ourInstaProj.entity.response;

import com.bsu.ourInstaProj.entity.User;
import com.bsu.ourInstaProj.entity.base.DataResponse;

import java.util.List;

public class UserResponse extends DataResponse<UserVO> {
        public UserResponse(List<UserVO> data) {
            super(data);
        }
}
