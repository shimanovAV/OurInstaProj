package com.bsu.ourInstaProj.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {

    private Long id;

    private Long userId;

    private String name;

    private String boardPicture;

    private List<UserVO> users = new ArrayList<>();
}
