package com.AISpringBootModel.AISpringBootModel.services.tools.telegram;

import com.AISpringBootModel.AISpringBootModel.services.tools.common.RegisterIndividualToolUsecase;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AITelegramTools {

    private RegisterIndividualToolUsecase registerIndividualToolUsecase;

    @Tool(description = "Essa tool existe apenas para fins de teste")
    public String exemploTool(@ToolParam(description = "Esse é apenas um parâmetro de exemplo") String parametro, ToolContext toolContext) {

        // esse metodo vai fazer o registro da tool no Redis ao final da execucao de cada tool
        // os campos que devem ser pegos sao: nome da tool, argumentos da IA, argumentos do toolContext se existirem e resultado
        registerIndividualToolUsecase.registerIndividualToolUsecase();
        return "MOCK: resultado da tool de teste";
    }

}
