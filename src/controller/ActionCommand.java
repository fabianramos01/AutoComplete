package controller;

public enum ActionCommand {

	COMMAND_LOAD_TREE("COMMAND_LOAD_TREE", "Cargar");
	
	private String command;
	private String title;
	
	private ActionCommand(String command, String title) {
		this.command = command;
		this.title = title;
	}
	
	public String getCommand() {
		return command;
	}
	
	public String getTitle() {
		return title;
	}
}