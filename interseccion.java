
                import lejos.nxt.*;
import lejos.robotics.subsumption.*;
import memoryxy.Memoryx;


public class interseccion implements Behavior {
    //private TouchSensor touch;
  private  boolean suppressed = false;
  private  boolean seguir = true;
  private  ColorSensor c;
  private  ColorSensor.Color color;
  private  int intcolor=0;
  private String desicion;
  private Memoryx mem=Memoryx.getInstance();
  private int coloractual;

 // private Memoryx myMemory;
    
    public interseccion(SensorPort port )
    {
      c = new ColorSensor(port);
    }

    public boolean takeControl() {
      color = c.getColor();
      intcolor= color.getColor();
       return (intcolor==0 || intcolor==1 || intcolor==2 || /*intcolor==6 ||*/ intcolor==7);
    }

    public void suppress() {
  LCD.clear();
       suppressed = true;
    }

    public void action() {
    LCD.drawString("interseccion",0,5);      //myMemory = Memory.getMemory();

    desicion=mem.evaluarDesicion(intcolor);
    LCD.clear();
    LCD.drawInt(intcolor,0,5); 
    LCD.drawString(desicion,0,3);
     coloractual=mem.getcolorActual();
    if(desicion.equals("D")){

 
       while(intcolor==coloractual){
                Motor.A.setSpeed(320);
                 Motor.B.setSpeed(120);
                 Motor.A.rotate(400, true);
                 Motor.B.rotate(400, true);
                intcolor= color.getColor();
               coloractual=mem.getcolorActual();
           
                }
           
    }
    if(desicion.equals("I")){
        while(intcolor==coloractual){
                Motor.A.setSpeed(120);
                 Motor.B.setSpeed(320);
                 Motor.A.rotate(400, true);
                 Motor.B.rotate(400, true);
                intcolor= color.getColor();
                coloractual=mem.getcolorActual();
           
               }

    }
    Motor.A.setSpeed(220);
                 Motor.B.setSpeed(220);


      /*switch (intcolor) {
            case 0:  Motor.A.rotate(-270, true);
                     Motor.B.rotate(270, true);
              break;
            case 1:
            while(intcolor==1){
                Motor.A.setSpeed(320);
                 Motor.B.setSpeed(120);
                 Motor.A.rotate(400, true);
                 Motor.B.rotate(400, true);
                intcolor= color.getColor();
           
                }
       
              break;
            case 2:
              break;
              }*/
       suppressed = false;
       

       while( Motor.B.isMoving() && !suppressed )
         Thread.yield();

       Motor.A.stop();
       Motor.B.stop();
    }
}
