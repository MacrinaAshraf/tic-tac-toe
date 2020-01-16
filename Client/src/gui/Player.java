package gui;

public class Player {
	private String name;
	private int score;
	private int id;
	private String playingWith;
	
	public Player() {
		name = "";
		score = 0;
		id = 0;
		playingWith = "";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlayingWith() {
		return playingWith;
	}
	public void setPlayingWith(String playingWith) {
		this.playingWith = playingWith;
	}

}
