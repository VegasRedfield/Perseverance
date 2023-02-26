import java.applet.*; //AudioClip
import java.net.*; //URL
 
/**
 * \class Audio : classe créant les pistes audio
 */
public class Audio extends Thread{
	
	//<!url du fichier audio
    private URL url;
    
    //<!localisation du fichier audio sur l'ordinateur
    private AudioClip sound;
	
	/**
   * \fn Audio(String song) : constructeur Audio
   * 
   * @param String song : chemin du fichier audio sur l'orinateur
   */
    public Audio(String song){
		
        url = this.getClass().getClassLoader().getResource(song);
        sound = Applet.newAudioClip(url);
        
    }
    
    /**
   * \fn void lecture() : méthode permettant de lire le son associé une fois
   */ 
    public void lecture(){ //joue le son
		sound.play();
	}
	
	/**
   * \fn void arret() : méthode permettant d'arrêter la lecture du son associé
   */ 
	public void arret(){ //arrête le son
		sound.stop();
	}
	
	/**
   * \fn void enBoucle() : méthode permettant de lire le son associé en boucle
   */ 
	public void enBoucle(){ //joue le son en boucle
		sound.loop();
	}
}
