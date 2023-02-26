import javax.swing.*; //fenêtre
import java.awt.*; //couleur
import java.awt.event.*; //événements
import java.io.*; //images
import javax.imageio.*; //BufferedImage

/**
 * \class FenetreChoix : fenêtre permettant de choisir le robot avec lequel l'utilisateur veut jouer  
 */ 

public class FenetreChoix extends JFrame implements ActionListener{ //fenêtre du choix du rover
    
    //<!rover Andie
    private Rover Andie;
    
    //<!rover Marjorie
    private Rover Marjorie;
    
    //<!rover Maël
    private Rover Mael;
    
    //<!rover Florent
    private Rover Florent; 
    
    //<!rover Janelle
    private Rover Janelle;
    
    //<!bouton de choix sur lequel l'utilisateur appuie pour commencer le jeu  
    private JButton Choisir;
    
    //<!bouton sélection du robot Andie
    private JButton Didi;
    
    //<!bouton sélection du robot Marjorie
    private JButton Marjo;
    
    //<!bouton sélection du robot Maël
    private JButton Mama;
    
    //<!bouton sélection du robot Florent
    private JButton Floflo;
    
    //<!bouton sélection du robot Janelle
    private JButton Jaja;
    
    //<!bouton permettant de couper le son
    private JButton Son;
    
    //<!booléen permettant de savoir si le son est activé ou non
    private boolean audioOn;
    
    //<!zone de fond bleu à droite de la fenêtre
    private JPanel Fond2;
    
    //<!zone de texte où l'on va afficher la description de chaque robot
    private JTextArea Texte;
    
    //<!rover choisi
    private Rover roverChoisi;
    
    //<!fond sonore
    private Audio a = new Audio("audio/choixRover.wav");
    
    //<!son du bouton de sélection de chaque rover
    private Audio aChoix = new Audio("audio/boutonSelection.wav");  
    
    //<!son du bouton choix si aucun rover sélectionné
    private Audio erreurChoix = new Audio("audio/erreurChoix.wav"); 
    
    //<!son du bouton choix si un rover a été sélectionné
    private Audio aStart = new Audio("audio/bouton.wav"); 
    
    /**
   * \fn FenetreChoix(boolean b) : constructeur FenetreChoix
   * 
   * @param boolean b : booléen indiquant si le son était allumé ou non dans la fenêtre start
   */ 
    public FenetreChoix (boolean b, JFrame fenetreParent) throws IOException{
		
		audioOn=b;
		
        //initialisation de la fenêtre
        
        this.setTitle("Choix du Rover");
		this.setSize(1000,1000);
        // Positionner la nouvelle fenêtre à l'emplacement de la fenêtre parent
        this.setLocationRelativeTo(fenetreParent);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//initialisation des composants
        
        JPanel Fond = new JPanel();
        Fond.setLayout(null);
        Fond.setBounds(0,0,1000,1000);
        Fond.setBackground(Color.white);
        this.add(Fond);        
        
        JLabel Titre = new JLabel ("SELECTION DU ROVER:");
        Titre.setLayout(null);
        Titre.setBounds(10,0,400,100);
        Titre.setFont(new Font("Agency FB",Font.BOLD,25));
        Titre.setForeground(new Color(249,200,93));
        Fond.add(Titre);
        
        JLabel NomJeu = new JLabel ("PERSEVERANCE");
        NomJeu.setLayout(null);
        NomJeu.setBounds(730,10,270,100);
        NomJeu.setFont(new Font("Agency FB",Font.BOLD,45));
        NomJeu.setForeground(new Color(249,200,93));
        Fond.add(NomJeu);
       
        JLabel A = new JLabel(new ImageIcon("images/Andie200.png"));
        A.setBounds(100,90,200,200);
        Fond.add(A);
        
        JLabel B = new JLabel(new ImageIcon("images/Marjo200.png"));
        B.setBounds(400,90,200,200);
        Fond.add(B);
        
        JLabel C = new JLabel(new ImageIcon("images/Florent200.png"));
        C.setBounds(100,390,200,200);
        Fond.add(C);
        
        JLabel D = new JLabel(new ImageIcon("images/Mael200.png"));
        D.setBounds(400,390,200,200);
        Fond.add(D);
        
        JLabel E = new JLabel(new ImageIcon("images/Janelle200.png"));
        E.setBounds(250,690,200,200);
        Fond.add(E);
        
        Didi = new JButton("Andie");
		Didi.setLayout(null);
		Didi.setBounds(100,305,200,50);
		Didi.setBackground(new Color(219,190,242));
        Didi.addActionListener(this); 
        Didi.setFont(new Font("Agency FB",Font.BOLD,25));
        Fond.add(Didi);
        
        Marjo = new JButton("Marjo");
		Marjo.setLayout(null);
		Marjo.setBounds(400,305,200,50);
		Marjo.setBackground(new Color(242,190,214));
        Marjo.addActionListener(this);
        Marjo.setFont(new Font("Agency FB",Font.BOLD,25)); 
        Fond.add(Marjo);
        
        Mama= new JButton("Ma\u00ebl");
		Mama.setLayout(null);
		Mama.setBounds(400,605,200,50);
		Mama.setBackground(new Color(251,236,225));
		Mama.setFont(new Font("Agency FB",Font.BOLD,25));
        Mama.addActionListener(this); 
        Fond.add(Mama);
        
        Floflo = new JButton("Florent");
		Floflo.setLayout(null);
		Floflo.setBounds(100,605,200,50);
		Floflo.setBackground(new Color(180,207,250));
        Floflo.addActionListener(this);
        Floflo.setFont(new Font("Agency FB",Font.BOLD,25)); 
        Fond.add(Floflo);
        
        Jaja = new JButton("Janelle");
		Jaja.setLayout(null);
		Jaja.setBounds(250,905,200,50);
		Jaja.setBackground(new Color(247,198,197));
        Jaja.addActionListener(this); 
        Jaja.setFont(new Font("Agency FB",Font.BOLD,25));
        Fond.add(Jaja);
        
        Choisir = new JButton("CHOISIR");
		Choisir.setLayout(null);
		Choisir.setBounds(725,800,250,100);
		Choisir.setBackground(new Color(249,200,93));
		Choisir.setForeground(new Color(52,62,162));
        Choisir.addActionListener(this); 
        Choisir.setFont(new Font("Agency FB",Font.BOLD,35));
        Fond.add(Choisir);
        
        Texte = new JTextArea();
        Texte.setEditable(false);
        Texte.setLayout(null);
        Texte.setBackground(new Color(52,62,162));
        Texte.setForeground(new Color(249,200,93));
        Texte.setFont(new Font("Agency FB",Font.BOLD,25));
        Texte.setBounds(725,300,300,400);
        Fond.add(Texte);
        
        Fond2 = new JPanel();
        Fond2.setLayout(null);
        Fond2.setBounds(700,0,300,1000);
        Fond2.setBackground(new Color(52,62,162));
        Fond.add(Fond2);
        
        Son = new JButton();
        if(audioOn){
			Son.setIcon(new ImageIcon("images/son on.png"));
		}else{
			Son.setIcon(new ImageIcon("images/son off.png"));
		}
		Son.setSize(50,50);
		Son.setLocation(825,100);
		Son.setBackground(new Color(52,62,162));
		Son.addActionListener(this); 
		Fond.add(Son);
        
		//initialisation des rovers
        
        Andie = new Rover(ImageIO.read(new File("images/Andie.png")),"texte/Andie.txt");
        Marjorie = new Rover(ImageIO.read(new File("images/Marjo.png")),"texte/Marjo.txt");
        Mael = new Rover(ImageIO.read(new File("images/Mael.png")),"texte/Mael.txt");
        Florent = new Rover(ImageIO.read(new File("images/Florent.png")),"texte/Florent.txt");
        Janelle = new Rover(ImageIO.read(new File("images/Janelle.png")),"texte/Janelle.txt");
              
        this.setVisible(true);
        if(audioOn){
			a.enBoucle(); //lecture du fond sonore
		}
    
    }
    
