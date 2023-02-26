import javax.swing.*; //graphique
import java.awt.*; //couleurs
import java.io.*; //File
import javax.imageio.*; //images
	import java.awt.image.*; //bufferedImage

/**
 * \class AffichageJeu : zone de jeu graphique
 */ 
public class AffichageJeu extends JPanel{
	
	//<!fenêtre principale correspondante
	private FenetreJeu f;
	
	//<!tableau contenant les objets célestes du niveau 1
	private ObjetsCelestes[] o1 = new ObjetsCelestes[3];
	
	//<!tableau contenant les objets célestes du niveau 2
	private ObjetsCelestes[] o2 = new ObjetsCelestes[3];
	
	//<!tableau contenant les objets célestes du niveau 3
	private ObjetsCelestes[] o3 = new ObjetsCelestes[4];
	
	//<!tableau contenant les objets célestes du niveau 4
	private ObjetsCelestes[] o4 = new ObjetsCelestes[5];
	
	//<!tableau contenant les objets célestes du niveau 5
	private ObjetsCelestes[] o5 = new ObjetsCelestes[6];
	
	//<!booléen permettant de savoir si un carburant a été touché dans le niveau en cours
	boolean touchCarbu=false; //
	
	//<!booléen gérant l'affichage ou non du carburant
	boolean affiche=true;
	
	//<!image de fond de la zone de jeu
	private BufferedImage fondEtoile;
	
	/**
   * \fn AffichageJeu(FenetreJeu f) : constructeur AffichageJeu
   * 
   * @param FenetreJeu f : objet désignant la fenêtre principale de jeu
   */ 
	public AffichageJeu(FenetreJeu f) throws IOException{
		
		this.f=f;
		fondEtoile= ImageIO.read(new File("images/fond_etoile.jpg")); //l'image de fond ne peut pas être créée dans la méthode paint (trop de ralentissement créé par la boucle try catch
		
		//initialisation des tableaux d'objets
		
		o1[0] = new Carburant(1,f);
		o2[0] = new Carburant(2,f);
		o3[0] = new Carburant(3,f);
		o4[0] = new Carburant(1,f);
		o5[0] = new Carburant(1,f);
		
		o1[1] = new Meteorites(f);
		o2[1] = new Meteorites(f);
		o3[1] = new Meteorites(f);
		o4[1] = new Meteorites(f);
		o5[1] = new Meteorites(f);
        o3[3] = new Meteorites(f);
        o4[3] = new Meteorites(f);
        o4[4] = new Meteorites(f);
        o5[3] = new Meteorites(f);
        o5[4] = new Meteorites(f);
		
		o1[2] = new TrouNoir(f);
		o2[2] = new TrouNoir(f);
		o3[2] = new TrouNoir(f);
		o4[2] = new TrouNoir(f);
		o5[2] = new TrouNoir(f);
        o5[5] = new TrouNoir(f);
	}
	
	/**
   * \fn void paintComponent(Graphics g) : méthode gérant l'affichage des composants du jeu
   * 
   * @param Graphics g : objet graphique d'affichage
   */ 
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//dessin de l'image du rover, de la plateforme et du fond
		
		g.drawImage(fondEtoile,0,0,(int)(20.0/27.0*1500),(int)(5.0/6.0*1000),null);
		g.setColor(new Color(249,200,93));
		g.fillRect((int)(3.5/5.0*f.largeurJeu),(int)(4.5/5.0*f.hauteurJeu),(int)(1.5/5.0*f.largeurJeu),(int)(0.2/5.0*f.hauteurJeu));
		g.drawImage(f.Perseverance.image,(int)(f.Perseverance.origine.x),(int)(f.Perseverance.origine.y),f.Perseverance.largeur,f.Perseverance.hauteur,null);
		
		if(!f.enJeu){ //si l'utilisateur n'est pas en jeu
		
		//dessin des points donnant à l'utilisateur une idée de la courbe suivie par le rover (cette courbe ne prend pas le vent en compte volontairement)
		
			g.setColor(new Color(249,200,93));
			for(int i=0;i<10;i++){
				g.drawOval((int)(f.vI*Math.cos(Math.toRadians(f.aI))*(i+1)+f.Perseverance.largeur),
				(int)(f.gravite*Math.pow(i+1,2)-f.vI*Math.sin(Math.toRadians(f.aI))*(i+1)+833-f.Perseverance.hauteur),
				5,5);
			}
		}
		//dessins propres à chaque f.numNiveau
		
