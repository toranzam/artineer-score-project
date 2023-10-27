package com.artineer.artineerscoreproject.score.controller;

import com.artineer.artineerscoreproject.score.dto.ScoreDto;
import com.artineer.artineerscoreproject.score.entity.Score;
import com.artineer.artineerscoreproject.score.repository.ScoreRepository;
import com.artineer.artineerscoreproject.score.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreRepository scoreRepository;

    private final ScoreService scoreService;

    @GetMapping("/")
    public String showMainPage(Model model) {
        List<Score> scoreList = scoreRepository.findAll();

        model.addAttribute("scoreDto", new ScoreDto());
        model.addAttribute("scoreList", scoreList);

        return "index";
    }

    @PostMapping("/")
    public String createScore(@Valid ScoreDto scoreDto, BindingResult result) {

        if (result.hasErrors()) {
            return "index";
        }

        scoreService.validationScore(scoreDto);

        return "redirect:/";

    }

    @GetMapping("/grades")
    public String showGrades() {
        return "grades";
    }


}






