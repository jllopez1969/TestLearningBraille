package com.diana.testlearningbraille;

public class voice extends Thread {

    public boolean stop = false;

    public void run() {
        stop = false;
        this.wait(4);
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
