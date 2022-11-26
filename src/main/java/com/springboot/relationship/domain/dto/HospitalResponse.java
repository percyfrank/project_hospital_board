package com.springboot.relationship.domain.dto;

import com.springboot.relationship.domain.entity.Hospital;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HospitalResponse {

    private Integer id;
    private String name;
    private String roadNameAddress;
    private List<ReviewResponse> reviewResponses;

    public static HospitalResponse of(Hospital hospital) {
        return HospitalResponse.builder()
                .id(hospital.getId())
                .name(hospital.getHospitalName())
                .roadNameAddress(hospital.getRoadNameAddress())
                .reviewResponses(hospital.getReviewList().stream().map(
                                (review) -> ReviewResponse.of(review)).collect(Collectors.toList()))
//                .reviewResponses(hospital.getReviewList().stream().map(ReviewResponse::of).collect(Collectors.toList()))
                .build();
    }
}
