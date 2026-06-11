from unittest import case
import os


def getEventData(event) -> dict:

    data = {
        "contactNumber": event["entry"][0]["changes"][0]["value"]["contacts"][0]["wa_id"],
        "contactName": event["entry"][0]["changes"][0]["value"]["contacts"][0]["profile"]["name"]
    }

    match event["entry"][0]["changes"][0]["value"]["messages"][0]["type"]:
        case "text":
            data["message"] = {
                "type": "text",
                "content": event["entry"][0]["changes"][0]["value"]["messages"][0]["text"]["body"]
            }

        case "image":
            data["message"] = {
                "type": "image",
                "media_id": event["entry"][0]["changes"][0]["value"]["messages"][0]["image"]["id"],
                "caption": event["entry"][0]["changes"][0]["value"]["messages"][0]["image"]["caption"]
            }

        case "audio":
            data["message"] = {
                "type": "audio",
                "media_id": event["entry"][0]["changes"][0]["value"]["messages"][0]["audio"]["id"],
            }

    return data


def getAgentData():
    pass


def findOrCreateConversation(agent_data):
    pass

def isFromAtendent(event_data):
    pass

def processAtendentMessage(event_data):
    ...

def isConversationPaused(conversation):
    ...

def processMessage(event_data):
    ...


def prepareDebouncePayload(ai_conversation, event_data, agent_data):
    pass


def sendMessageToDebouncer(debouncer_payload):
    pass


def lambda_handler(event, context):

    # pega os campos necessarios do webhook do whatsapp 
    # numero, nome do contato, mensagem e id do agente
    event_data = getEventData(event)

    #pega as variaveis de ambiente de acordo com o id do agente
    agent_data = getAgentData();

    # busca a conversa no database
    # cria se nao encontrar
    ai_conversation = findOrCreateConversation(agent_data);

    # verifica se a mensagem eh do atendente
    # se for do atendente: pausa a ia, salva no historico e finaliza
    # se nao for do atendente: prossegue com o fluxo
    message_from_atendent = isFromAtendent(event_data)

    if message_from_atendent:
        processAtendentMessage(event_data)
        exit()

    # verifica se a conversa esta pausada
    # se estiver pausada: salva no historico e finaliza
    # se nao estiver pausada: prossegue com o fluxo
    paused_conversation = isConversationPaused(ai_conversation)

    if paused_conversation:
        processMessage(event_data)
        exit()

    # manda mensagem para o debouncer
    # prepara o payload com: id do agente, nome do contato, numero do contato, id da conversa, tipo de mensagem, mensagem e tempo de debounce
    debouncer_payload = prepareDebouncePayload(ai_conversation, event_data, agent_data)

    # chamar o debouncer
    sendMessageToDebouncer(debouncer_payload)

    return {
        "statusCode": 200,
        "body": {},
    }