package com.springboot.relationship.service;

import com.springboot.relationship.domain.dto.HospitalReadResponse;
import com.springboot.relationship.domain.dto.HospitalResponse;
import com.springboot.relationship.domain.dto.ReviewReadResponse;
import com.springboot.relationship.domain.dto.ReviewResponse;
import com.springboot.relationship.domain.entity.Hospital;
import com.springboot.relationship.domain.entity.Review;
import com.springboot.relationship.repository.HospitalRepository;
import com.springboot.relationship.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final ReviewRepository reviewRepository;

    public List<HospitalReadResponse> findAllHospital(Pageable pageable) {
        Page<Hospital> hospitals = hospitalRepository.findAll(pageable);
        List<HospitalReadResponse> hospitalList = hospitals.stream()
                .map(hospital -> HospitalReadResponse.of(hospital)).collect(Collectors.toList());
        return hospitalList;
    }

    public List<ReviewReadResponse> findAllReview(Pageable pageable) {
        Page<Review> reviews = reviewRepository.findAll(pageable);
        List<ReviewReadResponse> reviewReadResponses = reviews.stream()
                .map(review -> ReviewReadResponse.of(review)).collect(Collectors.toList());

        return reviewReadResponses;
    }

    public HospitalResponse findReviewByHospitalId(Integer id) {
        // hospital 조회
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 병원을 찾을 수 없습니다."));

        return HospitalResponse.of(hospital);
    }

}
