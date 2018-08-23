import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;


public class Main {
   public static void main(String [] args) {
   //importancia de menor a mayor
      /*Behavior b1 = new Navegacion();
      Behavior b2 = new searchPerson(SensorPort.S1);
//perros
      Behavior b3 = new acercarse(SensorPort.S4);

      Behavior b4 = new getPerson(SensorPort.S4);
      Behavior b5 = new interseccion(SensorPort.S2);
      Behavior b6 = new DireccionAcomodar(SensorPort.S2,SensorPort.S3);
   3der
   2izq

     */
//    Behavior b2 = new AmbosCaenEnCiudad(SensorPort.S2,SensorPort.S3);
      Behavior b11 = new Navegacion();
      Behavior b10 = new searchPerson(SensorPort.S1);
      Behavior b9 = new acercarse(SensorPort.S4);
      Behavior b8 = new getPerson(SensorPort.S4);
      Behavior b7 = new DetectoColor(SensorPort.S2,SensorPort.S3);
      Behavior b6 = new EnCiudad(SensorPort.S2,SensorPort.S3);
      Behavior b5 = new CaeDer(SensorPort.S2,SensorPort.S3);
      Behavior b4 = new CaeIzq(SensorPort.S2,SensorPort.S3);
      Behavior b3 = new AmbosCaen(SensorPort.S2,SensorPort.S3);
      Behavior b2 = new AmbosCaenEnCiudad(SensorPort.S2,SensorPort.S3);
      Behavior b1 = new QuietoMijo();
      
      //Behavior [] bArray = {b1,b2,b3,b4,b5,b6};
      //Behavior [] bArray = {b4,b3,b2,b1};
     Behavior [] bArray = {b11,b10,b9,b8,b7,b6,b5,b4,b3,b2,b1};
      Arbitrator arby = new Arbitrator(bArray);
      arby.start();

   }
}