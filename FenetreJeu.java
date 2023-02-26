import javax.swing.*; //fenêtre
import java.awt.*; //couleur
import java.awt.event.*; //événements ActionListener
import javax.swing.event.*; //événements ChangeListener
import java.io.*; //images

/**
 * \class FenetreJeu : fenêtre principale interactive de jeu  
 */ 
public class FenetreJeu extends JFrame implements ActionListener, ChangeListener{
	
	//<!barre de jauge du carburant
	private JProgressBar currentCarbu;
	 
	//<!bouton start
	private JButton Start; 
	
	//<!bouton retstart
	private JButton Restart;
	
	//<!zone d'affichage du titre du jeu
	private JLabel Titre;
	
	//<!rover qui est l'objet dirigé par l'utilisateur
	Rover Perseverance;
	
	//<!objet responsable de la partie graphique du jeu
	AffichageJeu AffJeu; 
	
	//<!zone de couleur or autour de la partie graphique du jeu
	private JPanel Contour;
	
	//<!plateforme d'arrivée
	private JPanel Plateforme;
	
	//<!curseur de défilement permettant de régler la vitesse initiale du rover
	private JSlider Vitesse;
	
	//<!curseur de défilement permettant de régler l'angle initial du rover
	private JSlider Angle;
	
	//<!zone d'affichage de la vitesse initiale du rover
	private JLabel VitesseI;
	
	//<!zone d'affichage de l'angle initial du rover
	private JLabel AngleI;
	
	//<!zone d'affichage du vent présent sur le niveau
	private JLabel Vent;
	
	//<!zone d'affichage de la gravité présente sur le niveau
    private JLabel Gravite;
    
    //<!zone d'affichage pourcentage carburant restant
	private JLabel Carbu;
	 
	//<!bouton permettant d'afficher les règles du jeu, disponibles durant toute la partie
	private JButton Aide; 
	
	//<!bouton permettant de couper le son
	private JButton Son;
	
	//<!vitesse initiale du rover
	int vI;
	
	//<!angle initial du rover
	int aI;
	
	//<!vent présent sur le niveau en cours
	private int v;
	
	//<!pourcentage de carburant restant au rover
	double pCarbu=100.0;
	
	//<!gravité présente sur le niveau en cours
	double gravite;
	
	//<!numéro du niveau en cours
	int numNiveau;
	
	//<!niveau aléatoire auquel envoie un trou noir au contact du rover
	int newNumNiveau;
	
	//<!largeur de la zone de jeu dans laquelle évolue le rover
	int largeurJeu;
	
	//<!hauteur de la zone de jeu dans laquelle évolue le rover
	int hauteurJeu;
	
	//<!booléen permettant de savoir si un niveau est en cours de jeu ou non
	boolean enJeu=false;
	
	//<!booléen permettant de savoir si le rover a touché une météorite
	boolean toucheMeteorite=false; 
	
	//<!booléen permettant de savoir si le rover a touché un trou noir
	boolean toucheTrouNoir=false;
	
	//<!booléen permettant de savoir si le son est activé ou non
	boolean audioOn;
	
	//<!timer gérant l'évolution du jeu et servant de base temporelle
	private Timer mt;
	
	//<!variable permettant de compter le temps qui passe
	private double temps;
	
	//<!fond sonore
	private Audio fond = new Audio("audio/ambianceEspace.wav");
	
	//<!son des curseurs de défilement
	private Audio tick = new Audio("audio/tick3.wav");
	
	//<!son bouton restart
	private Audio restart = new Audio("audio/restart.wav");
	
	//<!son bouton start
	private Audio start = new Audio("audio/start.wav");
	
	//<!son quand l'utilisateur réussit un niveau
	Audio winNiveau = new Audio("audio/winNiveau.wav");
	
	//<!son quand l'utilisateur rate un niveau
	Audio looseNiveau = new Audio("audio/looseNiveau.wav");
	
