package org.example.transactional.repository;

import org.example.transactional.entity.SecondType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondTypeRepository extends JpaRepository<SecondType, Long> {
}
