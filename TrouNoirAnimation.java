import javax.swing.*; //fenêtre
import java.awt.*; //graphics
import java.awt.event.*; //événements
import java.io.*; //image
import javax.imageio.*; //ImageIO
import java.awt.image.*; //BufferedImage

/**
 * \class TrouNoirAnimation : fenêtre d'animation du contact trou noir/rover
 */
public class TrouNoirAnimation extends JFrame implements ActionListener{
    
    //<!fenêtre principale de jeu
    private FenetreJeu fenetre;
    
    //<!numéro du niveau où le trou noir emmène l'utilisateur
    private int newNumNiveau;
    
    //<!numéro du niveau où l'utilisateur était quand il est entré en contact avec le trou noir
    private int numNiveau;
    
    //<!image du fond de la fenêtre
    private BufferedImage fond;
    
    //<!texte affiché dépendamment de l'endoit où le trou noir emmène l'utilisateur
    private String texte;
    
    //<!dimenstions du dessin du rover
    private int x, y;
    
    //<!timer pour les mouvements, indicateur de début et fin de niveau
    private Timer time;
    
    //<!variable permettant de compter le temps qui passe
	private double temps;
	
	//<!son de l'animation
	private Audio son = new Audio("audio/trouNoirAnimation.wav");
	
	//<!booléen servant à connaître le sens d'envoi du trou noir
    private boolean sensEnvoiTrouNoir;
    
    /**
   * \fn TrouNoirAnimation(FenetreJeu f) : constructeur TrouNoirAnimation
   * 
   * @param FenetreJeu f : objet désignant la fenêtre principale de jeu
   */ 
    public TrouNoirAnimation(FenetreJeu f) throws IOException{
        fenetre=f;
        numNiveau = fenetre.numNiveau;
        this.setTitle("Aspiration par le Trou Noir");
		this.setSize(1111,833);
		// Positionner la nouvelle fenêtre à l'emplacement de la fenêtre parent
		this.setLocationRelativeTo(f);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
        
        time = new Timer(10,this);
        time.start();
        x=200;
        y=200;
       
        fond = ImageIO.read(new File("images/trou_noir.jpg"));
        this.setVisible(true);
        
		newNumNiveau = (int)(Math.random()*4+1);
		fenetre.newNumNiveau = newNumNiveau;
		
		if(newNumNiveau>numNiveau+1){ //texte en fonction de l'endroit où le trou noir amène l'utilisateur
			sensEnvoiTrouNoir=true;
            texte ="Super !  Le trou noir vous envoie au niveau "+newNumNiveau;
		}else if(newNumNiveau==numNiveau+1){
			sensEnvoiTrouNoir=true;
            texte = "Le trou noir vous am\u00e8ne au niveau sup\u00e9rieur ! ";
		}else if(newNumNiveau==numNiveau){
			sensEnvoiTrouNoir=false;
            texte = "Dommage, vous devez recommencer ce niveau";
		}else if(newNumNiveau<numNiveau){
			sensEnvoiTrouNoir=false;
            texte = "Pas de chance, le trou noir vous renvoie au niveau "+newNumNiveau;
		}
    }
    
    /**
   * \fn void actionPerformed(ActionEvent e) : méthode permettant de gérer la partie graphique de compter le temps grâce au timer, de modifier la taille du rover et gérant le son
   * 
   * @param ActionEvent e : événement associé
   */
    public void actionPerformed (ActionEvent e){
		if(isShowing()){ //si la fenêtre est affichée
			temps++;
			if(temps>50){ //le robot devient de plus en plus petit
				x=x-1;
				y=y-1;
				repaint();
			}
			if(x==190){
				if(fenetre.audioOn){
					son.lecture();
				}
			}
			if(x==0){ //quand il n'existe plus, l'animation est terminée
				time.stop();
				this.setVisible(false);
				try{
					new FenetreChangementNiveau(newNumNiveau, fenetre);
					if(sensEnvoiTrouNoir){
						if(fenetre.audioOn){
							fenetre.winNiveau.lecture();
						}
					}else{
						if(fenetre.audioOn){
							fenetre.looseNiveau.lecture();
						}
					}
				}catch(IOException Exception){
				}
			}
		}else{ //si l'utilisateur ferme la fenêtre, c'est comme si l'animation était terminée
			if(fenetre.audioOn){
				son.arret();
			}
			time.stop();
			this.setVisible(false);
			try{
				new FenetreChangementNiveau(newNumNiveau, fenetre);
				if(sensEnvoiTrouNoir){
					if(fenetre.audioOn){
						fenetre.winNiveau.lecture();
					}
				}else{
					if(fenetre.audioOn){
						fenetre.looseNiveau.lecture();
					}
				}
			}catch(IOException Exception){
			}
		}
	}			
    
    /**
   * \fn void paint(Graphics g) : méthode gérant l'affichage graphique de la fenêtre
   * 
   * @param Graphics g : objet graphique d'affichage
   */ 
    public void paint(Graphics g){
		
		//dessin du fond, du rover et de l'écriture
		
        g.drawImage(fond,0,0,1111,633,null);
        g.setColor(Color.black);
        g.fillRect(0,633,1111,200);
        g.drawImage(fenetre.Perseverance.image,500,400,x,y,null);
        g.setColor(Color.white);
		g.setFont(new Font("Agency FB",Font.BOLD,60));		
		g.drawString(texte,100,750);
    }
}

