package com.springboot.relationship.controller;

import com.springboot.relationship.domain.dto.ReviewCreateRequest;
import com.springboot.relationship.domain.dto.ReviewCreateResponse;
import com.springboot.relationship.domain.dto.ReviewReadResponse;
import com.springboot.relationship.domain.dto.ReviewResponse;
import com.springboot.relationship.domain.entity.Review;

import com.springboot.relationship.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor    // 필요한 argument 넣어줌(ReviewService)
@RequestMapping("api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 리뷰 등록 기능 - 등록이나 수정은 Many의 review쪽에서...
     */
    @PostMapping("/{id}")
    public ResponseEntity<ReviewCreateResponse> addReview(@PathVariable Integer id,
                                                          @RequestBody ReviewCreateRequest reviewCreateRequest) {
        return ResponseEntity.ok().body(reviewService.createReview(reviewCreateRequest));
    }

    /**
     * 리뷰 1개 조회 기능
     */
    @GetMapping("{reviewId}")
    public ResponseEntity<ReviewResponse> getEachReview(@PathVariable("reviewId") Integer id) {
        Review review = reviewService.findReview(id);
        return ResponseEntity.ok().body(ReviewResponse.of(review));
    }

    /**
     * 모든 리뷰 조회 기능
     */
    @GetMapping("")
    public ResponseEntity<List<ReviewReadResponse>> getAllReview(Pageable pageable) {
        return ResponseEntity.ok().body(reviewService.findAllReview(pageable));
    }
}
