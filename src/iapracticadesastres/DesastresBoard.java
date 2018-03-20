/*!
 * REpresentacion del estado del problema del viajante de comercio
 */
package iapracticadesastres;

import java.util.Random;
import IA.Desastres.Grupos;
import IA.Desastres.Centros;

public class DesastresBoard {

  /// String que describe el operador
  public static String INTERCAMBIO = "Intercambio";
    
  /// Nuemro de ciudades
  //private int ncities;
  /// Orden entre las ciudades
  //private int [] path; 
  /// Distancias entre las ciudades
  //private int [][] dist; 
  
  private static Grupos gs;
  private static Centros cs;

  /*!\brief Genera una instancia del problema Desastres
   *
   * Crea una nueva instancia del problema Desastres con los parametros especificados
   * 
   * @param [in] nc Numero de ciudades
   */
  public DesastresBoard(int nGrupos, int nCentros, int nHelis, int Seed) {
      gs = new Grupos(nGrupos, Seed);
      cs = new Centros(nCentros, nHelis, Seed);
    //Eo initialisation!
    //Maybe add a distance static to ease calculus!
    
    /*Random myRandom=new Random();
    int d;
    
    path=new int[nc];
    dist= new int[nc][nc];
    ncities=nc;
    
    for (int i = 0; i < nc; i++) path[i]=i;
    
    for (int i = 0; i < nc; i++)
      for (int j = i; j < nc; j++)
        if (i==j) dist[i][j]=0;
        else {
          d=  myRandom.nextInt(50)+10;
          dist[i][j]=d;
          dist[j][i]=d;
        }
 */
  }
  
    public DesastresBoard(int nc, int seed) {
    Random myRandom=new Random();
    int d;
    
    myRandom.setSeed(seed);
    path=new int[nc];
    dist= new int[nc][nc];
    ncities=nc;
    
    for (int i = 0; i < nc; i++) path[i]=i;
    
    for (int i = 0; i < nc; i++)
      for (int j = i; j < nc; j++)
        if (i==j) dist[i][j]=0;
        else {
          d=  myRandom.nextInt(50)+10;
          dist[i][j]=d;
          dist[j][i]=d;
        }
 
  }

  /*!\brief Genera una instancia del TSP con un camino inicial y una matriz de distancias
   *
   * Genera una instancia del problema del viajante de comercio recibiendo el numero de ciudades
   * el camino inicial y la matriz de distancias
   *
   * @param[in] nc Numero de ciudades
   * @param[in] p Camino inicial
   * @param[in] d matriz de distancias
   *
   */
  public DesastresBoard(int nc, int [] p, int [][] d) {

    path=new int[nc];
    dist= new int[nc][nc];
    
    ncities=nc;
    for (int i = 0; i < nc; i++) {
      path[i]=p[i];
    }

    for (int i = 0; i < nc; i++)
      for (int j = 0; j < nc; j++)
        dist[i][j]=d[i][j];
  }

  /*!\brief Retorna el numero de ciudades de la instancia
   *
   */
  public int getNCities(){return(ncities);}
  
  /*!\brief Retorna el camino entre las ciudades
   *
   */
  public int [] getPath(){return(path);}
  
  /*!\brief Retorna la matriz de distancias
   *
   */
  public int [][] getDists(){return(dist);}
   
  /*!\brief Retorna la distancia entre la ciudad i y la siguiente ciudad en el camino
   *
   */
  public int distCities(int i){
    if (i<ncities-1) return(dist[path[i]][path[i+1]]);
    else return(dist[path[i]][path[0]]);
  }

  /*!\Brief Intercambia dos ciudades en el recorrido
   *
   * \pre los valores han de ser validos
   */
  public void swapCities(int i, int j){
    int tmp;

    tmp=path[i];
    path[i]=path[j];
    path[j]=tmp;
  }

  /*!\brief Retorna un string indicando la diferencia entre los recorridos
   *
   */
  public String getDiff(DesastresBoard t){
   int [] b;
   String s="Intercambio ciudad ";
   boolean primera=true;

   b=t.getPath();

   for (int i=0;i<ncities;i++){
     if(b[i]!=path[i]){
       if (primera) {
         primera=false;
         s=s+path[i]+" con ";
       } else s=s+path[i];
     }
   }

   return(s);
  }
 
   /*!\brief Retorna el coste del recorrido
    *
    */
  public int pathCost(){
   int sum=0;
   for(int i=0;i<ncities;i++) sum=sum+distCities(i);
   return sum;
  }
  
  
  /*!\brief Retorna el recorrido como un string
   *
   */
  public String toString() {
    String retVal = "|";
    for (int i = 0; i < ncities; i++) {
      retVal = retVal + path[i] + "|";
    }
    return retVal;
  }
}