package com.artineer.artineerscoreproject.score.dto;

import com.artineer.artineerscoreproject.score.ValidScore;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ScoreDto {

    @NotBlank(message = "이름을 필수로 입력해야합니다.")
    private String name;

    @NotBlank(message = "FALG를 필수로 입력해야합니다.")
    @ValidScore(message = "FALG가 잘못되었습니다.")
    private String flag;

}
