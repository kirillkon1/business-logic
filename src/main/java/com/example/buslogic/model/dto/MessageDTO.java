package com.example.buslogic.model.dto;


import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class MessageDTO {
    @NotNull private String content;
}
