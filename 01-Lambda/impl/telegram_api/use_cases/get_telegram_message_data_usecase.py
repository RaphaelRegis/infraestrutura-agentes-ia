def get_telegram_message_data_usecase(event: dict) -> dict:

    # prepara os campos comuns
    # TODO: arrumar o agent_id
    message_data = {
        "agent_id": "",
        "contact_number": event["message"]["chat"]["id"],
        "contact_name": event["message"]["chat"]["username"],
    }

    # verifica o tipo da mensagem
    if "text" in event["message"]:
        message_data["text"] = event["message"]["text"]
        message_data["message_type"] = "text"

    elif "voice" in event["message"]:
        message_data["file_id"] = event["message"]["voice"]["file_unique_id"]
        message_data["message_type"] = "audio"

    elif "audio" in event["message"]:
        message_data["file_id"] = event["message"]["audio"]["file_unique_id"]
        message_data["message_type"] = "audio"

    elif "photo" in event["message"]:
        message_data["file_id"] = event["message"]["photo"][-1]["file_unique_id"]
        message_data["message_type"] = "image"

    return message_data