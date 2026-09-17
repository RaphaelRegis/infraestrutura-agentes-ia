import os
import json
from dotenv import load_dotenv

def get_agents_database_info_usecase():

    load_dotenv()

    raw_data = os.environ[f"AGENTS_DATABASE_API_DATA"]

    agent_data = json.loads(raw_data)

    return agent_data
