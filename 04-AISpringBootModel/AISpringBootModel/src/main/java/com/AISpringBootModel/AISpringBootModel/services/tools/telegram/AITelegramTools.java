package com.AISpringBootModel.AISpringBootModel.services.tools.telegram;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class AITelegramTools {

    @Tool(description = "Essa tool existe apenas para fins de teste")
    public String exemploTool(@ToolParam(description = "Esse é apenas um parâmetro de exemplo") String parametro) {
        return "MOCK: resultado da tool de teste";
    }
}
