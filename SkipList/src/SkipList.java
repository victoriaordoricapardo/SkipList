import java.lang.Math;

public class SkipList <T extends Comparable <T>>{
	
	NodoSkip<T> head, tail;
	int cont,numLista;
	double generador;
	
	//Constructor
	SkipList(){
		head=new NodoSkip<T>(null);
		tail=new NodoSkip<T>(null);
		cont=0;
		numLista=1;
		generador=0;
		  }
	
	
	
	/* Método privado para buscar elemento
	 * Para buscar un elemento inicial de la lista de la capa más alta,
	 * hasta alcanzar el máximo elemento que es menor o igual al buscado.
	 * Si NO lo encuentra regresa el valor más cercano
	 * @param T elem
	 * @return NodoSkip con el elemento
	 */
	private NodoSkip<T> busca(T elem){
		
		NodoSkip<T> actual=head; //apuntador inicial. 
		boolean exit=false;//aux para ciclo while
		
		while(!exit) { //Comienza el recorrido en la lista de hasta arriba
			while(actual.right.elem!=null && elem.compareTo(actual.right.elem)>=0) { //Compara que exista elem a la derecha y compara que derecha sea más grande o igual
				actual=actual.right;
			}
			if(actual.down!=null){//bajas
				actual = actual.down;
				} else 
					exit = true;
			}
		return actual;
		}
	/*Conexión de cabeza y cola
	 * @param NodoSkip head y NodoSkip tail
	 */
	public void connect(NodoSkip<T> head, NodoSkip<T> tail) {
		head.right=tail;
		tail.left=head;
	}
	
	
	/*Conexión horizontal de la lista
	 * @param NodoSkip left y NodoSkip right
	 */
	
	public void connectLeftRight(NodoSkip<T> nleft,NodoSkip<T> nright){
		nright.right=nleft;
		nleft.left=nright;
	}
	
	/*Conexión vertical de la lista
	 * @param NodoSkip left y NodoSkip right
	 */
	
	public void connectUpDown(NodoSkip<T> nup,NodoSkip<T> ndown){
		nup.up=ndown;
		ndown.down=nup;
	}
	
	private void insertH(NodoSkip<T> anterior, NodoSkip<T> nuevo) {
		connectLeftRight(anterior,nuevo);
		connectLeftRight(nuevo,anterior.right);
	}
	/*Inserta elemento en la lista
	 * @param T eleme
	 * @return NodoSkip
	 */
	public void insert(T elem){
		
		double random; //auxiliar para hacer el volado
		int i=1; //Contador de niveles
		cont++;
		NodoSkip<T> actual=busca(elem); //Primer paso de inserta
		
		//Segundo paso de ligar en la lista
		NodoSkip<T> newElem=new NodoSkip<T>(elem);
		insertH(actual,newElem);
		
		//Crear el volado
		random=Math.random();
		
		while(random>0.5 && (double)i<1.0+Math.log(cont)/Math.log(2)){
			if(numLista<random)
				expande();
			//Si tiene un elemento a la izquierda
			while(actual.getUp()==null && actual.getLeft()!=null)
				actual=actual.getLeft();
			actual=actual.getUp();
			NodoSkip<T> new2=new NodoSkip<T>(elem);
			connectLeftRight(actual,new2);
			connectUpDown(newElem,new2);
			newElem=new2;
			i++;	
			}
		}
	
	/*Expande la lista
	 */
	private void expande(){
        NodoSkip<T> headN=new NodoSkip<T>(null);
        NodoSkip<T> tailN=new NodoSkip<T>(null);

        connect(headN, tailN);
        connectUpDown(headN, head);
        connectLeftRight(tailN, tail);
        this.head=headN;
        this.tail=tailN;
        numLista++;
    }
	
	/*Método para borrar elemento
	 * @param T elem
	 */
	public void borrar(T elem) {
		
		NodoSkip<T> actual=busca(elem); //Primer paso buscar elemento
		
		//Caso base no encuentra elemento
		if(!actual.elem.equals(elem)) {
			return;
		}
		
		while(actual!=null) {
			connectLeftRight(actual.left,actual.right); //Conecta los dos extremos
			actual=actual.up;
		}
		
		//Juntar todas las conexiones
		if(numLista>1 && numLista>Math.log(cont)/Math.log(2)) {
			head=head.down;
			tail=tail.down;
			actual=head;
			while(actual!=null) {
				actual.up=null;
				actual=actual.right;
			}
			numLista--;
		}
		cont--;
	}
	
		
	
	

} //Clase
