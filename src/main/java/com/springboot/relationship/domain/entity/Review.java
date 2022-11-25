package com.springboot.relationship.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String patientName;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    /**
     * 양방향 연관관계에서 순환참조 방지
     * 참조가 되는 뒷부분을 의미하며, 직렬화를 수행하지 않는다.
     */
    @JsonBackReference  // 양방향 연관관계에서 순환참조 방지
    private Hospital hospital;
}
