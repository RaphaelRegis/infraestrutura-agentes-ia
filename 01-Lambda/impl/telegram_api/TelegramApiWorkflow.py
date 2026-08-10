from impl.common.use_cases.get_database_info_usecase import get_database_info_usecase
from impl.telegram_api.use_cases.send_to_message_adapter_usecase import send_to_message_adapter_usecase
from impl.telegram_api.use_cases.get_telegram_message_data_usecase import get_telegram_message_data_usecase
from impl.telegram_api.use_cases.prepare_telegram_message_adapter_payload_usecase import \
    prepare_telegram_message_adapter_payload_usecase
from impl.whatsapp_api.use_cases.GetAgentDataUsecase import get_agent_data
from impl.whatsapp_api.use_cases.IsConversationPausedUsecase import is_conversation_paused
from impl.whatsapp_api.use_cases.UpsertConversationUsecase import find_or_create_conversation_usecase
from strategy import ProcessWorkflow

class TelegramApiWorkflow(ProcessWorkflow.ProcessWorkflow):

    def __init__(self):
        super().__init__()

    @staticmethod
    def run(event: dict) -> dict:
        # pega os dados da mensagem
        message_data = get_telegram_message_data_usecase(event)

        # pega as informacoes do banco de dados
        database_info = get_database_info_usecase("TELEGRAM_API")

        # pega os dados do agente
        agent_data = get_agent_data(f"TELEGRAM_API_{message_data["agent_id"]}")

        # cria ou busca/atualiza a conversa
        ai_conversation = find_or_create_conversation_usecase(database_info)

        # verifica se esta pausada
        is_paused = is_conversation_paused(ai_conversation)

        # prepara o corpo para o message_adapter
        message_adapter_payload = prepare_telegram_message_adapter_payload_usecase(message_data, ai_conversation, agent_data, is_paused)

        # envia de maneira assincrona para o message_adapter
        result = send_to_message_adapter_usecase(message_adapter_payload)

        return result