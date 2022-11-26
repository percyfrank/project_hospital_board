## [병원,리뷰 관련]

### 프로젝트 구조
1. configuration
   - SwaggerConfigurartion
2. controller
   - HospitalController
   - ReviewController
3. domain
    - dto
      - HospitalReadResponse 
        - 병원 정보 반환 dto
      - HospitalResponse 
        - 병원, 리뷰 정보 dto
      - ReviewCreateRequest 
        - 리뷰 생성 요청 dto
      - ReviewCreateResponse 
        - 리뷰 생성 반환 dto
      - ReviewReadResponse 
        - 리뷰 정보 + 병원 이름 포함
      - ReviewResponse 
        - 리뷰 정보만 포함
    - entity
      - Hospital
      - Review
4. repository
   - HospitalRepository
   - ReviewRepository
5. service
   - HospitalService
   - ReviewService


### 기능
#### 1.병원
- 병원 등록(x)
- 모든 병원 정보 조회
  - ("api/v1/hospitals")
- 특정 병원의 리뷰만 조회
  - ("api/v1/hospitals/{hospitalId}/reviews")
- 특정 병원의 리뷰를 병원 정보와 함께 조회
  - ("api/v1/hospitals/{id}")
#### 2. 리뷰
- 리뷰 등록
  - ("api/v1/reviews/{id}")
- 리뷰 1개 조회
  - ("api/v1/reviews/{reviewId}")
- 리뷰 전제 조회
  - ("api/v1/reviews")