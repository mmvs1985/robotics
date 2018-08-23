
//importaciones
import lejos.nxt.*;
import lejos.robotics.subsumption.*;
import lejos.robotics.navigation.DifferentialPilot;
import memoryxy.NewMem;

public class EnCiudad  implements Behavior {
  private boolean suppressed = false;
  private ColorSensor csizq;
  private ColorSensor csder;
  private ColorSensor.Color colorizq ;
  private ColorSensor.Color colorder;
  private int intColorIzq;
  private int intColorDer;
  private Boolean enmeta,cargado;
  private DifferentialPilot pilot;
  //constructor
  public EnCiudad(SensorPort colorIzq,SensorPort colorder){
    csizq= new ColorSensor(colorIzq);
    csder= new ColorSensor(colorder);
  }
  public boolean takeControl() {
      
    colorizq = csizq.getColor();
    colorder = csder.getColor();
    intColorIzq = colorizq.getColor();
    intColorDer = colorder.getColor();
    enmeta = NewMem.getInstance().getEnmeta();
    cargado = NewMem.getInstance().getCargado();
    return (cargado && enmeta);
  }

  public void suppress() {
    suppressed = true;
  }

  public void action() {
    suppressed = false;
    LCD.clear();
    LCD.drawString("EnCiudad",4,1);
    pilot.setTravelSpeed(10);
    pilot.travel(70, false);
    //pilot=new DifferentialPilot(1.2f, 7.6f, Motor.A, Motor.B);
    /*Boolean caida =colorizq.getRed()<25||colorizq.getBlue()<25||colorizq.getGreen()<25;
    
    if(caida){LCD.drawString("Caida",4,4);
    //rueda solo 3 rueda con oruga 3.6
    pilot.travel(-5, false);
    pilot.setTravelSpeed(150);
    pilot.rotate(180);
    }*/
  }
}