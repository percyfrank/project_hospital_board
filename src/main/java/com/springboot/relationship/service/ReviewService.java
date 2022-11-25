package com.springboot.relationship.service;

import com.springboot.relationship.domain.dto.ReviewCreateRequest;
import com.springboot.relationship.domain.dto.ReviewCreateResponse;
import com.springboot.relationship.domain.dto.ReviewReadResponse;
import com.springboot.relationship.domain.dto.ReviewResponse;
import com.springboot.relationship.domain.entity.Hospital;
import com.springboot.relationship.domain.entity.Review;
import com.springboot.relationship.repository.HospitalRepository;
import com.springboot.relationship.repository.ReviewRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final HospitalRepository hospitalRepository;

    public ReviewService(ReviewRepository reviewRepository, HospitalRepository hospitalRepository) {
        this.reviewRepository = reviewRepository;
        this.hospitalRepository = hospitalRepository;
    }

     public ReviewCreateResponse createReview(ReviewCreateRequest dto) {
        // 리뷰엔 hospital이 들어가있기 때문에 먼저 hospital 조회
        Optional<Hospital> hospital = hospitalRepository.findById(dto.getHospitalId());
//        Optional<Hospital> hospital = hospitalRepository.findById(id); -> 이렇게 하면 id가 hospitalid가 아닌 값이 들어감

        // ReviewEntity 생성
        Review review = Review.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .patientName(dto.getPatientName())
                .hospital(hospital.get())   // 없으면 에러처리도 해줘야 함 - 추후 완성
                .build();

        // 리뷰 저장
        Review savedReview = reviewRepository.save(review);

        return new ReviewCreateResponse(
                savedReview.getId(),
                savedReview.getTitle(),
                savedReview.getContent(),
                savedReview.getPatientName(),
                "리뷰 등록이 성공 했습니다.");

    }


    public Review getReview(Integer id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id가 없습니다."));
        return review;

//        Optional<Review> optionalReview = reviewRepository.findById(id);
//        optionalReview.isEmpty();
//        optionalReview.orElseThrow();
    }
}
