package com.springboot.relationship.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateResponse {

    private Integer id;
    private String title;
    private String content;
    private String patientName;
    private String message;

}

