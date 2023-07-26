package com.example.demo.repository;

import com.example.demo.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Integer>, JpaSpecificationExecutor<Detail> {
    <T> T findFirstById(Integer id, Class<T> type);
}
