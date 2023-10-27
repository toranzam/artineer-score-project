package com.artineer.artineerscoreproject.score.dto;

import com.artineer.artineerscoreproject.score.ValidScore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.Annotation;

@Data
public class ScoreDto {

    @NotBlank(message = "이름을 필수로 입력해야합니다.")
    private String name;

    @NotBlank(message = "FALG를 필수로 입력해야합니다.")
    @ValidScore(message = "FALG가 잘못되었습니다.")
    private String flag;

}
