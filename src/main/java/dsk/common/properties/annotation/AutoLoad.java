package dsk.common.properties.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自動取得アノテーション<br>
 * getterに設定すること<br>
 * 任意の*.xmlファイルから指定したキーの値を取得する。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AutoLoad {
	/**
	 * @return key
	 */
	String value();
}