	//<!son quand le trou noir envoie l'utilisateur vers un niveau inférieur
	private Audio trouNoirMalus = new Audio("audio/trouNoirMalus.wav");
	
	//<!son quand le trou noir envoie l'utilisateur vers un niveau supérieur
	private Audio trouNoirBonus = new Audio("audio/trouNoirBonus.wav");

	/**
   * \fn FenetreJeu(Rover r, boolean b) : constructeur FenetreJeu
   * 
   * @param boolean b : booléen indiquant si le son était allumé ou non dans la fenêtre start
   * @param Rover r : rover avec lequel l'utilisateur a choisi de jouer
   */ 
	public FenetreJeu(Rover r,boolean b, JFrame fenetreParent) throws IOException{
				
		audioOn=b;
		
		//Initialisation de la fenêtre principale
	
		this.setTitle("Fenetre Jeu");
		this.setSize(1500,1000);
		// Positionner la nouvelle fenêtre à l'emplacement de la fenêtre parent
		this.setLocationRelativeTo(fenetreParent);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		mt = new Timer(1,this); //timer très rapide pour donner un effet de mouvement fluide
		
		//Initialisation et placement de tous les composants statiques
		
		Start = new JButton("LAUNCH");
        Start.setLayout(null);
        Start.setBounds((int)(5.0/99.0*1500),
        (int)(13.0/21.0*1000),(int)(40.0/297.0*1500),(int)(2.0/21.0*1000));
        Start.setBackground(new Color(242,121,121));
        Start.setForeground(new Color(52,62,162));
        Start.setFont(new Font("Agency FB",Font.BOLD,60));
        
        Restart = new JButton("RESTART");
        Restart.setLayout(null);
        Restart.setBounds((int)(5.0/99.0*1500),
        (int)(10/21.0*1000),(int)(40.0/297.0*1500),(int)(2.0/21.0*1000));
        Restart.setForeground(new Color(52,62,162));
        Restart.setBackground(new Color(249,200,93));
        Restart.setFont(new Font("Agency FB",Font.BOLD,40));
        
                
        Titre = new JLabel("PERSEVERANCE | Niveau "+ numNiveau);
        Titre.setLayout(null);
        Titre.setBounds((int)(60.0/99.0*1500),
        (int)(1.5/105.0*1000),(int)(800.0/297.0*1500),(int)(13.0/210.0*1000));
        Titre.setForeground(new Color(249,200,93));
        Titre.setFont(new Font("Agency FB",Font.BOLD,65));
              
        Vitesse = new JSlider(0,100,50);
        vI=Vitesse.getValue();
        Vitesse.setLayout(null);
        Vitesse.setBounds((int)(5.0/99.0*1500),
        (int)(3.5/21.0*1000),(int)(40.0/297.0*1500),(int)(2.0/21.0*1000));
        Vitesse.setMajorTickSpacing(25);
        Vitesse.setPaintTicks(true);
        Vitesse.setPaintLabels(true);
        Vitesse.setBackground(new Color(52,62,162));
        Vitesse.setForeground(new Color(249,200,93));
                
        VitesseI = new JLabel("Vitesse initiale : "+vI+" km/h");
        VitesseI.setLayout(null);
        VitesseI.setBounds((int)(5.0/99.0*1500),
        (int)(2.5/21.0*1000),(int)(40.0/297.0*1500),(int)(1.5/21.0*1000));
        VitesseI.setForeground(new Color(249,200,93));
        VitesseI.setFont(new Font("Agency FB",Font.BOLD, 19));
               
        Angle = new JSlider(0,90,45);
        aI=Angle.getValue();
        Angle.setLayout(null);
        Angle.setBounds((int)(5.0/99.0*1500),
        (int)(7.0/21.0*1000),(int)(40.0/297.0*1500),(int)(2.0/21.0*1000));
        Angle.setMajorTickSpacing(15);
        Angle.setPaintTicks(true);
        Angle.setPaintLabels(true);
        Angle.setBackground(new Color(52,62,162));
        Angle.setForeground(new Color(249,200,93));
                
                
        AngleI = new JLabel("Angle initial : "+aI+" \u00b0");
        AngleI.setLayout(null);
        AngleI.setBounds((int)(5.0/99.0*1500),
        (int)(6.0/21.0*1000),(int)(40.0/297.0*1500),(int)(1.5/21.0*1000));
        AngleI.setForeground(new Color(249,200,93));
        AngleI.setFont(new Font("Agency FB",Font.BOLD, 19));
        
                
        Vent = new JLabel();
        Vent.setLayout(null);
        Vent.setBounds((int)(5.0/99.0*1500),
        (int)(0.50/21.0*1000)+20,(int)(40.0/297.0*1500),(int)(1.5/21.0*1000));
        Vent.setForeground(new Color(249,200,93));
        Vent.setFont(new Font("Agency FB",Font.BOLD,35));
        
        Gravite = new JLabel();
        Gravite.setLayout(null);
        Gravite.setBounds((int)(5.0/99.0*1500),
        (int)(0.50/21.0*1000)-20,(int)(40.0/297.0*1500),(int)(1.5/21.0*1000));
        Gravite.setForeground(new Color(249,200,93));
        Gravite.setFont(new Font("Agency FB",Font.BOLD,35));
               
        Carbu = new JLabel("Carburant : "+(int)(pCarbu)+"%");
        Carbu.setLayout(null);
        Carbu.setBounds((int)(5.0/99.0*1500),
        (int)(16.0/21.0*1000),(int)(50.0/297.0*1500),(int)(1.5/21.0*1000));
        Carbu.setForeground(new Color(249,200,93));
        Carbu.setFont(new Font("Agency FB",Font.BOLD,35));
	
		currentCarbu = new JProgressBar(0,100);
		currentCarbu.setBounds((int)(5.0/99.0*1500),
		(int)(18.0/21.0*1000),(int)(40.0/297.0*1500),(int)(0.75/21.0*1000));
		currentCarbu.setBorderPainted(true);
		currentCarbu.setValue((int)(pCarbu));
		currentCarbu.setForeground(new Color(249,200,93));
		
		largeurJeu = (int)(20.0/27.0*1500);
		hauteurJeu = (int)(5.0/6.0*1000);
		
		Contour = new JPanel();
		Contour.setLayout(null);
		Contour.setBounds((int)(70.0/297.0*1500)-10,(int)(2.0/21.0*1000)-10,(int)(20.0/27.0*1500)+20,(int)(5.0/6.0*1000)+20);
		Contour.setBackground(new Color(249,200,93));
		
		//Initialisation et placement initial des composants graphiques
		
		Perseverance = r;
		AffJeu = new AffichageJeu(this);
		AffJeu.setLayout(null);
		AffJeu.setBounds((int)(70.0/297.0*1500),(int)(2.0/21.0*1000),
		(int)(20.0/27.0*1500),(int)(5.0/6.0*1000));
		AffJeu.repaint();
		
		Aide = new JButton("Aide");
		Aide.setSize(200,50);
		Aide.setLocation(350,20);
		Aide.setFont(new Font("Agency FB",Font.BOLD,35));
		Aide.setBackground(new Color(52,62,162));
		Aide.setForeground(Color.white);
		
		Son = new JButton();
		if(audioOn){
			Son.setIcon(new ImageIcon("images/son on.png"));
		}else{
			Son.setIcon(new ImageIcon("images/son off.png"));
		}
		Son.setSize(50,50);
		Son.setLocation(650,20);
		Son.setBackground(new Color(52,62,162));
		
		//ajout de tous ces composants au JPanel principal		
		
		JPanel panneauGlobal = new JPanel();
		panneauGlobal.setBackground(new Color(52,62,162));
		panneauGlobal.setLayout(null);
		panneauGlobal.add(Start);
		panneauGlobal.add(Restart);
		panneauGlobal.add(Titre);
		panneauGlobal.add(Aide);
		panneauGlobal.add(Son);
		panneauGlobal.add(AffJeu);
		panneauGlobal.add(Contour);
		panneauGlobal.add(Vitesse);
		panneauGlobal.add(VitesseI);
		panneauGlobal.add(Angle);
		panneauGlobal.add(AngleI);
		panneauGlobal.add(Vent);
        panneauGlobal.add(Gravite);
		panneauGlobal.add(Carbu);
		panneauGlobal.add(currentCarbu);
		
		//ajouts d'écouteurs sur les éléments utilisant l'IHM
		
		Start.addActionListener(this);
		Restart.addActionListener(this);
		Angle.addChangeListener(this);
		Vitesse.addChangeListener(this);
		Aide.addActionListener(this);
		Son.addActionListener(this);
		
		//ajout du JPanel principal à la fenêtre + affichage de celle-ci
		
		this.add(panneauGlobal);
		this.setVisible(true);
		
		//le jeu peut commencer
		
		debutJeu();
	}
	
