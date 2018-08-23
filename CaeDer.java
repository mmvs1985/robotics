
//importaciones
import lejos.nxt.*;
import lejos.robotics.subsumption.*;
import lejos.robotics.navigation.DifferentialPilot;
import memoryxy.Memoryx;

public class CaeDer  implements Behavior {
   private boolean suppressed = false;
   private ColorSensor csizq;
   private ColorSensor csder;
   private DifferentialPilot pilot;
   private ColorSensor.Color colorizq;
   private ColorSensor.Color colorder;
   private int intColorIzq, intColorDer;
   public CaeDer(SensorPort colorIzq,SensorPort colorder){
    csizq= new ColorSensor(colorIzq);
    csder= new ColorSensor(colorder);
   }

   public boolean takeControl() {
      
     colorizq = csizq.getColor();
     colorder = csder.getColor();
     intColorIzq= colorizq.getColor();
     intColorDer=colorder.getColor();
      return (intColorIzq!=7 && intColorDer==7);
   }

   public void suppress() {
      suppressed = true;
   }

   public void action() {
    suppressed = false;
    LCD.clear();
    LCD.drawString("L: "+intColorIzq, 4,3);
    LCD.drawString("CaeDer",4,1);
    pilot=new DifferentialPilot(1.5f, 7.6f, Motor.A, Motor.B);
    pilot.setTravelSpeed(150);
    pilot.travel(-3, true);
    pilot.rotate(10);
 
   }
}