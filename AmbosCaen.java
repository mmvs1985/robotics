
//importaciones
import lejos.nxt.*;
import lejos.robotics.subsumption.*;
import lejos.robotics.navigation.DifferentialPilot;
import memoryxy.NewMem;

public class AmbosCaen  implements Behavior {
   private boolean suppressed = false;
   private ColorSensor csizq;
   private ColorSensor csder;
   ColorSensor.Color colorizq ;
      ColorSensor.Color colorder;
     int intColorIzq;
     int intColorDer;
    Boolean enmeta;
   private DifferentialPilot pilot;
   public AmbosCaen(SensorPort colorIzq,SensorPort colorder){
    csizq= new ColorSensor(colorIzq);
    csder= new ColorSensor(colorder);
   }
   public boolean takeControl() {
      
       colorizq = csizq.getColor();
       colorder = csder.getColor();
     intColorIzq= colorizq.getColor();
      intColorDer=colorder.getColor();
     enmeta= NewMem.getInstance().getEnmeta();

      return (intColorIzq==7 && intColorDer==7 && !enmeta);
   }

   public void suppress() {
      suppressed = true;
   }

   public void action() {
    suppressed = false;
    LCD.drawString("AmbosCaen",4,1);
    pilot=new DifferentialPilot(3.0f, 20f, Motor.A, Motor.B);
    //pilot=new DifferentialPilot(1.2f, 7.6f, Motor.A, Motor.B);
    Boolean caida =colorizq.getRed()<25||colorizq.getBlue()<25||colorizq.getGreen()<25;
    if(caida){
      //si es caida gira 160 para evitar deathlocks
      LCD.drawString("Caida",4,4);
      pilot.travel(-5, false);
      pilot.setTravelSpeed(150);
      pilot.rotate(160);

    }
    else{
      //si no es caida gira 180 para volver por donde venia
      pilot.travel(-5, false);
      pilot.setTravelSpeed(150);
      pilot.rotate(180);
      
    }

    //rueda solo 3 rueda con oruga 3.6
    /*Motor.A.stop();
    Motor.B.stop();
    Motor.A.travel(-10);
    Motor.B.travel(-10);
    Motor.A.forward();
    Motor.B.forward();*/
    //while( !suppressed )
   //     Thread.yield();
    //Motor.A.stop(); // clean up khepe.1001
  //  Motor.B.stop();
   }
}