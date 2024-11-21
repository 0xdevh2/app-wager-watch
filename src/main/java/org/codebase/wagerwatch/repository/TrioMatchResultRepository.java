package org.codebase.wagerwatch.repository;

import org.codebase.wagerwatch.model.TrioMatch;
import org.codebase.wagerwatch.model.TrioMatchResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface TrioMatchResultRepository extends JpaRepository<TrioMatchResult, Long>{
	public List<TrioMatchResult> findByTrioMatch(TrioMatch trioMatch);
}
