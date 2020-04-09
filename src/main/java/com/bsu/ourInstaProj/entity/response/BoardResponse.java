package com.bsu.ourInstaProj.entity.response;

import com.bsu.ourInstaProj.entity.Board;
import com.bsu.ourInstaProj.entity.base.DataResponse;

import java.util.List;

public class BoardResponse extends DataResponse<Board> {
        public BoardResponse(List<Board> data) {
            super(data);
        }
}
