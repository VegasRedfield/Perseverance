import java.io.*; //image
import javax.imageio.*; //BufferedImage

/**
 * \class Meteorites : classe fille d'ObjetsCelestes gérant les météorites
 */
public class Meteorites extends ObjetsCelestes{
	
	//<!son du contact entre la météorite et le rover
	private Audio son;
	
	/**
   * \fn Meteorites(FenetreJeu f) : constructeur Meteorites
   * 
   * @param FenetreJeu f : objet désignant la fenêtre principale de jeu
   */
	public Meteorites(FenetreJeu f) throws IOException{ 
		
		super(f);
		son = new Audio("audio/meteorites.wav");
		image = ImageIO.read(new File("images/meteorite.png"));
	}
	
	/**
   * \fn void action() : méthode gérant le son lié de la météorite et lançant l'animation de contact entre meteorite et rover
   */
	public void action(){
		f.toucheMeteorite = true; //le booléen gérant le contact météorite dans la fenêtre jeu devient true
		if(f.audioOn){
			son.lecture();
		}
		if(f.pCarbu>15.0){
			try{
				new MeteoriteAnimation(f); //lancement de l'animation météorite
			}catch(IOException Exception){
			}
		}
	}
}
