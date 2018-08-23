package memoryxy;
import java.util.ArrayList;
import lejos.nxt.*;

public class NewMem {

	private Boolean cargado;
	private Boolean enmeta;
	private ArrayList <String> colores;
	private ArrayList <String> movimientos;
	private ArrayList <String> comportamientos;
	private static NewMem instance;

	private NewMem(){
		enmeta=false;
		cargado=false;
		colores= new ArrayList<String>();
		movimientos=new ArrayList<String>();
		comportamientos=new ArrayList<String>();
	}
	public static NewMem getInstance(){
		if(instance==null){
			instance= new NewMem();
		}
		return instance;
	}

	public Boolean getCargado(){
		return this.cargado;
	}

	public void setCargado(Boolean nuevo){
		this.cargado=nuevo;
	}

	//enmeta seria tras detectar la rampa
	public Boolean getEnmeta(){
		return this.enmeta;
	}
	public void setEnmeta(Boolean nuevo){
		this.enmeta=nuevo;
		
	}

	public void agregarColor(String nuevo){
		colores.add(nuevo);
	}

	public void agregarMovimiento(String nuevo){
		movimientos.add(nuevo);
	}

	public void agregarComportamiento(String nuevo){
		movimientos.add(nuevo);
	}
	public String getUltimoComportamiento(){
		return this.comportamientos.get(this.comportamientos.size());
	}
	public Boolean fueElUltimoComp(String comportamiento){
		if (this.comportamientos.isEmpty())
			return false;
		else return this.comportamientos.lastIndexOf(comportamiento)!=this.comportamientos.size();
	}

	public void controlEntrada(String color){
		if(this.colores.isEmpty()||this.colores.lastIndexOf(color)!=this.colores.size()){
			if(this.colores.isEmpty()||this.colores.lastIndexOf("blanco")==this.colores.size()){
				this.colores.add(color);
				LCD.drawString("ULt FUE BLANCO ",4,3);
			}
			else {
				if(!enmeta){
					LCD.drawString("ULT. NO BLANCO ",4,3);
					this.enmeta=true;
					this.colores.add(color);
				}
				else{
					LCD.drawString("SALGO",4,3);
					this.enmeta=false;
					this.colores.add(color);
				}
			}
		}
	}

}