		if(f.numNiveau==1){
			o1[0].origine = new APoint(500.0,550.0);
			o1[1].origine = new APoint(200.0,300.0);
			o1[2].origine = new APoint(800.0,200.0);
			for(int i=0;i<o1.length;i++){
				
				//dessin des objets célestes
				if(o1[i] instanceof Carburant){ //si l'objet est un carburant
					if(affiche){ //il vaut vérifier si on l'affiche ou non
						g.drawImage(o1[i].image,(int)(o1[i].origine.x),(int)(o1[i].origine.y),100,100,null);
					}
				}else{ //sinon on l'affiche dans tous les cas
					g.drawImage(o1[i].image,(int)(o1[i].origine.x),(int)(o1[i].origine.y),100,100,null);
				}
			}
			toucheObjetCeleste(o1);
		}else if(f.numNiveau==2){
			o2[0].origine = new APoint(400.0,300.0);
			o2[1].origine = new APoint(300.0,550.0);
			o2[2].origine = new APoint(600.0,500.0);
			for(int i=0;i<o2.length;i++){
				if(o2[i] instanceof Carburant){
					if(affiche){ 
						g.drawImage(o2[i].image,(int)(o2[i].origine.x),(int)(o2[i].origine.y),100,100,null);
					}
				}else{
					g.drawImage(o2[i].image,(int)(o2[i].origine.x),(int)(o2[i].origine.y),100,100,null);
					}
			}
			toucheObjetCeleste(o2);
		}else if(f.numNiveau==3){
			o3[0].origine = new APoint(500.0,300.0); //carburant
			o3[1].origine = new APoint(300.0,350.0); //météorite 
			o3[2].origine = new APoint(450.0,600.0); //trou noir
            o3[3].origine = new APoint(700.0,100.0); //météorite 
			for(int i=0;i<o3.length;i++){
				if(o3[i] instanceof Carburant){
					if(affiche){ 
						g.drawImage(o3[i].image,(int)(o3[i].origine.x),(int)(o3[i].origine.y),100,100,null);
					}
				}else{
					g.drawImage(o3[i].image,(int)(o3[i].origine.x),(int)(o3[i].origine.y),100,100,null);
					}
			}
			toucheObjetCeleste(o3);
		}else if(f.numNiveau==4){
			o4[0].origine = new APoint(450.0,400.0); //carburant
			o4[1].origine = new APoint(350.0,200.0); //météorite
			o4[2].origine = new APoint(600.0,350.0); //trou noir
            o4[3].origine = new APoint(800.0,500.0); //meteorite
            o4[4].origine = new APoint(350.0,600.0); //météorite
			for(int i=0;i<o4.length;i++){
				if(o4[i] instanceof Carburant){
					if(affiche){ 
						g.drawImage(o4[i].image,(int)(o4[i].origine.x),(int)(o4[i].origine.y),100,100,null);
					}
				}else{
					g.drawImage(o4[i].image,(int)(o4[i].origine.x),(int)(o4[i].origine.y),100,100,null);
				}
			}
			toucheObjetCeleste(o4);
		}else if(f.numNiveau==5){
			o5[0].origine = new APoint(600.0,325.0); //carburant
			o5[1].origine = new APoint(300.0,650.0); //meteorite
			o5[2].origine = new APoint(100.0,350.0); //trou noir
            o5[3].origine = new APoint(450.0,500.0); //meteorite
            o5[4].origine = new APoint(500.0,125.0); //meteorite
            o5[5].origine = new APoint(875.0,450.0); //trou noir
			for(int i=0;i<o5.length;i++){
				if(o5[i] instanceof Carburant){
					if(affiche){ 
						g.drawImage(o5[i].image,(int)(o5[i].origine.x),(int)(o5[i].origine.y),100,100,null);
					}
				}else{
					g.drawImage(o5[i].image,(int)(o5[i].origine.x),(int)(o5[i].origine.y),100,100,null);
				}
			}
			toucheObjetCeleste(o5);
		}
	}
	
	/**
   * \fn void toucheObjetCeleste(ObjetCelestes[] o) : méthode gérant le contact entre le rover et un objet céleste
   * 
   * @param ObjetCelestes[] o : tableau contenant les objets célestes du niveau en cours
   */ 
	public void toucheObjetCeleste (ObjetsCelestes [] o){
        
        		for(int i=0;i<o.length;i++){
				
				//si le rover touche l'objet céleste i
				
				if(
				//coin en haut à gauche
				((int)(f.Perseverance.origine.x+30)>=(int)(o[i].origine.x) && (int)(f.Perseverance.origine.x+30)<=(int)(o[i].origine.x)+100 &&
				(int)(f.Perseverance.origine.y+30)>=(int)(o[i].origine.y) && (int)(f.Perseverance.origine.y+30)<=(int)(o[i].origine.y)+100) || 
				//coin en haut à droite
				((int)(f.Perseverance.origine.x+f.Perseverance.largeur-30)>=(int)(o[i].origine.x) && (int)(f.Perseverance.origine.x+f.Perseverance.largeur-30)<=(int)(o[i].origine.x)+100 && 
				(int)(f.Perseverance.origine.y+30)>=(int)(o[i].origine.y) && (int)(f.Perseverance.origine.y+30)<=(int)(o[i].origine.y)+100) ||
				//coin en bas à droite
				((int)(f.Perseverance.origine.x+f.Perseverance.largeur-30)>=(int)(o[i].origine.x) && (int)(f.Perseverance.origine.x+f.Perseverance.largeur-30)<=(int)(o[i].origine.x)+100 && 
				(int)(f.Perseverance.origine.y+f.Perseverance.hauteur-30)>=(int)(o[i].origine.y) && (int)(f.Perseverance.origine.y+f.Perseverance.hauteur-30)<=(int)(o[i].origine.y)+100) ||
				//coin en bas à gauche
				((int)(f.Perseverance.origine.x+30)>=(int)(o[i].origine.x) && (int)(f.Perseverance.origine.x+30)<=(int)(o[i].origine.x)+100 && 
				(int)(f.Perseverance.origine.y+f.Perseverance.hauteur-30)>=(int)(o[i].origine.y) && (int)(f.Perseverance.origine.y+f.Perseverance.hauteur-30)<=(int)(o[i].origine.y)+100)){
					
					//on lance la méthode action de cet objet, 1 seule fois par objet
					if(o[i] instanceof Carburant){
						if(touchCarbu==false){
							o[i].action();
							touchCarbu=true;
						}
					}else{
						o[i].action();
					}
				}
			}
    }
}
