package org.codebase.wagerwatch.repository;

import org.codebase.wagerwatch.model.QuadMatch;
import org.codebase.wagerwatch.model.QuadMatchResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuadMatchResultRepository extends JpaRepository<QuadMatchResult, Long>{
	public List<QuadMatchResult> findByQuadMatch(QuadMatch quadMatch);
}
