package com.springboot.relationship.controller;

import com.springboot.relationship.domain.dto.ReviewReadResponse;
import com.springboot.relationship.domain.dto.ReviewResponse;
import com.springboot.relationship.domain.entity.Review;

import com.springboot.relationship.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor    // 필요한 argument 넣어줌(ReviewRepository, HospitalRepository)
@RequestMapping("api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 리뷰(id)로 조회하는 기능
     */
    @GetMapping("{id}")
    public ResponseEntity<ReviewResponse> getEachReview(@PathVariable Integer id) {
        Review review = reviewService.findReview(id);
        ReviewResponse reviewResponse = ReviewResponse.of(review);
        return ResponseEntity.ok().body(reviewResponse);
    }

    @GetMapping("")
    public ResponseEntity<List<ReviewReadResponse>> getAllReview(Pageable pageable) {
        return ResponseEntity.ok().body(reviewService.findAllReview(pageable));
    }

    
}