	/**
   * \fn void debutJeu() : méthode de lancement du niveau 1
   */ 
	public void debutJeu(){
		if(audioOn){
			fond.enBoucle();
		}
		numNiveau = 1;
		niveau(numNiveau); //affichage du niveau 1
	}
	
	/**
   * \fn void niveau(int i) : méthode affectant à chaque niveau ses caractéristiques propres et actualisant l'affichage en conséquence
   * 
   * @param int i : numéro du niveau à traiter
   */ 
	public void niveau(int i){
		
		//boucles contenant toutes les caractéristiques physiques propres à chaque niveau et mettant à jour l'affichage de la fenêtre pour chacun d'entre eux
		
		if(i==1){
			gravite = 2.0;
			v =(int) (1+2*Math.random()-1);
		}else if(i==2){
			gravite = 2.25;
			v =(int)(1+4*Math.random()-2);
		}else if(i==3){
			gravite = 2.75;
			v =(int)(1+6*Math.random()-3);
		}else if(i==4){
			gravite = 3.0;
			v =(int)(1+8*Math.random()-4);
		}else if(i==5){
			gravite = 3.25;
			v =(int)(1+10*Math.random()-5);
		}
		if(v>=0){
			Vent.setText("VENT : "+"+"+v);
		}else if(v<0){
			Vent.setText("VENT : "+v);
		}
        Gravite.setText("GRAVITE : "+gravite);
		Titre.setText("PERSEVERANCE Niveau "+ numNiveau);
	}
	
