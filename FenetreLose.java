import javax.swing.*; //fenêtre
import java.awt.*; //couleur
import java.awt.event.*; //événements
import java.io.*; //image

/**
 * \class FenetreLose : fenêtre s'affichant en cas de game over
 */
public class FenetreLose extends JFrame implements ActionListener{ 
	
	//<!bouton restart
	private JButton Restart;
	
	//<!fenêtre principale de jeu
	private FenetreJeu f;
	
	//<!audio de la fanêtre
	private Audio loose = new Audio("audio/loose.wav");
	
	//<!son du bouton restart
	private Audio restart = new Audio("audio/restart.wav");
	
	/**
   * \fn FenetreLose(FenetreJeu f) : constructeur FenetreLose
   * 
   * @param FenetreJeu f : objet désignant la fenêtre principale de jeu
   */ 
    public FenetreLose (FenetreJeu f) throws IOException{
		
		//création de la fenêtre de défaite
		
        this.setTitle("R\u00e9sultat");
		this.setSize(600,400);
		// Positionner la nouvelle fenêtre à l'emplacement de la fenêtre parent
		this.setLocationRelativeTo(f);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.f = f;
		
		//initialisation + placement des éléments de la fenêtre de défaite
		
        
        JPanel Fond = new JPanel();
        Fond.setLayout(null);
        Fond.setBounds(0,0,600,400);
        
        JLabel Phrase = new JLabel();
		Phrase.setLayout(null);
		Phrase.setSize(400,50);
		Phrase.setLocation(150,20);
		Phrase.setFont(new Font("Arial",Font.BOLD,40));
		Phrase.setForeground(Color.white);
		
        JLabel Gif = new JLabel();
        Gif.setSize(250,150);
        Gif.setLocation(175,100);
        
        Restart = new JButton("Restart");
        Restart.setBounds(250,300,100,50);
        Restart.setBackground(Color.white);
        
        //ajout d'un écouteur sur le bouton restart pour l'IHM
        
        Restart.addActionListener(this);
			
		//ajout des éléments + affichage de la fenêtre de défaite
		
		loose.lecture();
		Phrase.setText("T'as perdu nullos");
		Fond.setBackground(new Color(236,134,131));
		Gif.setIcon(new ImageIcon("images/explosion.gif"));
		Fond.add(Phrase);
		Fond.add(Restart);
		Fond.add(Gif);
		this.add(Fond);
		this.setVisible(true);
    }
    
    /**
   * \fn void actionPerformed(ActionEvent e) : méthode permettant de recommencer le jeu si l'utilisateur appuie sur le bouton restart
   * 
   * @param ActionEvent e : événement associé
   */
    public void actionPerformed(ActionEvent e){
		//la fenêtre de défaite de ferme, le jeu redémarre
		restart.lecture();
		f.restart();
		this.setVisible(false);
	}
    
}
