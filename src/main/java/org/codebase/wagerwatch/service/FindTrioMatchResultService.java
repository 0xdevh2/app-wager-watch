package org.codebase.wagerwatch.service;

import org.codebase.wagerwatch.model.TrioMatchResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



@Service
public class FindTrioMatchResultService {
	public List<TrioMatchResult> exeucte() {
		Random r = new Random();
		List<TrioMatchResult> list = new ArrayList<>();
		for (int id = 1; id <= 70; id++) {
			double oneOdds = Math.ceil(r.nextDouble(10.0, 11.0) * 10) / 10;
			double twoOdds = Math.ceil(r.nextDouble(1, 2) * 10) / 10;
			double threeOdds = Math.ceil(r.nextDouble(10.0, 11.0) * 10) / 10;

			String winner = r.nextBoolean() ? "one" : "three";
			var one = new TrioMatchResult(Long.valueOf(id), null, oneOdds, twoOdds, threeOdds, winner);
			list.add(one);
		}
		
		return list;
	}
}
