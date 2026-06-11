
from strategy.ProcessWorkflowStrategy import ProcessWorkflowStrategy

def get_correct_strategy(event) -> str:
    # TODO: adicionar implementacao real
    return "whatsapp_api"

def lambda_handler(event, context):

    try:

        print("Iniciando função Lambda...")
        # pega o workflow correto
        workflow_id = get_correct_strategy(event)
        process_workflow = ProcessWorkflowStrategy().get_workflow(workflow_id)

        # roda o workflow da mensagem de acordo com o tipo
        print(f"Iniciando o workflow do tipo {workflow_id}...")
        result = process_workflow.run(event)

        return {
            "statusCode": 200,
            "body": result,
        }

    except Exception as e:
        return {
            "statusCode": 500,
            "body": str(e),
        }