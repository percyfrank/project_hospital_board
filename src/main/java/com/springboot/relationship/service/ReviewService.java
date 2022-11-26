package com.springboot.relationship.service;

import com.springboot.relationship.domain.dto.ReviewCreateRequest;
import com.springboot.relationship.domain.dto.ReviewCreateResponse;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final HospitalRepository hospitalRepository;


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

    public Review findReview(Integer id) {
        // 리포에서 찾은 데이터가 null일 경우 RuntimeException 에외를 던지고,
        // 데이터가 있다면 review 엔티티를 꺼낸다.
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 리뷰가 없습니다."));
    }

    public List<ReviewReadResponse> findAllReview(Pageable pageable) {
        Page<Review> reviews = reviewRepository.findAll(pageable);

        return reviews.stream()
                .map(review -> ReviewReadResponse.of(review)).collect(Collectors.toList());
    }

    public List<ReviewReadResponse> findAllByHospitalId(Integer hospitalId) {
         // hospitalId로 hospital 가져오기
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new IllegalArgumentException("해당 병원을 찾을 수 없습니다."));

        // Review리포에서 hostpital entity로 리뷰 정보 가져오기
        return reviewRepository.findByHospital(hospital).stream()
                .map(review -> ReviewReadResponse.of(review)).collect(Collectors.toList());
    }
}
