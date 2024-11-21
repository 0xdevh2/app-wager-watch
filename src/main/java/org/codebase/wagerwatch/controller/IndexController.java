package org.codebase.wagerwatch.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codebase.wagerwatch.model.QuadMatch;
import org.codebase.wagerwatch.model.TrioMatch;
import org.codebase.wagerwatch.repository.QuadMatchRepository;
import org.codebase.wagerwatch.repository.TrioMatchRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {
	private final TrioMatchRepository trioMatchRepository;
	private final QuadMatchRepository quadMatchRepository;

	@ModelAttribute(name = "trioMatches")
	public List<TrioMatch> trioMatches() {
		log.info("default model attribute ");
		return trioMatchRepository.findAllOrderedByResultCount();
	}

	@ModelAttribute(name="quadMatches")
	public List<QuadMatch> quadMatches() {
		return quadMatchRepository.findAllOrderedByResultCount();
	}

	@GetMapping("/")
	public String indexHandle() {
		log.info("trioMatchRepository size:" + trioMatchRepository.findAllOrderedByResultCount().size());
		return "index";
	}
}
