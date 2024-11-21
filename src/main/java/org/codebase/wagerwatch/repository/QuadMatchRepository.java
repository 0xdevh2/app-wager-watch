package org.codebase.wagerwatch.repository;

import org.codebase.wagerwatch.model.QuadMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuadMatchRepository extends JpaRepository<QuadMatch, Long> {
	@Query("SELECT tm FROM QuadMatch tm " + "LEFT JOIN (SELECT tmr.quadMatch.id AS matchId, COUNT(tmr) AS resultCount "
			+ "FROM QuadMatchResult tmr GROUP BY tmr.quadMatch.id) AS tmrCount " + "ON tm.id = tmrCount.matchId "
			+ "ORDER BY tmrCount.resultCount DESC, tm.id ASC")
	List<QuadMatch> findAllOrderedByResultCount();
}
