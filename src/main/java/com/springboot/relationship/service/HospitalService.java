package com.springboot.relationship.service;

import com.springboot.relationship.domain.dto.HospitalReadResponse;
import com.springboot.relationship.domain.dto.HospitalResponse;
import com.springboot.relationship.domain.dto.ReviewReadResponse;
import com.springboot.relationship.domain.dto.ReviewResponse;
import com.springboot.relationship.domain.entity.Hospital;
import com.springboot.relationship.domain.entity.Review;
import com.springboot.relationship.repository.HospitalRepository;
import com.springboot.relationship.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final ReviewRepository reviewRepository;

    public HospitalService(HospitalRepository hospitalRepository, ReviewRepository reviewRepository) {
        this.hospitalRepository = hospitalRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<HospitalReadResponse> findAllHospital(Pageable pageable) {
        Page<Hospital> hospitals = hospitalRepository.findAll(pageable);
        List<HospitalReadResponse> hospitalList = hospitals.stream()
                .map(hospital -> HospitalReadResponse.of(hospital)).collect(Collectors.toList());
        return hospitalList;
    }

    public List<ReviewReadResponse> findEachHospitalReviews(Pageable pageable) {
        Page<Review> reviews = reviewRepository.findAll(pageable);
        List<ReviewReadResponse> reviewReadResponses = reviews.stream()
                .map(review -> ReviewReadResponse.of(review)).collect(Collectors.toList());

        return reviewReadResponses;
    }

    public HospitalResponse findHospitalReview(Integer id) {

        Hospital hospital = hospitalRepository.findById(id).get();

        List<Review> hospitalReviewList = hospital.getReviewList();

        List<ReviewResponse> finalHospitalReviewList = hospitalReviewList.stream()
                .map(hospitalReview -> ReviewResponse.of(hospitalReview)).collect(Collectors.toList());

        return HospitalResponse.builder()
                .id(hospital.getId())
                .name(hospital.getHospitalName())
                .roadNameAddress(hospital.getRoadNameAddress())
                .reviewResponses(finalHospitalReviewList)
                .build();

    }

    //    public List<ReviewResponse> findHospitalReview(Integer id) {
//
//        Hospital hospital = hospitalRepository.findById(id).get();
//        List<Review> hospitalReviewList = hospital.getReviewList();
//        List<ReviewResponse> finalHospitalReviewList = hospitalReviewList.stream()
//                .map(hospitalReview -> ReviewResponse.of(hospitalReview)).collect(Collectors.toList());
//
//        return finalHospitalReviewList;
//    }

}
