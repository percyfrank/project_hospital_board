package com.springboot.relationship.controller;

import com.springboot.relationship.domain.dto.HospitalResponse;
import com.springboot.relationship.domain.dto.ReviewCreateRequest;
import com.springboot.relationship.domain.dto.ReviewCreateResponse;
import com.springboot.relationship.domain.dto.ReviewResponse;
import com.springboot.relationship.domain.entity.Hospital;
import com.springboot.relationship.domain.entity.Review;
import com.springboot.relationship.service.HospitalService;
import com.springboot.relationship.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;
    private final ReviewService reviewService;

    public HospitalController(HospitalService hospitalService, ReviewService reviewService) {
        this.hospitalService = hospitalService;
        this.reviewService = reviewService;
    }


    /**
     * 병원 정보 나오는 기능
     */
    @GetMapping("")
    public ResponseEntity<List<HospitalResponse>> hospitalList(Pageable pageable) {
        return ResponseEntity.ok().body(hospitalService.findAllHospital(pageable));
    }

    /**
     * 모든 리뷰 정보와 해당 리뷰가 작성된 병원 이름이 같이 나오는 기능
     */
    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewResponse>> eachReviewWithHospital(Pageable pageable) {
        return ResponseEntity.ok().body(hospitalService.findEachHospitalReviews(pageable));
    }

    /**
     * 리뷰 등록 기능
     */
    @PostMapping("/{id}/reviews")
    public ResponseEntity<ReviewCreateResponse> addReview(@PathVariable Integer id,
                                                     @RequestBody ReviewCreateRequest reviewCreateRequest) {
        return ResponseEntity.ok().body(reviewService.createReview(reviewCreateRequest));
    }

    /**
     * 특정 병원(id)의 리뷰만 조회하는 기능
     */
    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> hospitalReviewList(@PathVariable Integer id) {
        return ResponseEntity.ok().body(hospitalService.findHospitalReview(id));
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<List<ReviewResponse>> hospitalReviewList(@PathVariable Integer id) {
//        return ResponseEntity.ok().body(hospitalService.findHospitalReview(id));
//    }

    /**
     * 특정 리뷰(id)로 병원정보까지 조회하는 기능
     */
//    @GetMapping("/reviews/{id}")
//    public ResponseEntity<ReviewResponse> reviewWithHospital() {
//
//    }

}
