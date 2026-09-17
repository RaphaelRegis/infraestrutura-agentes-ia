def prepare_uazapi_message_adapter_payload_usecase(message_data: dict, ai_conversation: dict, agent_data: dict, is_paused: bool) -> dict:

    message_adapter_payload = {
        "agentID": agent_data["agent_uuid"],
        "chatID": ai_conversation["id"],
        "contactName": message_data["contact_name"],
        "isPaused": is_paused,
        "fromAtendent": message_data["from_atendent"],
        "debounceSeconds": agent_data["debounce_seconds"],
        "token": message_data["token"],
        "messageType": message_data["message_type"],
        "messageMechanism": "uazapi"
    }


    match message_data["message_type"]:
        case "text":
            message_adapter_payload["message"] = message_data["text"]

        case "audio":
            message_adapter_payload["fileID"] = message_data["file_id"]
            message_adapter_payload["mediaKey"] = message_data["media_key"]
            message_adapter_payload["mimetype"] = message_data["mimetype"]

        case "image":
            message_adapter_payload["fileID"] = message_data["file_id"]
            message_adapter_payload["mediaKey"] = message_data["media_key"]
            message_adapter_payload["mimetype"] = message_data["mimetype"]
            message_adapter_payload["imageCaption"] = message_data["caption"]




    return message_adapter_payload











    ...