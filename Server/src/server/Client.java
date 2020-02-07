package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.PrintStream;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class Client implements Observable{
    String userName;
    String playingWith;
    int score;
    Boolean isPlaying;    
    String status;
    BufferedReader diss;
    PrintStream pss;
    Client(){
        isPlaying=false;  
        status="offline";
        score=0;
    }
    Client(BufferedReader dis,PrintStream ps){
        diss = dis;
        pss = ps; 
        isPlaying=false;  
        status="offline";       
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
    public BufferedReader getDataInputStream() {
        return diss;
    }
    public void setDataInputStream(BufferedReader diss) {
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

    @Override
    public void addListener(InvalidationListener listener) {
        System.out.print("changed");//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}