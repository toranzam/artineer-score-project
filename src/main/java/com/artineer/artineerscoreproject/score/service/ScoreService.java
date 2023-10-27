package com.artineer.artineerscoreproject.score.service;

import com.artineer.artineerscoreproject.score.dto.ScoreDto;
import com.artineer.artineerscoreproject.score.entity.Score;
import com.artineer.artineerscoreproject.score.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional /*만약 메소드 실행중 에러가 있을경우 롤백*/
public class ScoreService {

    /*ScoreRepository 의존성 주입*/
    private final ScoreRepository scoreRepository;

    /*난이도 낮은문제 정답 FLAG*/
    private final List<String> easyFlagList = Arrays.asList(
            "111", "222", "333", "444"
    );
    private final List<String> middleFlagList = Arrays.asList(
            "555", "666", "777", "888"
    );
    private final List<String> highFlagList = Arrays.asList(
            "999", "101010", "111111", "121212"
    );

    private final int easyScore = 10;
    private final int middleScore = 20;
    private final int highScore = 30;


    /* 들어온 플래그에 따라서 점수를 계산하는 메소드 */
    public void validationScore(ScoreDto scoreDto) {
        /*scoreDto에 들어온 이름으로 DB에서 객체를 검색*/
        /*반환타입이 옵셔널로 래퍼(감싼)된 상태*/
        Optional<Score> score = scoreRepository.findByName(scoreDto.getName());

        /*문제의 종류 파악(easy, middle, high)*/
        String testLevel = testLevelCheck(scoreDto.getFlag());

        /*빌더패턴으로 객체생성지연*/
        Score.ScoreBuilder scoreBuilder = Score.builder()
                .name(scoreDto.getName());

        /* 만약 이름으로 검색된 객체가 없다면, 스트림을 활용하여 필터로 easyFlagList 값이 매칭되는지 검사 */
        if (score.isEmpty() && testLevel.equals("1")) { /*easy*/
            Optional<String> result = easyFlagList.stream()
                    /*필터를 통해 lowLevelFlags에 입력된 FLAG값이 맞는지 거르기*/
                    .filter(flag -> flag.equals(scoreDto.getFlag()))
                    /*첫번째로 찾은값을 반환*/
                    .findFirst();

            /* 이름이 없을때, easyFlagList에 정답이 있는경우 */
            if (result.isPresent()) {

                /*지연시킨 객체를 생성*/
                easyScoreBuild(result, scoreBuilder);

                /*빌더로 완성된 객체를 가져옴*/
                Score newScore = scoreBuilder.build();

                /*DB에 저장*/
                scoreRepository.save(newScore);
            }
        }
        /* 이름이 없을때, middleFlagList에 정답이 있는경우 */
        if (score.isEmpty() && testLevel.equals("2")) { /*easy*/
            Optional<String> result = middleFlagList.stream()
                    /*필터를 통해 lowLevelFlags에 입력된 FLAG값이 맞는지 거르기*/
                    .filter(flag -> flag.equals(scoreDto.getFlag()))
                    /*첫번째로 찾은값을 반환*/
                    .findFirst();

            /* 이름이 없을때, easyFlagList에 정답이 있는경우 */
            if (result.isPresent()) {

                /*지연시킨 객체를 생성*/
                middleScoreBuild(result, scoreBuilder);

                /*빌더로 완성된 객체를 가져옴*/
                Score newScore = scoreBuilder.build();

                /*DB에 저장*/
                scoreRepository.save(newScore);

            }
        }

        if (score.isEmpty() && testLevel.equals("3")) { /*easy*/
            Optional<String> result = highFlagList.stream()
                    /*필터를 통해 lowLevelFlags에 입력된 FLAG값이 맞는지 거르기*/
                    .filter(flag -> flag.equals(scoreDto.getFlag()))
                    /*첫번째로 찾은값을 반환*/
                    .findFirst();

            /* 이름이 없을때, easyFlagList에 정답이 있는경우 */
            if (result.isPresent()) {

                /*지연시킨 객체를 생성*/
                highScoreBuild(result, scoreBuilder);

                /*빌더로 완성된 객체를 가져옴*/
                Score newScore = scoreBuilder.build();

                /*DB에 저장*/
                scoreRepository.save(newScore);

            }
        }


//        /*만약 이름으로 검색한 객체가 존재한다면, 스트림을 활용하여 필터로 lowLevelFlags에 값이 매칭되는지 검사*/
        if (score.isPresent() && testLevel.equals("1")) { /*score 객체가 존재한다면(null이 아니라면)*/
            Optional<String> result = easyFlagList.stream()
                    .filter(m -> m.equals(scoreDto.getFlag()))
                    .findFirst();

            /*이름이 이미 존재할때, 낮은레벨문제에 정답이 있는경우*/
            if (result.isPresent()) {

                if (result.get().equals(easyFlagList.get(0))) {
                    score.get().setEasy1(easyScore);
                }

                if (result.get().equals(easyFlagList.get(1))) {
                    score.get().setEasy2(easyScore);
                }

                if (result.get().equals(easyFlagList.get(2))) {
                    score.get().setEasy3(easyScore);
                }

                if (result.get().equals(easyFlagList.get(3))) {
                    score.get().setEasy4(easyScore);
                }

                scoreRepository.save(score.get());
            }

        }

        if (score.isPresent() && testLevel.equals("2")) { /*score 객체가 존재한다면(null이 아니라면)*/
            Optional<String> result = middleFlagList.stream()
                    .filter(m -> m.equals(scoreDto.getFlag()))
                    .findFirst();

            /*이름이 이미 존재할때, 낮은레벨문제에 정답이 있는경우*/
            /*옵셔널로 래핑된 객체는 get()으로 꺼낼때 객체가 존재하는지 .isPresent()로 확인후 꺼낼것*/
            if (result.isPresent()) {
                if (result.get().equals(middleFlagList.get(0))) {
                    score.get().setMiddle1(middleScore);
                }

                if (result.get().equals(middleFlagList.get(1))) {
                    score.get().setMiddle2(middleScore);
                }

                if (result.get().equals(middleFlagList.get(2))) {
                    score.get().setMiddle3(middleScore);
                }

                if (result.get().equals(middleFlagList.get(3))) {
                    score.get().setMiddle4(middleScore);
                }
                scoreRepository.save(score.get());
            }
        }

        if (score.isPresent() && testLevel.equals("3")) {
            Optional<String> result = highFlagList.stream()
                    /*필터를 통해 lowLevelFlags에 입력된 FLAG값이 맞는지 거르기*/
                    .filter(flag -> flag.equals(scoreDto.getFlag()))
                    /*첫번째로 찾은값을 반환*/
                    .findFirst();

            if (result.isPresent()) {
                if (result.get().equals(highFlagList.get(0))) {
                    score.get().setHigh1(highScore);
                }

                if (result.get().equals(highFlagList.get(1))) {
                    score.get().setHigh2(highScore);
                }

                if (result.get().equals(highFlagList.get(2))) {
                    score.get().setHigh3(highScore);
                }

                if (result.get().equals(highFlagList.get(3))) {
                    score.get().setHigh4(highScore);
                }

                scoreRepository.save(score.get());
            }
        }
    }

