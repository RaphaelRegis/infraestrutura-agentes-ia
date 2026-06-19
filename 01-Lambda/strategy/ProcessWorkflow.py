from abc import ABC, abstractmethod


class ProcessWorkflow(ABC):
    def __init__(self):
        ...

    @staticmethod
    @abstractmethod
    def run(event: dict) -> dict:
        ...