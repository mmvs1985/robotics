
//importaciones
import lejos.nxt.*;
import lejos.robotics.subsumption.*;
import lejos.robotics.navigation.DifferentialPilot;
import memoryxy.NewMem;

public class AmbosCaenEnCiudad  implements Behavior {
  private boolean suppressed = false;
  private ColorSensor csizq;
  private ColorSensor csder;
  private ColorSensor.Color colorizq ;
  private ColorSensor.Color colorder;
  private int intColorIzq;
  private int intColorDer;
  private Boolean enmeta;
  private DifferentialPilot pilot;
  public AmbosCaenEnCiudad(SensorPort colorIzq,SensorPort colorder){
  csizq= new ColorSensor(colorIzq);
  csder= new ColorSensor(colorder);
  }
  public boolean takeControl() {
      
    colorizq = csizq.getColor();
    colorder = csder.getColor();
    intColorIzq= colorizq.getColor();
    intColorDer=colorder.getColor();
    enmeta= NewMem.getInstance().getEnmeta();

    return (intColorIzq==7 && intColorDer==7 && enmeta);
  }

  public void suppress() {
    suppressed = true;
  }

  public void action() {
    suppressed = false;
    LCD.drawString("AmbosCaenEnCiudad",4,1);
    //pilot=new DifferentialPilot(1.2f, 7.6f, Motor.A, Motor.B);
    Boolean caida =colorizq.getRed()<25||colorizq.getBlue()<25||colorizq.getGreen()<25;
    pilot=new DifferentialPilot(3.0f, 20f, Motor.A, Motor.B);
    if(caida){
      LCD.drawString("Caida",4,4);
      
      //rueda solo 3 rueda con oruga 3.6
      pilot.travel(-5, false);
      pilot.setTravelSpeed(10);
      pilot.rotate(180);
    }
    else{
      //entrar en el circulo negro
      pilot.travel(20);
      pilot.stop();
      //soltar el cargado
      NewMem.getInstance().setCargado(false);
      pilot.rotate(180);
      pilot.travel(70);
    }
  }
}