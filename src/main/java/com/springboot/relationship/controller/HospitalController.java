package com.springboot.relationship.controller;

import com.springboot.relationship.domain.dto.*;
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
     * 모든 병원 정보 조회 기능
     */
    @GetMapping("")
    public ResponseEntity<List<HospitalReadResponse>> hospitalList(Pageable pageable) {
        return ResponseEntity.ok().body(hospitalService.findAllHospital(pageable));
    }

    /**
     * 모든 리뷰 조회 기능 - ReviewController에 있는거랑 뭐가 다른걸까...
     */
    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewReadResponse>> ReviewList(Pageable pageable) {
        return ResponseEntity.ok().body(hospitalService.findAllReview(pageable));
    }

    /**
     * 리뷰 등록 기능 - 이것도 ReviewController에 있어야 하는거 아닌가?..
     */
    @PostMapping("/{id}/reviews")
    public ResponseEntity<ReviewCreateResponse> addReview(@PathVariable Integer id,
                                                     @RequestBody ReviewCreateRequest reviewCreateRequest) {
        return ResponseEntity.ok().body(reviewService.createReview(reviewCreateRequest));
    }

    /**
     * 특정 병원(id)의 리뷰만 조회하는 기능
     */
    @GetMapping("/{hospitalId}/reviews")
    public ResponseEntity<List<ReviewReadResponse>> hospitalReview(@PathVariable("hospitalId") Integer id) {
        return ResponseEntity.ok().body(reviewService.findAllByHospitalId(id));
    }

    /**
     * 특정 병원의 리뷰를 병원 정보와 함께 조회하는 기능
     */
    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> hospitalWithReview(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(hospitalService.findReviewByHospitalId(id));
    }
}
