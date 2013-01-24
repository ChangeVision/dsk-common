package dsk.common.logging;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dsk.common.exception.DskWarningException;

public class LogInterceptor implements MethodInterceptor {
	private static final Logger LOG = LoggerFactory.getLogger(LogInterceptor.class);

	private Set<String> litePublishMethods = new HashSet<String>();

	private Set<String> ignoreMethods = new HashSet<String>();

	public LogInterceptor() {
		super();
		this.ignoreMethods.add("finalize");
		this.ignoreMethods.add("toString");
	}

	public void addLitePublishMethods(String method1, String... methods) {
		this.litePublishMethods.add(method1);
		if (null != methods) {
			for (String method : methods) {
				this.litePublishMethods.add(method);
			}
		}
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		final String mName = invocation.getMethod().getName();
		if (this.ignoreMethods.contains(mName)) {
			return invocation.proceed();
		}
		final String cName = invocation.getThis().getClass().getName();
		try {
			LOG.info("start {} : {}", cName, mName);
			if (!this.litePublishMethods.contains(mName) && (LOG.isDebugEnabled() || LOG.isTraceEnabled())) {
				Object[] objs = invocation.getArguments();
				Class<?>[] classes = invocation.getMethod().getParameterTypes();
				String str;
				for (int i = 0; i < classes.length; ++i) {
					Object o = objs[i];
					Class<?> clazz = classes[i];
					if (null == o) {
						str = "null";
					} else {
						if (clazz.isArray()) {
							int length = Array.getLength(o);
							if (0 < length) {
								StringBuilder sb = new StringBuilder();
								for (int j = 0; j < length; ++j) {
									sb.append(", ");
									sb.append(Array.get(o, j));
								}
								str = sb.substring(2);
							} else {
								str = "";
							}
						} else {
							str = o.toString();
						}
					}
					LOG.debug("\t\t{} : {}", clazz.toString(), str);
				}
			}
			Object result = invocation.proceed();
			LOG.info("end   {} : {}", cName, mName);
			if (!this.litePublishMethods.contains(mName) && (LOG.isDebugEnabled() || LOG.isTraceEnabled())) {
				Class<?> clazz = invocation.getMethod().getReturnType();
				if (!(Void.TYPE == clazz)) {
					String str;
					if (clazz.isArray()) {
						int length = Array.getLength(result);
						if (0 < length) {
							StringBuilder sb = new StringBuilder();
							for (int i = 0; i < length; ++i) {
								sb.append(", ");
								sb.append(Array.get(result, i));
							}
							str = sb.substring(1);
						} else {
							str = "";
						}
					} else {
						if (null == result) {
							str = "null";
						} else {
							str = result.toString();
						}
					}
					LOG.debug("\t\t{} : {}", clazz.toString(), str);
				}
			}
			return result;
		} catch (Exception e) {
			if (e instanceof DskWarningException) {
				LOG.warn(e.getLocalizedMessage());
			} else {
				LOG.error(e.getLocalizedMessage(), e);
			}
			throw e;
		}
	}
}
