package fr.topeka.sheepwar.commands;

import java.util.List;

public interface CommandCompleter {

	
	public List<String> tabCompleterHandle();
	
}
