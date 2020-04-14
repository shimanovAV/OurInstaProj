package com.bsu.ourInstaProj.entity.response;


import com.bsu.ourInstaProj.entity.Photo;
import com.bsu.ourInstaProj.entity.base.DataResponse;

import java.util.List;

public class PhotoResponse extends DataResponse<Photo> {
    public PhotoResponse(List<Photo> data) {
        super(data);
    }
}
