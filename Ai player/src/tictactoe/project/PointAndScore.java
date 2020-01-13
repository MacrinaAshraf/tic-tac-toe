/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.project;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Ismail_khadr
 */
public class PointAndScore {
    
//   public int score;
//   private Point point;
//   public PointAndScore(int score ,Point point)
//   {
//   this.score=score;
//   this.point=point;
//   
//   
//   }
   
   public static final Random RANDOM = new Random();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i=0;i<3 ;i++)
        {
        System.out.println(RANDOM.nextInt(4));  
        
        }
    }
   
    
}
