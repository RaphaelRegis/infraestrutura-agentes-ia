from unittest import case


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


def lambda_handler(event, context):

    # pega os campos necessarios do webhook do whatsapp 
    # numero, nome do contato, mensagem e id do agente
    data = getEventData(event)

    #pega as variaveis de ambiente de acordo com o id do agente


    # busca a conversa no database
    # cria se nao encontrar

    # verifica se a mensagem eh do atendente
    # se for do atendente: pausa a ia, salva no historico e finaliza
    # se nao for do atendente: prossegue com o fluxo

    # verifica se a conversa esta pausada
    # se estiver pausada: salva no historico e finaliza
    # se nao estiver pausada: prossegue com o fluxo

    # manda mensagem para o debouncer
    # prepara o payload com: id do agente, nome do contato, numero do contato, id da conversa, tipo de mensagem, mensagem e tempo de debounce

    # chamar o debouncer
    


    return {
        "statusCode": 200,
        "body": data,
    }