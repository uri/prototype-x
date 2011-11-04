package com.murderbody.prototype;

public class Hippity {

    public static void main (String[] args) {
        
        int temp = 16;
        
        for (int x = 0; x < temp; x++) {
            
            if (x % 3 == 0 && x % 5 == 0){
                System.out.println("Hop");
            } else if (x % 3 == 0) {
                System.out.println("Hippity");
            } else if (x % 5 ==0) {
                System.out.println("Hopity");
            }
            
        }
    }
}
