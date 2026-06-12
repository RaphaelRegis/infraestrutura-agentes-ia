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

        # pega os campos necessarios do webhook do whatsapp
        # numero, nome do contato, mensagem e id do agente
        message_data = get_message_data(event)

        # pega as variaveis de ambiente de acordo com o id do agente
        agent_data = get_agent_data(f"WHATSAPP_API_{message_data["agent_id"]}")

        # busca a conversa no database
        # cria se nao encontrar
        ai_conversation = upsert_conversation_usecase(agent_data)

        # verifica se a mensagem eh do atendente
        # se for do atendente: pausa a ia, salva no historico e finaliza
        # se nao for do atendente: prossegue com o fluxo
        message_from_atendent = is_from_attendent(message_data)

        if message_from_atendent:
            process_atendent_message(message_data)
            return {}

        # verifica se a conversa esta pausada
        # se estiver pausada: salva no historico e finaliza
        # se nao estiver pausada: prossegue com o fluxo
        paused_conversation = is_conversation_paused(ai_conversation)

        if paused_conversation:
            process_paused_message(message_data, ai_conversation)
            return {}

        # manda mensagem para o debouncer
        # prepara o payload com: id do agente, nome do contato, numero do contato, id da conversa, tipo de mensagem, mensagem e tempo de debounce
        debouncer_payload = prepare_debounce_payload(ai_conversation, message_data, agent_data)

        # chamar o debouncer
        result = send_message_to_debouncer(debouncer_payload)

        return result