import java.awt.image.*; //BufferedImage

/**
 * \abstract class ObjetsCelestes : classe abstraite permettant de gérer tous les objets célestes interagissant avec le rover (carburant, trous noirs, météorites...)
 */
public abstract class ObjetsCelestes{
	
	//<!image de l'objet céleste
	protected BufferedImage image;
	
	//<!point en 2D déterminant l'origine de l'objet céleste
	protected APoint origine;
	
	//<!fenêtre principale de jeu
	protected FenetreJeu f;
	
	/**
   * \fn ObjetsCelestes(FenetreJeu f) : constructeur ObjetsCelestes
   * 
   * @param FenetreJeu f : objet désignant la fenêtre principale de jeu
   */ 
	public ObjetsCelestes(FenetreJeu f){
		this.f=f;
	}
	
	/**
   * \fn abstract void action() : méthode abstraite propre à chaque objet céleste et gérant l'action à effectuer au contact du rover
   */ 
	public abstract void action();
}
