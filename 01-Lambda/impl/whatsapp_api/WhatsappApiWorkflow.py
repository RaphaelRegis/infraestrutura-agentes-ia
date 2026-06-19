from impl.common.use_cases.GetSupabaseDataUseCase import get_supabase_data
from strategy import ProcessWorkflow
from impl.whatsapp_api.use_cases.PrepareDebouncePayloadUsecase import prepare_debounce_payload
from impl.whatsapp_api.use_cases.sendMessageToDebouncerUsecase import send_message_to_debouncer
from impl.whatsapp_api.use_cases.IsConversationPausedUsecase import is_conversation_paused
from impl.whatsapp_api.use_cases.IsFromAtendentUsecase import is_from_attendent
from impl.whatsapp_api.use_cases.ProcessAtendentMessageUsecase import process_atendent_message
from impl.whatsapp_api.use_cases.ProcessPausedMessageUsecase import process_paused_message
from impl.whatsapp_api.use_cases.UpsertConversationUsecase import upsert_conversation_usecase
from impl.whatsapp_api.use_cases.GetAgentDataUsecase import get_agent_data
from impl.whatsapp_api.use_cases.GetEventDataUsecase import get_message_data


class WhatsappApiWorkflow(ProcessWorkflow.ProcessWorkflow):

    def __init__(self):
        super().__init__()

    @staticmethod
    def run(event: dict) -> dict:
        message_data = get_message_data(event)
        supabase_data = get_supabase_data("WHATSAPP_API")
        agent_data = get_agent_data(f"WHATSAPP_API_{message_data["agent_id"]}")
        ai_conversation = upsert_conversation_usecase(message_data["contactName"], message_data["contactNumber"], agent_data["agent_uuid"], supabase_data["url"], supabase_data["api_key"])

        # verifica se a mensagem eh do atendente
        message_from_atendent = is_from_attendent(message_data)

        # se for do atendente: pausa a ia, salva no historico e finaliza
        # se nao for do atendente: prossegue com o fluxo
        if message_from_atendent:
            process_atendent_message(message_data)
            return {}


        paused_conversation = is_conversation_paused(agent_data["pause_minutes"], ai_conversation["paused_at"])

        if paused_conversation:
            process_paused_message(message_data, ai_conversation)
            return {}

        # manda mensagem para o debouncer
        # prepara o payload com: id do agente, nome do contato, numero do contato, id da conversa, tipo de mensagem, mensagem e tempo de debounce
        debouncer_payload = prepare_debounce_payload(ai_conversation, message_data, agent_data)

        # chamar o debouncer
        result = send_message_to_debouncer(debouncer_payload)

        return result