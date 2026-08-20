def prepare_telegram_message_adapter_payload_usecase(message_data: dict, ai_conversation: dict, agent_data: dict, is_paused: bool) -> dict:

    message_adapter_payload = {
        "agentID": agent_data["agent_uuid"],
        "chatID": ai_conversation["id"],
        "contactName": message_data["contact_name"],
        "isPaused": is_paused,
        "debounceSeconds": agent_data["debounce_seconds"]
    }


    match message_data["message_type"]:
        case "text":
            message_adapter_payload["message"] = message_data["text"]

        case "audio":
            message_adapter_payload["fileID"] = message_data["file_id"]
            message_adapter_payload["botToken"] = agent_data["bot_token"]

        case "image":
            message_adapter_payload["fileID"] = message_data["file_id"]
            message_adapter_payload["imageCaption"] = message_data["image_caption"]
            message_adapter_payload["botToken"] = agent_data["bot_token"]




    return message_adapter_payload











    ...