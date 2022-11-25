package com.springboot.relationship.domain.dto;

import com.springboot.relationship.domain.entity.Hospital;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalReadResponse {

    private Integer id;
    private String name;
    private String roadNameAddress;

    public static HospitalReadResponse of(Hospital hospital) {
        return HospitalReadResponse.builder()
                .id(hospital.getId())
                .name(hospital.getHospitalName())
                .roadNameAddress(hospital.getRoadNameAddress())
                .build();
    }
}
