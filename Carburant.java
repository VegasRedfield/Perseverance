import java.io.*; //image
import javax.imageio.*; //BufferedImage

/**
 * \class Carburant : classe fille d'ObjetsCelestes gérant les carburants
 */
public class Carburant extends ObjetsCelestes{
	
	//<!son du contact entre le carburant et le rover
	private Audio son;
	
	//<!niveau du carburant déterminant le poucentage de carburant ajouté à son contact
	private int bonus;

	/**
   * \fn Carburant(int b, FenetreJeu f) : constructeur Carburant
   * 
   * @param int b : niveau de bonus octroyé par le carburant
   * @param FenetreJeu f : objet désignant la fenêtre principale de jeu
   */ 
	public Carburant(int b, FenetreJeu f) throws IOException{
		
		super(f);
		bonus = b;
		son = new Audio("audio/carburant.wav");
		
		//initialisation de l'image en fonction du bonus du carburant
		
		if(bonus==1){
			image = ImageIO.read(new File("images/carbu1.png"));
		}else if(bonus==2){
			image = ImageIO.read(new File("images/carbu2.png"));
		}
		if(bonus==3){
			image = ImageIO.read(new File("images/carbu3.png"));
		}
			
	}
	
	/**
   * \fn void action() : méthode ajoutant du carburant en fonction de la variable bonus et lançant la lecture du son du carburant
   */ 
	public void action() {
		f.AffJeu.affiche = false;
		if(bonus==1){
			f.pCarbu=f.pCarbu+15.0;
		}else if(bonus==2){
			f.pCarbu=f.pCarbu+25.0;
		}else if(bonus==3){
			f.pCarbu=f.pCarbu+35.0;
		}
		if(f.pCarbu>100){
			f.pCarbu=100;
		}
		if(f.audioOn){
		son.lecture();
		}
	}
}
