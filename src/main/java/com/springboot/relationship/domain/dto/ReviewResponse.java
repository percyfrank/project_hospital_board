package com.springboot.relationship.domain.dto;

import com.springboot.relationship.domain.entity.Hospital;
import com.springboot.relationship.domain.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {

    private Integer reviewId;
    private String reviewTitle;
    private String reviewContent;
    private String reviewPatientName;
//    private String hospitalName;
//    private Hospital hospital;

    public static ReviewResponse of(Review review) {
        return ReviewResponse.builder()
                .reviewId(review.getId())
                .reviewTitle(review.getTitle())
                .reviewContent(review.getContent())
                .reviewPatientName(review.getPatientName())
//                .hospitalName(review.getHospital().getHospitalName())
//                .hospital(review.getHospital())
                .build();
    }
}
