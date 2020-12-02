package org.mattpayne.spring.visit.notes.repository;

import org.mattpayne.spring.visit.notes.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByTag(String tagStr);
}
