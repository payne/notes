package org.mattpayne.spring.visit.notes.repository;

import org.mattpayne.spring.visit.notes.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
}
