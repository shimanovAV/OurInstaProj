package com.bsu.ourInstaProj.entity.response;

import com.bsu.ourInstaProj.entity.User;
import com.bsu.ourInstaProj.entity.base.DataResponse;

import java.util.List;

public class UserResponse extends DataResponse<User> {
        public UserResponse(List<User> data) {
            super(data);
        }
}
