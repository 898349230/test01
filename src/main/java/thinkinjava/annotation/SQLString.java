package thinkinjava.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value=ElementType.FIELD)
@Retention(value=RetentionPolicy.RUNTIME)
public @interface SQLString {

	int value() default 0;
	String name() default "";
	Constraints constrains() default @Constraints;
	
}
