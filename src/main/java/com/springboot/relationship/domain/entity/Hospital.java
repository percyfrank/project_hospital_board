package com.springboot.relationship.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hospital")
public class Hospital {

    @Id
//    @GeneratedValue
    private Integer id;
    private String hospitalName;
    private String roadNameAddress;

    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY)
    @ToString.Exclude
    /**
     * 양방향 연관관계에서 순환참조 방지
     * 참조가 되는 앞부분을 의미하며, 정상적으로 직렬화를 수행한다.
     * Collection Type 에 적용된다.
     */
    @JsonManagedReference
    private List<Review> reviewList = new ArrayList<>();
}
