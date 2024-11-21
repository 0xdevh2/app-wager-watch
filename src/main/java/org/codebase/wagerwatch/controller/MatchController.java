package org.codebase.wagerwatch.controller;

import lombok.RequiredArgsConstructor;
import org.codebase.wagerwatch.model.QuadMatch;
import org.codebase.wagerwatch.model.TrioMatch;
import org.codebase.wagerwatch.repository.QuadMatchRepository;
import org.codebase.wagerwatch.repository.TrioMatchRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller

@RequiredArgsConstructor
public class MatchController {

	private final TrioMatchRepository trioMatchRepository;
	private final QuadMatchRepository quadMatchRepository;

	@ModelAttribute(name = "trioMatches")
	public List<TrioMatch> trioMatches() {
		return trioMatchRepository.findAllOrderedByResultCount();
	}

	@ModelAttribute(name = "quadMatches")
	public List<QuadMatch> quadMatches() {
		return quadMatchRepository.findAllOrderedByResultCount();
	}

	@GetMapping("/match/trio")
	public String matchTrioHandle(Model model) {

		return "match/trio";
	}

	@PostMapping("/match/trio")
	public String matchTrioHandle(TrioMatch match) {

		trioMatchRepository.save(match);

		return "redirect:/analysis/triomatch/"+ match.getId();
	}

	@GetMapping("/match/quad")
	public String matchQuadHandle(Model model) {

		return "match/quad";
	}

	@PostMapping("/match/quad")
	public String matchQuadHandle(QuadMatch match) {

		quadMatchRepository.save(match);

		return "redirect:/analysis/quadmatch/"+ match.getId();
	}

}
