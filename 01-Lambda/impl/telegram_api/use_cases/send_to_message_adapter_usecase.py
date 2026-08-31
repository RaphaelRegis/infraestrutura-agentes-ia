import requests

def send_to_message_adapter_usecase(message_adapter_payload: dict, message_type: str) -> dict:

    headers = {
        "Content-Type": "application/json"
    }

    response = requests.post(url=f"http://localhost:8080/api/telegram/{message_type}", headers=headers, json=message_adapter_payload)
    response.raise_for_status()

    return response.json()