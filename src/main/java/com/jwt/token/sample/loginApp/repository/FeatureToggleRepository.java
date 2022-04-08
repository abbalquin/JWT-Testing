package com.jwt.token.sample.loginApp.repository;

import com.jwt.token.sample.loginApp.constant.FeatureCode;
import com.jwt.token.sample.loginApp.domain.entity.FeatureToggle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeatureToggleRepository extends JpaRepository<FeatureToggle, Long> {

    Optional<FeatureToggle> findByFeatureCode(FeatureCode featureCode);

    //Optional<FeatureToggle> findByFeatureCodeAndCustomErrorMessageNotNull(FeatureCode featureCode);
}
