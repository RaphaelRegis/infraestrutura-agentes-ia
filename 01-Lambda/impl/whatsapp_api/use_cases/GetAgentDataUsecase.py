import os
import json
from dotenv import load_dotenv

def get_agent_data(agent_id: str) -> dict:
        print("Pegando dados do agente...")

        load_dotenv()

        raw_data = os.environ[agent_id]

        agent_data = json.loads(raw_data)

        return agent_data