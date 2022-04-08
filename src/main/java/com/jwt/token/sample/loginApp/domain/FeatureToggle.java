package com.jwt.token.sample.loginApp.domain;

import com.jwt.token.sample.loginApp.constant.FeatureCode;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity(name ="featureToggle")
@Table(name = "FEATURE_TOGGLE")
public class FeatureToggle {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String createdBy;
    private String modifiedBy;
    private Date createdDate;
    private Date modifiedDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private FeatureCode featureCode;
    private String customErrorMessage;
    private Boolean enabled = true;
}
