package fr.topeka.sheepwar.arena;

public enum StateArena {

	WAITING("Waiting players"),
	STARTING("Starting..."),
	PLAYING("In Game"),
	FINISH("Game finished"),
	MAINTENANCE("Arena in maintenance");
	
	private String state;

	StateArena(String state){
		this.state = state;
	}
	
	@Override
	public String toString() {
		return state;
	}
	
}
