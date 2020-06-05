package fr.topeka.sheepwar.commands;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(CLASS)
@Target(METHOD)
public @interface Permission {
	String _permission();
}
