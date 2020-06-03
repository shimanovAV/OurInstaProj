package com.bsu.ourInstaProj.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoRequest {

    private String url;

    private Integer width;

    private Integer height;
}
