package com.bsu.ourInstaProj.entity.base;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DataResponse<T> {

    private List<T> data;

    public DataResponse(List<T> data) {
        this.data = data;
    }
}
