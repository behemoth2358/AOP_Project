package sample.aspect;

import sample.helper.LogHelper;
import java.util.HashMap;
import java.util.Map;

import static sample.helper.Utils.append;

public aspect CachingAspect {
	private Map<String, Object> cache;

	public CachingAspect() {
		this.cache = new HashMap<>();
	}

	pointcut CachingPointcut(): execution(* sample.manager.IRepository.executeQuery(..));
	pointcut InvalidatePointcut(): execution(* sample.manager.IRepository.executeUpdate(..));

	Object around() : CachingPointcut() {
		String key = (String)thisJoinPoint.getArgs()[0];
		Object result = cache.get(key);
		if (result == null) {
			LogHelper.Instance.LogInfo("Result not yet cached");
			result = proceed();
			cache.put(key, result);
		}
		else {
			LogHelper.Instance.LogInfo("Returning cached result");
		}
		LogHelper.Instance.LogInfo(append("Cached result is: " , result.toString()));
		return result;
	}

	after() : InvalidatePointcut() {
		this.cache.clear();
		LogHelper.Instance.LogInfo("Cache was invalidated");
	}
}