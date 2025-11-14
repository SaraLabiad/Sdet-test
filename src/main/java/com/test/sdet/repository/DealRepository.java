package com.test.sdet.repository;

import com.test.sdet.domain.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, String> {
}
