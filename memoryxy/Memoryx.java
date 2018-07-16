package memoryxy;
public class Memoryx {

        private  int colorActual;
        private  int colorAnteriorAnterior;
        private int colorAnterior;
        private boolean cargando;

        private String ultimaDesicion;
        private static Memoryx myMemory=null;
        //Recordar que color le corresponde
        private int derecha;
        private int izquierda;
        private int continuar;
        private String siguienteDesicion;
         
         private Memoryx(){
            derecha=7;
            izquierda=7;
            continuar=7;

            colorActual=7;

            colorAnteriorAnterior=7;
            cargando=false;
            
            ultimaDesicion="N";
         }

        public static Memoryx getInstance(){
            if(myMemory==null){
                myMemory= new Memoryx();
           
            }
            return myMemory;
        }

        public String evaluarDesicion(int colorAct){
            setcolorActual(colorAct);
            if(ultimaDesicion=="N" && colorAnteriorAnterior==7 ) return "D";
            if(derecha==colorActual) return "D";
            if(continuar==colorActual) return "C";
            if(izquierda==colorActual) return "I";
            if(colorActual==colorAnteriorAnterior && colorAnterior==6){
                ultimaDesicion=siguienteDesicion(ultimaDesicion);
                colorAnteriorAnterior=colorActual;
                return "D";
            } else{ 
                setDesicionCorrecta(colorAnteriorAnterior, ultimaDesicion);
                ultimaDesicion=siguienteDesicion(ultimaDesicion);
                return ultimaDesicion;
            }
        }

        public String siguienteDesicion(String d){
            if(derecha==7 && d!="D" && colorAnteriorAnterior==7){
                siguienteDesicion="D";
            }else{ 
                if (continuar==7 && d!="C"){
                    siguienteDesicion="C";
                }else{ 
                    if (izquierda==7 && d!="I"){
                        siguienteDesicion="I";
                    }
                }
            }
            return siguienteDesicion;
        }

        public void setDesicionCorrecta(int colorActual,String desicion ){
            if (desicion=="D"){
                setDerecha(colorActual);
            }
            if (desicion=="C"){
                setContinuar(colorActual);
            }
            if (desicion=="I"){
                setIzquierda(colorActual);
            }
        }
        public int getcolorActual(){
            return colorActual;
        }
        public int getcolorAnteriorAnterior(){
            return colorAnteriorAnterior;
        }
        public boolean getcargando(){
            return cargando;
        }
     
        public String getultimaDesicion(){
            return ultimaDesicion;
        }
         public int getDerecha(){
            return derecha;
        }
         public int getIzquierda(){
            return izquierda;
        }
         public int getContinuar(){
            return continuar;
        }
        
        
       
        // setters
        public void setcolorActual(int valor){
            colorActual=valor;
        }
        public void setcolorAnteriorAnterior(int valor){
            colorAnteriorAnterior=valor;
        }
        public void setCargando(boolean valor){
            this.cargando=valor;
        }

        public void setultimaDesicion(String valor){
            ultimaDesicion=valor;
        }
          public void setDerecha(int valor){
           derecha=valor;
        }
         public void setIzquierda(int valor){
            izquierda=valor;
        }
         public void setContinuar(int valor){
            continuar=valor;
        }
        
        

}