	/**
   * \fn void actionPerformed(ActionEvent e) : méthode permettant de gérer la partie graphique du jeu grâce au timer et de gérer l'action des différents boutons
   * 
   * @param ActionEvent e : événement associé
   */ 
	public void actionPerformed (ActionEvent e){
		if(e.getSource() == Son){
			if(audioOn){
				fond.arret();
				audioOn=false;
				Son.setIcon(new ImageIcon("images/son off.png"));
			}else{
				fond.enBoucle();
				audioOn=true;
				Son.setIcon(new ImageIcon("images/son on.png"));
			}
		}
		if(e.getSource() == Aide){ //appui sur le bouton aide
			String T = readFile("texte/aide.txt");
			JOptionPane.showMessageDialog(this,T); //message rappelant les règles du jeu
		}
		if(e.getSource()==Start){ //appui sur le bouton Start
			if(!enJeu){ //si l'utilisateur n'est pas déjà en train de jouer
				enJeu = true; //alors il commence à jouer
				timerStart();	//on indique le début du mouvement par le début du timer
				if(audioOn){
					start.lecture();
				}
			}		
		}
		if(e.getSource()==mt){ //toutes les millisecondes si le timer est en route (= si l'utilisateur est en jeu)
			
			//mise à jour de la position du Rover et de ses caractéristiques
			
			pCarbu =pCarbu-0.01;
			currentCarbu.setValue((int)(pCarbu));
			Carbu.setText("Carburant : "+(int)(pCarbu)+"%");
			Perseverance.ChangePosition(v,vI,aI,temps/50,gravite);
			temps++;
			AffJeu.repaint();
			
			//vérifiacation de l'avancée du niveau
			
			niveauTermine();
		}
		if(e.getSource()==Restart){ //appui sur le bouton restart
			if(!enJeu){
				if(audioOn){
					restart.lecture();
				}
				restart(); //on relance le jeu
			}
		}
	}
	
