package sample.aspect;

import sample.helper.LogHelper;

public aspect LogAspect {

    pointcut doLogging():execution(* sample.repository.ToDoRepository.*(..));

    before():doLogging() {
        System.out.println("TEST!");
        LogHelper.Instance.LogInfo("Entered method: " + thisJoinPoint.getSignature().getName() + ".\n");

        Object[] arguments = thisJoinPoint.getArgs();
        if (arguments.length > 0) {
            LogHelper.Instance.LogInfo("With arguments:");
            for (int i = 0; i < arguments.length; i++){
                Object argument = arguments[i];
                if (argument != null){
                    LogHelper.Instance.LogInfo("\t" + argument.getClass().getSimpleName() + " -> " +  argument + "\n");
                }
            }
        }
    }

    after():doLogging() {
        System.out.printf("Exited method: %s. \n", thisJoinPoint.getSignature().getName());
    }
}
