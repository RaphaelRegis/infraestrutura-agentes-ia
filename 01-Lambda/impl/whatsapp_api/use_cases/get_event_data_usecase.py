def get_message_data(event: dict) -> dict:

    print("Pegando dados da mensagem...")

    data = {
        "agent_id": event["entry"][0]["changes"][0]["value"]["metadata"]["phone_number_id"],
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