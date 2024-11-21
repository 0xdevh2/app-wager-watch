package org.codebase.wagerwatch.repository;

import org.codebase.wagerwatch.model.TrioMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;



public interface TrioMatchRepository extends JpaRepository<TrioMatch, Long> {

	@Query("SELECT tm FROM TrioMatch tm " + "LEFT JOIN (SELECT tmr.trioMatch.id AS matchId, COUNT(tmr) AS resultCount "
			+ "FROM TrioMatchResult tmr GROUP BY tmr.trioMatch.id) AS tmrCount " + "ON tm.id = tmrCount.matchId "
			+ "ORDER BY tmrCount.resultCount DESC, tm.id ASC")
	List<TrioMatch> findAllOrderedByResultCount();
}
