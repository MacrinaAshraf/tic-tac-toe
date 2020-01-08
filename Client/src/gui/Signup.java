package gui;

import java.io.IOException;

public class Signup {
    
static GUIframe x ;
public Signup() throws IOException
{
   x= new GUIframe();
    x.setVisible(true);
}
    public static void main(String[] args) throws IOException {
        Signup gui = new Signup();
    }
}
