package com.artineer.artineerscoreproject.score;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;


public class ScoreValidator implements ConstraintValidator<ValidScore, String> {


    List<String> flags = Arrays.asList(
            "111", "222", "333", "444", "555", "666", "777", "888", "999", "101010", "111111", "121212"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for (String s: flags) {
            if (value.equals(s)) {
                return true;
            }

        }
        return false;
    }
}
