package thinkinjava.annotation;


public @interface Unique {

	Constraints constrains() 
		default @Constraints(unique=true);
}
