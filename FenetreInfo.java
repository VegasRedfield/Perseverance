import javax.swing.*; //fenêtre
import java.awt.*; //couleur
import java.io.*; //image

/**
 * \class FenetreInfo : fenêtre affichant plus d'informations sur la mission Mars 2020
 */
public class FenetreInfo extends JFrame{
	
	/**
   * \fn FenetreInfo() : constructeur FenetreInfo
   */
	public FenetreInfo(){
		
		//initialisation de la fenêtre
		
		this.setTitle("Trajectoire illustree de Perseverance");
		this.setSize(1000,830);
		this.setLocation(400,20);
		this.setResizable(false);
		
		//initialisation des composants de la fenêtre
		
		JPanel Fond = new JPanel();
        Fond.setLayout(null);
        Fond.setBounds(0,0,1000,809);
        Fond.setBackground(Color.black);
        this.add(Fond);
        
        JLabel Image = new JLabel(new ImageIcon("images/nasa_descente.jpg"));
        Image.setBounds(0,0,1000,809);
        Fond.add(Image);
        this.setVisible(true);
        
	}
}
