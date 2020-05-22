package com.bsu.ourInstaProj.entity.response;

import com.bsu.ourInstaProj.entity.Board;
import com.bsu.ourInstaProj.entity.base.DataResponse;

import java.util.List;

public class BoardResponse extends DataResponse<BoardVO> {
        public BoardResponse(List<BoardVO> data) {
            super(data);
        }
}
