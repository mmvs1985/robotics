
//importaciones
import lejos.nxt.*;
import lejos.robotics.subsumption.*;
import lejos.robotics.navigation.DifferentialPilot;
import memoryxy.Memoryx;

public class CaeIzq  implements Behavior {
   private boolean suppressed = false;
   private ColorSensor csizq, csder;
   private DifferentialPilot pilot;
   private ColorSensor.Color colorizq, colorder;
   private int intColorIzq, intColorDer;
   

   public CaeIzq(SensorPort colorIzq,SensorPort colorder){
    csizq= new ColorSensor(colorIzq);
    csder= new ColorSensor(colorder);
   }
   public boolean takeControl() {
      
      ColorSensor.Color colorizq = csizq.getColor();
      ColorSensor.Color colorder = csder.getColor();
     int intColorIzq= colorizq.getColor();
     int intColorDer=colorder.getColor();
      return (intColorIzq==7 && intColorDer!=7);
   }

   public void suppress() {
      suppressed = true;
   }

   public void action() {
    suppressed = false;
    LCD.clear();
    LCD.drawString("CaeIzq",4,1);
    pilot=new DifferentialPilot(1.5f, 7.6f, Motor.A, Motor.B);
    pilot.setTravelSpeed(150);
    pilot.travel(-3, true);
    pilot.rotate(-10);
 
   }
}