package sample.aspect;

import sample.helper.LogHelper;
import static sample.helper.Utils.append;

public aspect LogAspect {
	
	pointcut doLogging():execution(* sample.repository.ToDoRepository.*(..));
	
	before():doLogging() {
		LogHelper.Instance.LogInfo(append("Entered method: ", thisJoinPoint.getSignature().getName(), ".\n"));
		
		Object[] arguments = thisJoinPoint.getArgs();
		if (arguments.length > 0) {
			LogHelper.Instance.LogInfo("With arguments:");
			for (int i = 0; i < arguments.length; i++) {
				Object argument = arguments[i];
				if (argument != null) {
					LogHelper.Instance.LogInfo("\t" + argument.getClass().getSimpleName() + " -> " + argument + "\n");
				}
			}
		}
	}
	
	after():doLogging() {
		LogHelper.Instance.LogInfo(append("Exited method:", thisJoinPoint.getSignature().getName(), "\n"));
	}
}
