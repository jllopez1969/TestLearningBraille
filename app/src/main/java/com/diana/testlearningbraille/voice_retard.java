package com.diana.testlearningbraille;

public class voice_retard extends Thread{
    public boolean stop = false;

    public void run() {
        stop = false;
        this.wait(2);
        stop = true;
    }


    private void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
