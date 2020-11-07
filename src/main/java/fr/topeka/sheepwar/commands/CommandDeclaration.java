package fr.topeka.sheepwar.commands;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface CommandDeclaration {

	String command();
	
	String[] aliases() default {};
	
	String permission() default "";
	
	String usage() default "";
	
	String[] description() default { "" };
	
}
