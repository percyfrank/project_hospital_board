package com.springboot.relationship.domain.dto;

import com.springboot.relationship.domain.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewReadResponse {

    private Integer id;
    private String title;
    private String content;
    private String patientName;
    private String hospitalName;

    public static ReviewReadResponse of(Review review) {
        return ReviewReadResponse.builder()
                .id(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
                .patientName(review.getPatientName())
                .hospitalName(review.getHospital().getHospitalName())
                .build();
    }
}
