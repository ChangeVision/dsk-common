package dsk.common.properties.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自動保存アノテーション<br>
 * AutoSaveをつけたsetterのパラメーターに設定すること
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface PropertyKey {
	/**
	 * @return key
	 */
	String value();
}
