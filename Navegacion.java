import lejos.nxt.*;
import lejos.robotics.subsumption.*;
import memoryxy.NewMem;

public class Navegacion  implements Behavior {
   private boolean suppressed = false;
   
   public boolean takeControl() {
      return true;
   }

   public void suppress() {
      suppressed = true;
	  LCD.clear();
   }

   public void action() {
	 LCD.drawString("Navegacion",0,5);

    NewMem memo= NewMem.getInstance();
    memo.agregarComportamiento("Navegacion");

     suppressed = false;
     Motor.A.setSpeed(220);
     Motor.B.setSpeed(220);
     Motor.A.forward();
     Motor.B.forward();
     while( !suppressed )
        Thread.yield();
     
   }
}