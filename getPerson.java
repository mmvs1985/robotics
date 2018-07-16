
import lejos.nxt.*;
import lejos.robotics.subsumption.*;
import memoryxy.Memoryx;

public class getPerson implements Behavior {
    //private TouchSensor touch;
    private UltrasonicSensor sonar;
    private boolean suppressed = false;
    private boolean menor=false;
    int medida=0;
    private int tacho=0;
    private Memoryx mem= Memoryx.getInstance();
    private boolean cargando;
    public getPerson(SensorPort port )
    {
       sonar = new UltrasonicSensor( port );
    }

    public boolean takeControl() {
     medida= sonar.getDistance();
	 //mem= Memoryx.getInstance();
     cargando=mem.getcargando();
     menor=(medida< 7) /*&& (sonar.getDistance() > 8)*/;
       return (menor && !(cargando));
    }

    public void suppress() {
		LCD.clear();
       suppressed = true;
    }

    public void action() {
         suppressed = false;

          //LCD.clear();
		LCD.drawString("get person",1,1);
        //LCD.drawString("get",4,4);
		LCD.drawInt(medida, 1, 2);
    //tacho = Motor.C.getTachoCount();
      //LCD.drawString("Gancho:",3,1);
        //LCD.drawInt(tacho,4,1);
if(menor && !(cargando)){
  Motor.C.setSpeed(40);Motor.C.rotate(80, true);
}
        //LCD.drawString("Gancho:",3,1);
        //LCD.drawInt(tacho,4,1);

//tacho 90 cerrado sin persona
//tacho 70 cerrado con persona

		mem.setCargando(true);
tacho = Motor.C.getTachoCount();
  LCD.drawString("Gancho:",1,3);
        LCD.drawInt(tacho,1,4);

       while( Motor.B.isMoving() && !suppressed )
         Thread.yield();

       Motor.A.stop();
       Motor.B.stop();
    }

   
}