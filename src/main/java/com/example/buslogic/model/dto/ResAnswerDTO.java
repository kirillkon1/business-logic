package com.example.buslogic.model.dto;

import lombok.Data;

@Data
public class ResAnswerDTO {
    private Long id;
    private Long question_id;
    private String content;
    private Long user_id;
    private Long rating;

}
