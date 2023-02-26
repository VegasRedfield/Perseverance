import java.io.*; //image
import java.awt.image.*; //BufferedImage

/**
 * \class Rover : classe créant un rover
 */
public class Rover{
	
	//<!image du rover
	BufferedImage image;
	
	//<!dimensions du rover
	int hauteur, largeur;
	
	//<!point 2D désignant l'origine du rover
	APoint origine;
	
	//<!texte lié au rover
	String description;
	
	/**
   * \fn Rover(BufferedImage i, String s) : constructeur Rover
   * 
   * @param BufferedImage i : image du rover
   * @param String s : texte de description du rover
   */
	public Rover(BufferedImage i, String s) throws IOException{
		description =s;
		image = i;
		hauteur = 100;
		largeur = 100;
		origine = new APoint(0,833-100);
	}
	
	/**
   * \fn void ChangePosition(int vent, int vitesseI, int angleI, double t, double g) : méthode gérant l'actualisation de l'origine du rover pour le déplacement de celui-ci
   * 
   * @param int vent : vent du niveau en cours
   * @param int vitesseI : vitesse initiale du rover
   * @param int angleI : angle initial du rover
   * @param double t : temps depuis le lancement du rover
   * @param double g : gravité du niveau en cours
   */ 
	public void ChangePosition(int vent, int vitesseI, int angleI, double t, double g){//méthode effectuant le changement de la position du rover en fonction du temps et des paramètres du niveau en cours
				
		origine.x = 0.25*vent*Math.pow(t,2)+vitesseI*Math.cos(Math.toRadians(angleI))*t;
		origine.y = g*Math.pow(t,2)-vitesseI*Math.sin(Math.toRadians(angleI))*t+833-hauteur;
	}	
}
