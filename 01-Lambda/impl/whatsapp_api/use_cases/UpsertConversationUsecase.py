import requests
import uuid

def search_conversation(contact_name: str, contact_number: str, agent_uuid: str, url: str, api_key: str) -> list:

    headers = {
        "apikey": api_key,
        "Authorization": api_key,
    }

    params = {
        "select": "*",
        "agent_uuid": f"eq.{agent_uuid}",
        "contact_name": f"eq.{contact_name}",
        "contact_number": f"eq.{contact_number}",
    }

    response = requests.request("GET", url=f"{url}/rest/v1/ai_conversations", headers=headers, params=params)
    response.raise_for_status()

    data = response.json()

    return data

def create_conversation(contact_name: str, contact_number: str, agent_uuid: str, url: str, api_key: str):
    new_conversation = {
        "id": str(uuid.uuid4()),
        "agent_uuid": agent_uuid,
        "contact_name": contact_name,
        "contact_number": contact_number
    }

    headers = {
        "apikey": api_key,
        "Authorization": api_key,
        "Content-Type": "application/json",
        "Prefer": "return=representation",
    }

    response = requests.post(url=f"{url}/rest/v1/ai_conversations", headers=headers, json=new_conversation)
    response.raise_for_status()

    return response.json()[0]

def upsert_conversation_usecase(contact_name: str, contact_number: str, agent_uuid: str, url: str, api_key: str):

    print("Criando ou buscando os dados da conversa...")

    conversation = search_conversation(contact_name, contact_number, agent_uuid, url, api_key)

    if conversation == []:
        conversation.append(create_conversation(contact_name, contact_number, agent_uuid, url, api_key))

    # retorna a conversa
    return conversation[0]