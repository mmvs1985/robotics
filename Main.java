import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;


public class Main {
   public static void main(String [] args) {
   //importancia de menor a mayor
      Behavior b1 = new Navegacion();
      Behavior b2 = new searchPerson(SensorPort.S1);

      Behavior b3 = new acercarse(SensorPort.S4);

      Behavior b4 = new getPerson(SensorPort.S4);
      Behavior b5 = new interseccion(SensorPort.S2);
      Behavior b6 = new DireccionAcomodar(SensorPort.S2,SensorPort.S3);
    
      Behavior b7 = new QuietoMijo();
      Behavior [] bArray = {b1,b2,b3,b4,b5,b6,b7};
      Arbitrator arby = new Arbitrator(bArray);
      arby.start();

   }
}