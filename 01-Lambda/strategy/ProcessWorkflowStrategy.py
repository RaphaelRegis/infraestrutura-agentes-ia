from impl.telegram_api import TelegramApiWorkflow
from impl.whatsapp_api import WhatsappApiWorkflow


class ProcessWorkflowStrategy:

    def __init__(self):

        self.workflows = {
            "whatsapp_api": WhatsappApiWorkflow.WhatsappApiWorkflow,
            "telegram_api": TelegramApiWorkflow.TelegramApiWorkflow,
        }

    def get_workflow(self, origin):
        return self.workflows[origin]





