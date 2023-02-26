import javax.swing.*; //fenêtre
import java.awt.*; //couleur
import java.awt.event.*; //événements
import java.io.*; //images

/**
 * \class FenetreStart : fenêtre d'introduction expliquant les règles, le contexte et le but du jeu
 */

public class FenetreStart extends JFrame implements ActionListener{
	
	//<!fond sonore
	private Audio a = new Audio("audio/accueil.wav");
	
	//<!son du bouton start
	private Audio aStart = new Audio("audio/bouton.wav");
	
	//<!bouton start
	private JButton start1;
	
	//<!bouton permettant de couper le son
	private JButton Son;
	
	//<!booléen permettant de savoir si le son est activé ou non
	private boolean audioOn=true;
	
	/**
   * \fn FenetreStart() : constructeur FenetreStart
   */ 
	public FenetreStart(){
		
		//initialisation de la fenêtre
		
		this.setTitle("Perseverance: le jeu");
		this.setSize(1200,1000);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//création des éléments de la page d'accueil
		
		JLabel image1 = new JLabel(new ImageIcon("images/Andie.png"));
        image1.setBounds(800,700,300,300);
        
		JLabel image2 = new JLabel(new ImageIcon("images/Mael.png"));
        image2.setBounds(100,700,300,300);
        
		start1= new JButton("START");
		start1.setBounds(450,800,300,100);
		start1.setBackground(new Color(249,200,93));
		start1.setFont(new Font("Agency FB",Font.BOLD,50));
		start1.setForeground(new Color(52,62,162));
		start1.addActionListener(this);
		
		JLabel ombre = new JLabel();
		ombre.setLayout(null);
		ombre.setSize(1200,200);
		ombre.setLocation(106,0);
		ombre.setText("PERSEVERANCE");
		ombre.setFont(new Font("Agency FB",Font.BOLD,180));
		ombre.setForeground(Color.black);
		ombre.setBackground(new Color(52,62,162));
		
		JLabel txt = new JLabel();
		txt.setLayout(null);
		txt.setSize(1200,200);
		txt.setLocation(100,0);
		txt.setText("PERSEVERANCE");
		txt.setFont(new Font("Agency FB",Font.BOLD,180));
		txt.setForeground(new Color(249,200,93));
		txt.setBackground(new Color(52,62,162));
		
		String T = readFile("texte/debut.txt");
        JTextArea Texte = new JTextArea(T);
        Texte.setBackground(new Color(52,62,162));
        Texte.setForeground(Color.white);
        Texte.setBounds(50,200,1200,600);
        Texte.setFont(new Font("Agency FB",Font.BOLD,25));
        Texte.setEditable(false);
        
        Son = new JButton(new ImageIcon("images/son on.png"));
		Son.setSize(50,50);
		Son.setLocation(1100,75);
		Son.setBackground(new Color(52,62,162));        
        
        JPanel panneauGlobal = new JPanel();
		panneauGlobal.setLayout(null);
		panneauGlobal.setBounds(0,0,1000,1000);
		panneauGlobal.setBackground(new Color(52,62,162));
		panneauGlobal.add(start1);
		panneauGlobal.add(txt);
		panneauGlobal.add(Texte);
		panneauGlobal.add(ombre);
		panneauGlobal.add(image1);
		panneauGlobal.add(image2);
		panneauGlobal.add(Son);
		
		Son.addActionListener(this);
		
		this.add(panneauGlobal);
		this.setVisible(true);
		if(audioOn){
			a.enBoucle(); //lecture du son en fond
		}
	}
	
	/**
   * \fn void actionPerformed(ActionEvent e) : méthode permettant de lancer le jeu si
   * l'utilisateur appuie sur start ou de couper le son si il appuie sur le bouton correspondant
   * 
   * @param ActionEvent e : événement associé
   */ 
	 public void actionPerformed (ActionEvent e){
		 if(e.getSource()==Son){ //coupure ou réactivation du son
			 if(audioOn){ //si l'audio est on
				a.arret(); //on l'arrête
				audioOn=false;
				Son.setIcon(new ImageIcon("images/son off.png"));
			}else{ //si l'audio est off
				a.enBoucle(); //on le redémarre
				audioOn=true;
				Son.setIcon(new ImageIcon("images/son on.png"));
			}
		 }
		 if(e.getSource()==start1){ //lancement du jeu
			 try{
				if(audioOn){
					aStart.lecture(); //lecture du son du bouton start
					a.arret(); //arrêt du son en fond de la fenêtre start
				}
				setVisible(false); //fermeture de la fenêtre start
				new FenetreChoix(audioOn, this); //création de la fenêtre choix
			}catch(IOException exception){
			}
		}
	 }
	 
	/**
   * \fn static String readFile(String chemin) : méthode permettant de convertir des fichiers texte en String en conservant la présentation du texte
   * 
   * @param String chemin : localisation du fichier à convertir
   */ 
	 public static String readFile(String chemin) {
        try{
            InputStream flux= new FileInputStream(chemin);
            InputStreamReader lecture= new InputStreamReader(flux,"UTF-8");
            try (BufferedReader buff = new BufferedReader(lecture)) {
                String ligne = "";
                String contenu = "";
                while ((ligne = buff.readLine()) != null){
                    contenu += ligne + "\n";
                }
                return contenu;
            } catch (Exception e) {
                System.out.println(e.toString());
            }
       } catch (IOException e){
            System.out.println(e.toString());
       }
       return null;
    } 
	
	
}
		
