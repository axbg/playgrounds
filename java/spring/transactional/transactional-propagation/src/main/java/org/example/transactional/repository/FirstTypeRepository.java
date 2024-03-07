package org.example.transactional.repository;

import org.example.transactional.entity.FirstType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstTypeRepository extends JpaRepository<FirstType, Long> {
}
