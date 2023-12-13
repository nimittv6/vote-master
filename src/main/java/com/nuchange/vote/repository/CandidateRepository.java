package com.nuchange.vote.repository;

import com.nuchange.vote.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
}
