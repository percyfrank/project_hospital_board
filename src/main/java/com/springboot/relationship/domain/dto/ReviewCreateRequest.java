package com.springboot.relationship.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateRequest {

//    private Integer id;
    private Integer hospitalId;
    private String title;
    private String content;
    private String patientName;

}