	/**
   * \fn void timerStart() : méthode de lancement du timer au temps 0
   */ 
	public void timerStart(){
		temps = 0 ;
		mt.start();
	}
	
	/**
   * \fn void timerStart() : méthode d'arrêt du timer
   */ 
	public void timerStop(){
		mt.stop();
	}
	
	/**
   * \fn void actionChanged(ChangeEvent e) : méthode permettant de gérer l'action des différents curseurs de défilement
   * 
   * @param ActionEvent e : événement associé
   */ 
	public void stateChanged(ChangeEvent e){
		if(!enJeu){ //l'utilisateur ne peut modifier aucunes caractéristiques si le niveau est en cours
			if(audioOn){
				tick.lecture();
			}
			if(e.getSource()==Vitesse){  
				
				//mise à jour + affichage changement vitesse
				
				vI = Vitesse.getValue();
				VitesseI.setText("Vitesse initiale : "+vI+" km/h");
			}else if(e.getSource()==Angle){
				
				//mise à jour + affichage changement angle
				
				aI = Angle.getValue();
				AngleI.setText("Angle initial : "+aI+" \u00b0");
			}
		}
		AffJeu.repaint();	
	}
	
	/**
   * \fn void niveauTermine() : méthode permettant de tester si le niveau est terminé ou non et d'agir en conséquence
   */ 
    public void niveauTermine() {
		
		if((int)(Perseverance.origine.y)<=(int)(4.5/5.0*hauteurJeu-Perseverance.hauteur+10) &&
		(int)(Perseverance.origine.y)>=(int)(4.5/5.0*hauteurJeu-Perseverance.hauteur)
		 && (Perseverance.origine.x>=3.5/5.0*largeurJeu-Perseverance.largeur) 
		 && Perseverance.origine.x<=3.5/5.0*largeurJeu+1.5/5.0*largeurJeu){ //si le niveau a été réussi
			 
			//gestion audio 
			
			if(audioOn){ 
				winNiveau.lecture();
				start.arret();
			}
			 
			 //passage et lancement du niveau suivant
			 
			numNiveau++;
			niveau(numNiveau);
            if(numNiveau!=6){
				try{
					new FenetreChangementNiveau(numNiveau,this); //fenêtre de transition entre niveaux
				}catch(IOException Exception){
				}
            }
			
			//arrêt du timer et indication que l'utilisateur n'est plus en jeu
			
			timerStop();
			enJeu = false;
			
			//réinitialisation des attributs d'affichage graphique + replacement du rover et des composants
			
			AffJeu.touchCarbu=false;
			AffJeu.affiche=true;
			Perseverance.origine = new APoint(0,833-100);
			Angle.setValue(45);
			Vitesse.setValue(50);
			aI = Angle.getValue();
			AngleI.setText("Angle initial : "+aI+" \u00b0");
			vI = Vitesse.getValue();
			VitesseI.setText("Vitesse initiale : "+vI+" km/h");
			
			
		}else if((Perseverance.origine.y>hauteurJeu-Perseverance.hauteur) || (Perseverance.origine.x+Perseverance.largeur>=largeurJeu)
		 || ((int)(pCarbu)==0) || (((int)(Perseverance.origine.x+Perseverance.largeur)>=(int)(3.5/5.0*largeurJeu))&&((int)(Perseverance.origine.y+Perseverance.hauteur)>=(int)(4.5/5.0*hauteurJeu)))
		 || toucheMeteorite || toucheTrouNoir){ //si le niveau n'a pas été réussi ou qu'on a touché un trou noir
			 
			 //gestion audio
			 
			 if(audioOn){
				start.arret();
			}
			
			if(toucheTrouNoir){ //si la raison de son échec est le contact avec un trou noir
				
				//gestion sonore
				
				if(newNumNiveau>numNiveau+1){
					if(audioOn){
						trouNoirBonus.lecture();
					}
				}else if(newNumNiveau<numNiveau){
					if(audioOn){
						trouNoirMalus.lecture();
					}
				}
				
				//actualisation du numéro du niveau
				
				numNiveau = newNumNiveau;
			}
			
			//lancement du niveau (le même que précedamment sauf si l'utilisateur a touché un trou noir
			
			niveau(numNiveau);
			
			//arrêt du timer
			
			timerStop();
			
			//malus : baisse du carburant et affichage de cette baisse
			  
			pCarbu=pCarbu-15.0;
			if(pCarbu<0){
				pCarbu=0;
			}
			currentCarbu.setValue((int)(pCarbu));
			Carbu.setText("Carburant : "+(int)(pCarbu)+"%");
			
			if(!toucheMeteorite && !toucheTrouNoir && (int)(pCarbu)!=0){ //si la raison de l'échec de l'utilisateur est la sortie des limites du jeu
				try{
					if(audioOn){
						looseNiveau.lecture(); //son de défaite (d'autres sons sont joués si nous ne sommes pas dans cette boucle)
					}
					new FenetreChangementNiveau(numNiveau,this); //fenêtre de transition entre niveaux (lancée à un autre moment si nous ne sommes pas dans cette boucle)
				}catch(IOException Exception){
				}
            }
            
            //réinitialisation des attributs d'affichage graphique + replacement du rover et des composants
			
			AffJeu.touchCarbu=false;
			AffJeu.affiche=true;
			Perseverance.origine = new APoint(0,833-100);
			Angle.setValue(45);
		    Vitesse.setValue(50);
		    aI = Angle.getValue();
			AngleI.setText("Angle initial : "+aI+" \u00b0");
			vI = Vitesse.getValue();
			VitesseI.setText("Vitesse initiale : "+vI+" km/h");
			toucheMeteorite=false;
			toucheTrouNoir = false;
			
		}
		
		//test afin de savoir si l'utilisateur a gagné ou perdu
		
		WinOrLose();
	}
	
	/**
   * \fn void WinOrLose() : méthode permettant de tester si l'utilisateur a terminé sa partie en gagnant ou perdant et d'agir en conséquence
   */
	public void WinOrLose(){
		if(numNiveau==6){//si l'utilisateur a réussi le niveau 5, il a gagné
			try{
				if(audioOn){
					fond.arret();
				}
				new FenetreWin(this); //création de la fenêtre de victoire
				setVisible(false);
			}catch(IOException Exception){
			}
				
		}else if((int)(pCarbu)<=0){ //si l'utilisateur n'a plus de carburant, il a perdu
			try{
				if(audioOn){
					fond.arret();
				}
				new FenetreLose(this); //création de la fenêtre game over
				setVisible(false);
			}catch(IOException Exception){
			}
		}
	}
   
   /**
   * \fn void WinOrLose() : méthode permettant de relancer le jeu et de recommencer au début
   */
	public void restart(){
		mt.stop();
		if(audioOn){
			fond.arret();
		}
		setVisible(false);
		new FenetreStart();
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
		

		
		

		
		
		
