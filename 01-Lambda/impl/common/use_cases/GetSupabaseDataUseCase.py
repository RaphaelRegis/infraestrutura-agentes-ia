import os
import json
from dotenv import load_dotenv

def get_supabase_data(agent_type: str):

    load_dotenv()

    raw_data = os.environ[f"{agent_type}_SUPABASE_API_DATA"]

    agent_data = json.loads(raw_data)

    return agent_data
