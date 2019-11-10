package com.alg.common.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alg.common.app.domain.WDI;

public interface IWDIRepository extends JpaRepository<WDI, Long> {

	List<WDI> findByCountryCodeAndIndicatorCode(String countryCode, String indicatorCode);

	List<WDI> findByIndicatorCode(String indicatorCode);

}
