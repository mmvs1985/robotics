import lejos.nxt.*;
import lejos.robotics.subsumption.*;
import lejos.nxt.addon.OpticalDistanceSensor;
import memoryxy.Memoryx;

public class searchPerson implements Behavior {
    //private TouchSensor touch;
    //private UltrasonicSensor sonar;
    private UltrasonicSensor sonar2; 
    private OpticalDistanceSensor sonar;
    private boolean suppressed = false;
    private int distance=0;
    private Memoryx mem;
    private boolean cargando;
    public searchPerson(SensorPort port )
    {
      sonar2 = new UltrasonicSensor( SensorPort.S4 );
       sonar = new OpticalDistanceSensor( port );
    }

    public boolean takeControl() {
      mem=Memoryx.getInstance();
      cargando=mem.getcargando();
      distance=sonar.getDistance() ;
       return ((distance< 180) && !(cargando)); //Verdadero=Encontro una persona

    }

    public void suppress() {
		LCD.clear();
       suppressed = true;
    }

    public void action() {
		LCD.drawString("searchPerson",0,5);
    LCD.drawInt(sonar2.getDistance(),0,6);
       suppressed = false;
       Motor.A.setSpeed(100);
       Motor.B.setSpeed(100);
      Motor.A.forward();
       Motor.B.backward();
      
       while( Motor.B.isMoving() && !suppressed )
         Thread.yield();

       Motor.A.stop();
       Motor.B.stop();
    }






    public void rotation(){
       Motor.A.forward();
       Motor.B.backward();
       //harcode
       //Motor.A.rotate(550, true);
       //Motor.B.rotate(-550, true);
    
       return;
    }
}