package com.gying.wander.model.dto;

import javax.validation.constraints.NotBlank;

public class AiChatRequest {
    @NotBlank
    private String prompt;

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
