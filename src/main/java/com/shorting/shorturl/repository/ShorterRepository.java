package com.shorting.shorturl.repository;


import com.shorting.shorturl.entities.ShorterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories(basePackageClasses = ShorterRepository.class)
public interface ShorterRepository extends JpaRepository<ShorterEntity, Long> {
    ShorterEntity findByHash(String hash);
    Boolean existsByHash(String hash);
}
