package sample.aspect;

public aspect PerformanceAspect {
	pointcut performanceLogging() : execution(* sample.repository.ToDoRepository.*(..));
	
	Object around() : performanceLogging() {
		final long start, end;
		Object result;
		try{
			start = System.nanoTime();
			result = proceed();
		}
		finally {
			end = System.nanoTime();
		}
		
		StringBuilder builder = new StringBuilder(128);
		builder.append("Method `")
				.append(thisJoinPoint.getSignature().getName())
				.append("` was executed in ")
				.append((end - start) / 1000 / 1000)
				.append(" ms");
		
		System.out.println(builder.toString());
		
		return result;
	}
}