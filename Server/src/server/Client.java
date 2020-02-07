package server;

import java.io.DataInputStream;
import java.io.PrintStream;

public class Client {
    String userName;
    String playingWith;
    int score;
    DataInputStream diss;
    PrintStream pss;
    
    Client(DataInputStream dis,PrintStream ps){
        diss = dis;
        pss = ps;      
       
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
    
}