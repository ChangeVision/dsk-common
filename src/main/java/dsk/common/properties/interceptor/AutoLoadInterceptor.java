package dsk.common.properties.interceptor;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import dsk.common.exception.DskRuntimeException;
import dsk.common.properties.PropertiesHelper;
import dsk.common.properties.annotation.AutoLoad;

public class AutoLoadInterceptor implements MethodInterceptor {
	private final List<String> cache = new ArrayList<>();
	private PropertiesHelper propertiesHelper;

	@Inject
	public void setPropertiesHelper(PropertiesHelper propertiesHelper) {
		this.propertiesHelper = propertiesHelper;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		String methodName = invocation.getMethod().getName();
		String id = String.format("%s#%s", invocation.getThis().getClass().getName(), methodName);
		if (this.cache.contains(id)) {
			return invocation.proceed();
		}
		this.cache.add(id);

		Annotation[] annotations = invocation.getMethod().getAnnotations();
		AutoLoad a = null;
		for (Annotation annotation : annotations) {
			if (annotation.annotationType() == AutoLoad.class) {
				a = (AutoLoad) annotation;
				break;
			}
		}
		if (a == null) {
			return invocation.proceed();
		}
		String fieldName = methodName.substring("get".length());
		fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1, fieldName.length());
		PropertyDescriptor nameProp = new PropertyDescriptor(fieldName, invocation.getThis().getClass());
		Method setter = nameProp.getWriteMethod();

		String key = a.value();
		Class<?> returnType = invocation.getMethod().getReturnType();
		if (returnType.isPrimitive()) {
			if (returnType == boolean.class) {
				boolean rs = Boolean.parseBoolean(this.propertiesHelper.get(key, "0"));
				setter.invoke(invocation.getThis(), rs);
				return rs;
			} else if (returnType == short.class) {
				short rs = Short.parseShort(this.propertiesHelper.get(key, "0"));
				setter.invoke(invocation.getThis(), rs);
				return rs;
			} else if (returnType == int.class) {
				int rs = Integer.parseInt(this.propertiesHelper.get(key, "0"));
				setter.invoke(invocation.getThis(), rs);
				return rs;
			} else if (returnType == long.class) {
				long rs = Long.parseLong(this.propertiesHelper.get(key, "0"));
				setter.invoke(invocation.getThis(), rs);
				return rs;
			} else if (returnType == float.class) {
				float rs = Float.parseFloat(this.propertiesHelper.get(key, "0"));
				setter.invoke(invocation.getThis(), rs);
				return rs;
			} else if (returnType == double.class) {
				double rs = Double.parseDouble(this.propertiesHelper.get(key, "0"));
				setter.invoke(invocation.getThis(), rs);
				return rs;
			} else {
				throw new DskRuntimeException("type is unkwoun.");
			}
		}
		Object rs = returnType.cast(this.propertiesHelper.get(key));
		setter.invoke(invocation.getThis(), rs);
		return rs;
	}
}