    private void easyScoreBuild(Optional<String> result, Score.ScoreBuilder scoreBuilder) {
        if (result.isPresent()) {
            if (result.get().equals(easyFlagList.get(0))) {
                scoreBuilder
                        .easy1(easyScore)
                        .build();
            }

            if (result.get().equals(easyFlagList.get(1))) {
                scoreBuilder
                        .easy2(easyScore)
                        .build();
            }

            if (result.get().equals(easyFlagList.get(2))) {
                scoreBuilder
                        .easy3(easyScore)
                        .build();
            }

            if (result.get().equals(easyFlagList.get(3))) {
                scoreBuilder
                        .easy4(easyScore)
                        .build();
            }
        }
    }

    private void middleScoreBuild(Optional<String> result, Score.ScoreBuilder scoreBuilder) {
        if (result.isPresent()) {
            if (result.get().equals(middleFlagList.get(0))) {
                scoreBuilder
                        .middle1(middleScore)
                        .build();
            }

            if (result.get().equals(middleFlagList.get(1))) {
                scoreBuilder
                        .middle2(middleScore)
                        .build();
            }

            if (result.get().equals(middleFlagList.get(2))) {
                scoreBuilder
                        .middle3(middleScore)
                        .build();
            }

            if (result.get().equals(middleFlagList.get(3))) {
                scoreBuilder
                        .middle4(middleScore)
                        .build();
            }
        }
    }

    private void highScoreBuild(Optional<String> result, Score.ScoreBuilder scoreBuilder) {
        if (result.isPresent()) {
            if (result.get().equals(highFlagList.get(0))) {
                scoreBuilder
                        .high1(highScore)
                        .build();
            }

            if (result.get().equals(highFlagList.get(1))) {
                scoreBuilder
                        .high2(highScore)
                        .build();
            }

            if (result.get().equals(highFlagList.get(2))) {
                scoreBuilder
                        .high3(highScore)
                        .build();
            }

            if (result.get().equals(highFlagList.get(3))) {

                scoreBuilder
                        .high4(highScore)
                        .build();
            }
        }
    }

    public String testLevelCheck(String s) {
        if (easyFlagList.contains(s))
            return "1";
        if (middleFlagList.contains(s))
            return "2";
        if (highFlagList.contains(s))
            return "3";

        return null;
    }
}
