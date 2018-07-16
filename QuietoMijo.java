import lejos.nxt.*;
import lejos.robotics.subsumption.*;
import lejos.nxt.Button;

public class QuietoMijo implements Behavior {
    //private TouchSensor touch;
  private boolean suppressed = false;
      
    

    public boolean takeControl() {
       return Button.ENTER.isDown();
    }

    public void suppress() {
       suppressed = true;
    }

    public void action() {
        suppressed = false;
       Motor.A.stop(); // clean up
       Motor.B.stop();
       System.exit(0); 

    }
}