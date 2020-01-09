package model;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Client extends Thread{
    private String userName;
    private int id;
    private String playingWith;
    private int score;
    private Boolean isPlaying;    
    private String status;
    private DataInputStream diss;
    private PrintStream pss;
    private Socket server;
    
    public Client() {
    	try {
			server = new Socket("127.0.0.1", 5008);
			isPlaying = false;  
	        status = "offline";
	        score = 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public Client(DataInputStream dis, PrintStream ps) {
        try {
			server = new Socket("127.0.0.1", 5008);
			diss = dis;
	        pss = ps; 
	        isPlaying = false;  
	        status = "offline";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DataInputStream getDiss() {
		return diss;
	}

	public void setDiss(DataInputStream diss) {
		this.diss = diss;
	}

	public PrintStream getPss() {
		return pss;
	}

	public void setPss(PrintStream pss) {
		this.pss = pss;
	}

	public Boolean getIsPlaying() {
        return isPlaying;
    }

    public void setIsPlaying(Boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getPlayingWith() {
        return playingWith;
    }
    
    public void setPlayingWith(String playingWith) {
        this.playingWith = playingWith;
    }
    
    public DataInputStream getDataInputStream() {
        return diss;
    }
    
    public void setDataInputStream(DataInputStream diss) {
        this.diss = diss;
    }
    
    public PrintStream getPrintStream() {
        return pss;
    }
    
    public void setPrintStream(PrintStream pss) {
        this.pss = pss;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    public void run() {
    	
    }
}