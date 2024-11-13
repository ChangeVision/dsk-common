package dsk.common.properties.interceptor;

import java.lang.annotation.Annotation;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import dsk.common.properties.PropertiesHelper;
import dsk.common.properties.annotation.PropertyKey;
import jakarta.inject.Inject;

public class AutoStoreInterceptor implements MethodInterceptor {
	private PropertiesHelper propertiesHelper;

	@Inject
	public void setPropertiesHelper(PropertiesHelper propertiesHelper) {
		this.propertiesHelper = propertiesHelper;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Annotation[][] parameterAnnotations = invocation.getMethod().getParameterAnnotations();
		boolean isSet = false;
		for (int paramIndex = 0; paramIndex < parameterAnnotations.length; ++paramIndex) {
			Object o = invocation.getArguments()[paramIndex];
			if (o == null) {
				continue;
			}
			for (Annotation a : parameterAnnotations[paramIndex]) {
				if (a.annotationType() == PropertyKey.class) {
					isSet = true;
					this.propertiesHelper.set(((PropertyKey) a).value(), o.toString());
				}
			}
		}
		if (isSet) {
			this.propertiesHelper.save();
		}
		return invocation.proceed();
	}
}
