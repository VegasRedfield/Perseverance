import java.io.*; //image
import javax.imageio.*; //BufferedImage

/**
 * \class TrouNoir : classe fille d'ObjetsCelestes gérant les trous noirs
 */
public class TrouNoir extends ObjetsCelestes{
	
	/**
   * \fn TrouNoir(FenetreJeu f) : constructeur TrouNoir
   * 
   * @param FenetreJeu f : objet désignant la fenêtre principale de jeu
   */
	public TrouNoir(FenetreJeu f) throws IOException{
		super(f);
		image = ImageIO.read(new File("images/trouNoir.png"));
	}
	
	/**
   * \fn void action() : méthode lançant l'animation de contact entre trou noir et rover
   */
	public void action(){
		try{
			f.toucheTrouNoir = true; //le booléen gérant le contact trou noir dans la fenêtre jeu devient true
			new TrouNoirAnimation(f); //lancement de l'animation trou noir
		}catch(IOException Exception){
		}
	}
}
