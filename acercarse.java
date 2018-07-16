
import lejos.nxt.*;
import lejos.robotics.subsumption.*;
import memoryxy.Memoryx;

public class acercarse implements Behavior {
    //private TouchSensor touch;
    private UltrasonicSensor sonar;
    private boolean suppressed = false;
    private boolean menor=false;
    int medida=255;
    private int tacho=0;
    private Memoryx mem= Memoryx.getInstance();
    private boolean cargando=false;
    public acercarse(SensorPort port )
    {
       sonar = new UltrasonicSensor( port );
    }

    public boolean takeControl() {
     medida= sonar.getDistance();
	 
     cargando=mem.getcargando();
     menor= (medida< 23);
       return (menor && !(cargando));
    }





    public void suppress() {
		LCD.clear();
       suppressed = true;
    }








    

    public void action() {
        suppressed = false;
        LCD.drawString("Acercandose:",1,6);
        LCD.drawInt(sonar.getDistance(),1,7);
          Motor.A.setSpeed(150);
          Motor.B.setSpeed(150);
          Motor.A.forward();
          Motor.B.forward();
         
       while( Motor.B.isMoving() && !suppressed )
                 Thread.yield();

               Motor.A.stop();
               Motor.B.stop();
    }

   
}