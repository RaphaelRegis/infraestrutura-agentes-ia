from datetime import datetime, timezone
import re


def is_conversation_paused(pause_minutes: int, paused_at: str) -> bool:
    print("Verificando se a conversa está pausada...")
    # a ideia eh que o update da conversa representa

    # Se o update for None, ja retorna False
    if paused_at == None:
        return False

    # Normaliza "+00" para "+00:00" (exigido pelo fromisoformat no Python < 3.11)
    normalized = re.sub(r'([+-]\d{2})$', r'\1:00', paused_at)

    updated_dt = datetime.fromisoformat(normalized)
    now = datetime.now(timezone.utc)

    elapsed_minutes = (now - updated_dt).total_seconds() / 60

    if elapsed_minutes > pause_minutes:
        return False


    return True
    ...