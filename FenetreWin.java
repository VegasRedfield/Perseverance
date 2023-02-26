import javax.swing.*; //fenêtre
import java.awt.*; //partie graphique
import java.awt.event.*; //événement
import java.io.*; //image
import javax.imageio.*; //ImageIO
import java.awt.image.*; //BufferedImage

/**
 * \class FenetreWin : fenêtre s'affichant en cas de victoire
 */
public class FenetreWin extends JFrame implements ActionListener{
	
	//<!fenêtre principale de jeu
    FenetreJeu fenetre;
    
    //<!image du sol martien
    BufferedImage sol;
    
    //<!abscisse du rover
    int x =0;
    
    //<!ordonnée du rover
    int y=0;
    
    //<!timer pour les mouvements
    Timer time;
    
    //<!variable permettant de compter le temps qui passe
	double temps;
    
    //<!audio de la fenêtre
    Audio win = new Audio("audio/win.wav");
    
    /**
   * \fn FenetreWin(FenetreJeu f) : constructeur FenetreWin
   * 
   * @param FenetreJeu f : objet désignant la fenêtre principale de jeu
   */ 
    public FenetreWin (FenetreJeu f) throws IOException{
		
		//initialisation de la fenêtre
		
		fenetre=f;
        this.setTitle("Att\u00e9rissage sur Mars");
		this.setSize(500,500);
        // Positionner la nouvelle fenêtre à l'emplacement de la fenêtre parent
        this.setLocationRelativeTo(f);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//lancement du timer
		        
        time = new Timer(10,this);
        time.start();
    
        sol = ImageIO.read(new File("images/mars.jpg"));
        
        this.setVisible(true);
        if(f.audioOn){
			win.lecture();
		}    
    }
    
    /**
   * \fn void actionPerformed(ActionEvent e) : méthode permettant d'actualiser l'ordonnée et l'abscisse du rover dans le temps
   * 
   * @param ActionEvent e : événement associé
   */
    public void actionPerformed (ActionEvent e){
        //descente vers le coin bas-droite de la fenêtre su rover
        x=x+1;
        y=y+1;
        repaint();
        if(y==300){ //on s'arrête à un certain point
			time.stop();
			try{
				new FenetreFinale(fenetre); //création de la fenêtre finale
            }catch(IOException exception){
            }
            this.setVisible(false);
        }
    }
    
    /**
   * \fn void paint(Graphics g) : méthode gérant l'affichage raphique de la fenêtre
   * 
   * @param Graphics g : objet graphique d'affichage
   */ 
    public void paint(Graphics g){
        g.drawImage(sol,0,0,500,500,null);
        g.drawImage(fenetre.Perseverance.image,x,y,100,100,null);
    }
   
    
}

