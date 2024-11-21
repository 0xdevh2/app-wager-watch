package org.codebase.wagerwatch.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codebase.wagerwatch.model.QuadMatch;
import org.codebase.wagerwatch.model.QuadMatchResult;
import org.codebase.wagerwatch.model.TrioMatch;
import org.codebase.wagerwatch.model.TrioMatchResult;
import org.codebase.wagerwatch.repository.QuadMatchRepository;
import org.codebase.wagerwatch.repository.QuadMatchResultRepository;
import org.codebase.wagerwatch.repository.TrioMatchRepository;
import org.codebase.wagerwatch.repository.TrioMatchResultRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
public class AnalysisController {
	private final TrioMatchRepository trioMatchRepository;
	private final TrioMatchResultRepository trioMatchResultRepository;
	// private final FindTrioMatchResultService findTrioMatchResultService;
	private final QuadMatchRepository quadMatchRepository;
	private final QuadMatchResultRepository quadMatchResultRepository;

	@ModelAttribute(name = "trioMatches")
	public List<TrioMatch> trioMatches() {
		return trioMatchRepository.findAllOrderedByResultCount();
	}

	@ModelAttribute(name = "quadMatches")
	public List<QuadMatch> quadMatches() {
		return quadMatchRepository.findAllOrderedByResultCount();
	}

	@GetMapping("/analysis/triomatch/{matchId}")
	public String analysisTrioHandle(@PathVariable(required = false) Long matchId, Model model) {
		var match = trioMatchRepository.findById(matchId).get();
		model.addAttribute("match", match);

		model.addAttribute("results", trioMatchResultRepository.findByTrioMatch(match));
		log.info("findByTrioMatch size:" + trioMatchResultRepository.findByTrioMatch(match).size());
		return "analysis/trio";
	}

	@PostMapping("/analysis/triomatch/{matchId}")
	public String indexHandle(@PathVariable(required = false) Long matchId, TrioMatchResult entity, Model model) {
		log.info("entity for save: " + entity);
		var match = trioMatchRepository.findById(matchId).get();

		entity.setTrioMatch(match);
		trioMatchResultRepository.save(entity);

		return "redirect:/analysis/triomatch/" + matchId;
	}

	@GetMapping("/analysis/quadmatch/{matchId}")
	public String analysisQuadHandle(@PathVariable(required = false) Long matchId, Model model) {
		var match = quadMatchRepository.findById(matchId).get();
		model.addAttribute("match", match);

		model.addAttribute("results", quadMatchResultRepository.findByQuadMatch(match));
		// log.info("findByTrioMatch size:" +
		// trioMatchResultRepository.findByTrioMatch(match).size());
		return "analysis/quad";
	}

	@PostMapping("/analysis/quadmatch/{matchId}")
	public String analysisQuadHandle(@PathVariable(required = false) Long matchId, QuadMatchResult entity,
			Model model) {
		var match = quadMatchRepository.findById(matchId).get();

		entity.setQuadMatch(match);

		quadMatchResultRepository.save(entity);

		return "redirect:/analysis/quadmatch/" + matchId;
	}
}
