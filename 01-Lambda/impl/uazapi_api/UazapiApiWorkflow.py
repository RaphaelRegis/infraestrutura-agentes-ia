from impl.common.use_cases.get_database_info_usecase import get_agents_database_info_usecase
from impl.common.use_cases.send_to_message_adapter_usecase import send_to_message_adapter_usecase
from impl.uazapi_api.use_cases.get_uazapi_message_data_usecase import get_uazapi_message_data_usecase
from impl.uazapi_api.use_cases.prepare_uazapi_message_adapter_payload_usecase import \
    prepare_uazapi_message_adapter_payload_usecase
from impl.common.use_cases.get_agent_data_usecase import get_agent_data_usecase
from impl.common.use_cases.is_conversation_paused_usecase import is_conversation_paused_usecase
from impl.common.use_cases.find_or_create_conversation_usecase import find_or_create_conversation_usecase
from strategy import ProcessWorkflow

class UazapiApiWorkflow(ProcessWorkflow.ProcessWorkflow):

    def __init__(self):
        super().__init__()

    @staticmethod
    def run(event: dict) -> dict:
        # pega os dados da mensagem
        message_data = get_uazapi_message_data_usecase(event)

        # pega as informacoes do banco de dados
        database_info = get_agents_database_info_usecase()

        # pega os dados do agente
        agent_data = get_agent_data_usecase(f"UAZAPI_API_{message_data.get('instance_name')}")

        # cria ou busca/atualiza a conversa
        ai_conversation = find_or_create_conversation_usecase(message_data["contact_name"], message_data["contact_number"], agent_data["agent_uuid"], database_info["url"], database_info["api_key"])

        # verifica se esta pausada
        is_paused = is_conversation_paused_usecase(agent_data["pause_minutes"], ai_conversation["paused_at"])

        # prepara o corpo para o message_adapter
        message_adapter_payload = prepare_uazapi_message_adapter_payload_usecase(message_data, ai_conversation, agent_data, is_paused)

        # envia de maneira assincrona para o message_adapter
        result = send_to_message_adapter_usecase(message_adapter_payload, message_data["message_type"])

        return message_data