    /**
   * \fn void actionPerformed(ActionEvent e) : méthode permettant de lancer le jeu si
   * l'utilisateur appuie sur le bouton de choix ou de couper le son si il appuie sur le bouton correspondant
   * 
   * @param ActionEvent e : événement associé
   */ 
    public void actionPerformed (ActionEvent e){ 
		
		if(e.getSource()==Son){
			if(audioOn){
				a.arret();
				audioOn=false;
				Son.setIcon(new ImageIcon("images/son off.png"));
			}else{
				a.enBoucle();
				audioOn=true;
				Son.setIcon(new ImageIcon("images/son on.png"));
			}			
		}

	//le rover sélectionné est enregistré

        if (e.getSource() == Didi){
            Texte.repaint();
            if(audioOn){
            aChoix.lecture();
			}
            Texte.setText(readFile(Andie.description));
            roverChoisi=Andie;
            
        }
        if (e.getSource() == Floflo){
            Texte.repaint();
            if(audioOn){
            aChoix.lecture();
			}
            Texte.setText(readFile(Florent.description));
            roverChoisi=Florent;
        }
        if (e.getSource() == Jaja){
            Texte.repaint();
            if(audioOn){
            aChoix.lecture();
			}
            Texte.setText(readFile(Janelle.description));
            roverChoisi=Janelle;
        }
        if (e.getSource() == Mama){
            Texte.repaint();
            if(audioOn){
            aChoix.lecture();
			}
            Texte.setText(readFile(Mael.description));
            roverChoisi=Mael;
        }
        if (e.getSource() == Marjo){
            Texte.repaint();
            if(audioOn){
            aChoix.lecture();
			}
            Texte.setText(readFile(Marjorie.description));
            roverChoisi=Marjorie;
        }
        
	//appui sur le bouton choisir
        
        if(e.getSource()==Choisir){
            if(roverChoisi!=null){ //si l'utilisateur a choisi un rover
				try{
					if(audioOn){
						aStart.lecture(); //lecture du son du bouton start
						a.arret();
					}
					new FenetreJeu(roverChoisi,audioOn, this); //création de la fenêtre principale de jeu
					setVisible(false);
				}catch(IOException exception){
				}
			}else{ //si l'utilisateur n'a choisi aucun rover
				if(audioOn){
					erreurChoix.lecture(); //son erreur
				}
				JOptionPane.showMessageDialog(this,"Veuillez s\u00e9lectionner un rover !"); //message d'erreur
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
