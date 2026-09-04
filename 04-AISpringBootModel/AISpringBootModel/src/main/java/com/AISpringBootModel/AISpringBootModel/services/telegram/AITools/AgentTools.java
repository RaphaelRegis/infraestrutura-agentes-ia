package com.AISpringBootModel.AISpringBootModel.services.telegram.AITools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class AgentTools {

    @Tool(description = "Essa tool existe apenas para fins de teste")
    public String exemploTool(@ToolParam(description = "Esse é apenas um parâmetro de exemplo") String parametro) {
        return "MOCK: resultado da tool de teste";
    }
}