from typing import Any


def get_uazapi_message_data_usecase(event: dict) -> dict:

    # prepara os campos comuns
    #message_data = {
    #    "contact_number": event["message"]["sender"],
    #    "contact_name": event["message"]["senderName"],
    #    "agent_id": event["token"]
    #}

    # verifica o tipo da mensagem
    #if event["message"]["type"] == "text":
    #    message_data["text"] = event["message"]["text"]
    #    message_data["message_type"] = "text"

    #elif event["message"]["messageType"] == "AudioMessage":
    #    message_data["file_id"] = event["message"]["voice"]["file_unique_id"]
    #    message_data["message_type"] = "audio"

    #elif event["message"]["messageType"] == "ImageMessage":
    #    message_data["file_id"] = event["message"]["audio"]["file_unique_id"]
    #    message_data["message_type"] = "audio"

    #elif event["message"]["type"] == "":
    #    message_data["file_id"] = event["message"]["photo"][-1]["file_unique_id"]
    #    message_data["message_type"] = "image"

    #return message_data

    if event.get("EventType") != "messages":
        return None

    msg: dict[str, Any] = event.get("message") or {}
    if not msg:
        return None

    # -- Resolve message type ------------------------------------------------
    raw_type: str = msg.get("type", "")
    media_type_hint: str = msg.get("mediaType", "")
    message_type_hint: str = msg.get("messageType", "")

    if raw_type == "text":
        message_type = "text"
    elif raw_type == "media":
        is_audio = media_type_hint in ("ptt", "audio") or message_type_hint == "AudioMessage"
        is_image = media_type_hint == "image" or message_type_hint == "ImageMessage"
        if is_audio:
            message_type = "audio"
        elif is_image:
            message_type = "image"
        else:
            return None  # unknown media sub-type
    else:
        return None

    # -- Common fields -------------------------------------------------------
    chat: dict[str, Any] = event.get("chat") or {}
    is_group: bool = bool(chat.get("wa_isGroup", False))

    # In group chats, chatid is the group JID; sender_pn carries the real number.
    # In 1:1 chats, chatid IS the contact's number.
    contact_number: str = (
            msg.get("sender_pn")
            or (msg.get("sender") if is_group else msg.get("chatid", ""))
    )

    message_data: dict[str, Any] = {
        "contact_number": contact_number,
        "contact_name": msg.get("senderName", ""),
        "token": event.get("token", ""),
        "instance_name": event.get("instanceName", ""),
        "from_atendent": bool(msg.get("fromMe", False)),
        "message_type": message_type,
    }

    # -- Type-specific fields ------------------------------------------------
    if message_type == "text":
        message_data["text"] = msg.get("text", "") or ""

    else:  # audio or image
        content = msg.get("content")
        content_obj: dict[str, Any] = content if isinstance(content, dict) else {}

        # Resolve download URL — may be a direct URL or assembled from
        # BaseUrl + directPath (both variants exist across Uazapi versions)
        media_url: str | None = content_obj.get("URL") or content_obj.get("url") or None

        if not media_url:
            direct_path: str | None = content_obj.get("directPath") or None
            base_url: str | None = event.get("BaseUrl") or None
            if direct_path and base_url:
                normalized_base = base_url.rstrip("/")
                normalized_path = direct_path if direct_path.startswith("/") else f"/{direct_path}"
                media_url = f"{normalized_base}{normalized_path}"

        # mediaKey may live at message level OR inside content (version-dependent)
        media_key: str | None = (
                content_obj.get("mediaKey")
                or msg.get("mediaKey")
                or None
        )

        message_data["file_id"] = media_url
        message_data["media_key"] = media_key
        message_data["mimetype"] = content_obj.get("mimetype") or None

        if message_type == "image":
            message_data["caption"] = msg.get("text", "") or ""

    return message_data