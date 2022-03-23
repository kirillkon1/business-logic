package com.example.buslogic.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class QuestionDTO {

    @NotNull private String title;
    @NotNull private String content;

}
