import javax.swing.*; //fenêtre
import java.awt.*; //couleur
import java.awt.event.*; //événements
import java.io.*; //image

/**
 * \class FenetreChangementNiveau : fenêtre gérant l'introduction d'un niveau par une animation
 */
public class FenetreChangementNiveau extends JFrame implements ActionListener{
		
	//<!fenêtre principale de jeu
	private FenetreJeu fenetre;
		
	//<!abscisse de la zone de texte
	private int x;
		
	//<!numéro du niveau en cours
	private int niveau;
		
	//<!timer permettant de faire bouger l'affichage
	private Timer mt;
		
	//<!variable permettant de compter le temps qui passe
	private double temps;
	
	/**
   * \fn FenetreChangementNiveau(int i, FenetreJeu f) : constructeur FenetreChangementNiveau
   * 
   * @param int i : numéro du niveau en cours
   * @param FenetreJeu f : objet désignant la fenêtre principale de jeu
   */
    public FenetreChangementNiveau (int i, FenetreJeu f) throws IOException{
		fenetre=f;
		
		//initialisation de la fenêtre de défaite
		
        this.setTitle("R\u00e9sultat");
		this.setSize(1000,500);
		// Positionner la nouvelle fenêtre à l'emplacement de la fenêtre parent
		this.setLocationRelativeTo(f);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		
		//initialisation + placement des éléments de la fenêtre de défaite
		niveau =f.numNiveau;
        mt = new Timer(1,this);
        
        x = 0;
        
		//ajout des éléments + affichage de la fenêtre de défaite
		this.setVisible(true);
		
		mt.start();
		
    }
    
    /**
   * \fn void paint(Graphics g) : méthode gérant l'affichage raphique de la fenêtre
   * 
   * @param Graphics g : objet graphique d'affichage
   */ 
    public void paint(Graphics g){
		g.setColor(new Color(52,62,162));
		g.fillRect(0,0,getWidth(),getHeight());
		g.setColor(new Color(249,200,93));
		g.setFont(new Font("Agency FB",Font.BOLD,120));
		g.drawString("Niveau "+niveau,x,300);
	}
    
    /**
   * \fn void actionPerformed(ActionEvent e) : méthode permettant de gérer la partie graphique de compter le temps grâce au timer, de modifier l'abscisse du texte en conséquence afin de le faire bouger
   * 
   * @param ActionEvent e : événement associé
   */ 
    public void actionPerformed(ActionEvent e){
		if(isShowing()){
			if(x<=1000){ //gestion de la translation du String affiché
				x=x+1;
				repaint();
			}else{ //arrêt de la translation quand l'affichage sort de la fenêtre
				mt.stop();
				setVisible(false);
				fenetre.enJeu = false;
				fenetre.AffJeu.repaint();
			}
		}else{
			mt.stop();
			setVisible(false);
			fenetre.enJeu = false;
			fenetre.AffJeu.repaint();
		}
	}
}
