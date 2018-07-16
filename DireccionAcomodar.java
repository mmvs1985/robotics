import lejos.nxt.*;
import lejos.robotics.subsumption.*;

public class DireccionAcomodar implements Behavior {
    //private TouchSensor touch;
  private boolean suppressed = false;
    boolean seguir = true;
     ColorSensor c;
    ColorSensor.Color color;
    int intcolor=0;
    //Light
    // private boolean suppressed = false;
    //boolean seguir = true;
    LightSensor light;
    int fall=0;
     private int tacho=0;
    int giro=0;
    
    
    public DireccionAcomodar(SensorPort port1,SensorPort port2 )
    {
      c = new ColorSensor(port1);
       light = new LightSensor(port2);
    }

    public boolean takeControl() {
      color = c.getColor();
      intcolor= color.getColor();
      fall=light.getLightValue();
       //LCD.drawInt(fall, 7, 1);
       giro=0;
       return fall<30 || intcolor==7;
    }

    public void suppress() {
       suppressed = true;
    }

    public void action() {


      
    Motor.A.setSpeed(150);
    Motor.B.setSpeed(150);
	LCD.drawString("direc. Acom",0,5);
    

    if( (fall<30) && (intcolor==7)){
       Motor.A.rotate(-620, true);
       Motor.B.rotate(620, true);
        }


    if( (fall<30) && !(intcolor==7)){
      suppressed = false;
      // Motor.A.rotate(-50, true);
      // Motor.B.rotate(-50, true);
       //LCD.drawInt(fall, 0, 1);
       try{Thread.sleep(100);}catch(Exception e){}
      Motor.A.rotate(-100, true);
       Motor.B.rotate(100, true);
        //LCD.drawInt(fall, 0, 2);
        try{Thread.sleep(100);}catch(Exception e){}
        
        }

    if( !(fall<30) && (intcolor==7) ){
      suppressed = false;
       //Motor.A.rotate(-50, true);
       //Motor.B.rotate(-50, true);
       //LCD.drawInt(fall, 0, 1);

        tacho = Motor.C.getTachoCount();
        LCD.drawString("Gancho:",1,6);
        LCD.drawInt(tacho,1,7);


       try{Thread.sleep(100);}catch(Exception e){}
       Motor.A.rotate(100, true);
       Motor.B.rotate(-100, true);
       //LCD.drawInt(fall, 0, 2);
       try{Thread.sleep(100);}catch(Exception e){}
        }

      Motor.A.setSpeed(200);
    Motor.B.setSpeed(200);

       while( Motor.B.isMoving() && !suppressed )
         Thread.yield();
//&& intcolor!=1 && intcolor!=2 && intcolor!=6  && intcolor!=0
       Motor.A.stop();
       Motor.B.stop();
    }
}