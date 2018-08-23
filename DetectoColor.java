
//importaciones
import lejos.nxt.*;
import lejos.robotics.subsumption.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import memoryxy.NewMem;

public class DetectoColor  implements Behavior {
   private boolean suppressed = false;
   private ColorSensor csizq;
   private ColorSensor csder;
   private DifferentialPilot pilot;
   
   private ColorSensor.Color colorizq, colorder;
   private int intColorIzq,intColorDer;
   private String colores[] = {"rojo","verde","azul","amarillo","magenta",
   "naranja","blanco","negro","rosa","gris","gris oscuro","cyan"};

   public DetectoColor(SensorPort colorIzq,SensorPort colorder){
    csizq= new ColorSensor(colorIzq);
    csder= new ColorSensor(colorder);
   }
   public boolean takeControl() {
      
       colorizq = csizq.getColor();
       colorder = csder.getColor();
      intColorIzq= colorizq.getColor();
      intColorDer=colorder.getColor();
      //detecto<6 no detecta negro ni blanco
      return (intColorIzq<6 && intColorDer<6);
   }

   public void suppress() {
      suppressed = true;
   }

   public void action() {
    NewMem memo= NewMem.getInstance();
    memo.controlEntrada(colores[intColorIzq]);
    memo.agregarComportamiento("DetectoColor");
    suppressed = false;
    //LCD.clean();
    int colorViejo=colorizq.getColor();
    LCD.drawString("color: "+colores[colorViejo],4,5);
    //pilot=new DifferentialPilot(1.2f, 7.6f, Motor.A, Motor.B);

    pilot=new DifferentialPilot(3.0f, 20f, Motor.A, Motor.B);

    //rueda solo 3 rueda con oruga 3.6
    pilot.travel(4,false);
    Delay.msDelay(1000);
    pilot.stop();
    LCD.clear();
    int colornuevo=colorizq.getColor();
    LCD.drawString(colores[colornuevo],4,5);
    if(colorViejo==colornuevo){
      pilot.travel(11, false);
      //diametro de rueda por segundo
      pilot.setTravelSpeed(10);
      pilot.rotate(90);
      pilot.travel(15, false);
    }
    else{
      if (memo.getCargado()){
      memo.setEnmeta(true);
      }
      
      else
        pilot.rotate(180);
    }
    /*Motor.A.stop();
    Motor.B.stop();
    Motor.A.travel(-10);
    Motor.B.travel(-10);
    Motor.A.forward();
    Motor.B.forward();*/
    //while( !suppressed )
   //     Thread.yield();
    //Motor.A.stop(); // clean up
  //  Motor.B.stop();
   }
}