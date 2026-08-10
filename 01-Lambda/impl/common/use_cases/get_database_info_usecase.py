import os
import json
from dotenv import load_dotenv

def get_database_info_usecase(agent_type: str):

    load_dotenv()

    raw_data = os.environ[f"{agent_type}_DATABASE_API_DATA"]

    agent_data = json.loads(raw_data)

    return agent_data
