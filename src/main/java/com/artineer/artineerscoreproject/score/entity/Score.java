package com.artineer.artineerscoreproject.score.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Score {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int easy1;

    private int easy2;

    private int easy3;

    private int easy4;

    private int middle1;

    private int middle2;

    private int middle3;

    private int middle4;

    private int high1;

    private int high2;

    private int high3;

    private int high4;






}
