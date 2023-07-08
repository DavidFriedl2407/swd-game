package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy implements GameObject {

    private List<PositionObserver> observers;
    private final Texture image; // Die Textur des Spielers.
    private final Sprite sprite; // Der Sprite des Spielers.
    private final String imagePath;
    private float x; // X-Koordinate des Spielers
    private float y; // Y-Koordinate des Spielers
    private final float speed; // Geschwindigkeit des Spielers

    private float elapsedTime; // Gesamte vergangene Zeit
    private float changeMovementTime; // Zeitpunkt, an dem die Bewegung geändert wird
    private boolean isAlternateMovement; // Gibt an, ob die alternative Bewegung aktiv ist
    private int LiveEnemy;

    public Enemy(String imagePath) {
        this.imagePath = imagePath;
        this.image = AssetRepository.getInstance().getTexture(imagePath);
        this.sprite = new Sprite(image);
        this.x = 0;
        this.y = 0;
        this.speed = 40;
        this.elapsedTime=0;
        this.changeMovementTime =5; // Ändere die Bewegung nach 5 Sekunden
        this.isAlternateMovement=false;

    }

    public List<PositionObserver> getObservers() {
        return observers;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    int velX = 2;
    int velY = 2;

    @Override
    public void act(float delta) {
        // Zähle die vergangene Zeit hoch
        elapsedTime +=delta;
        isAlternateMovement=false;
        if (elapsedTime>=changeMovementTime&&elapsedTime<=changeMovementTime+1 && !isAlternateMovement){
            // Wenn die Zeit abgelaufen ist und die alternative Bewegung nicht aktiv ist,
            // ändere die Bewegung und aktiviere die alternative Bewegung
            isAlternateMovement=true;
            changeMovement();
        }

        if (!isAlternateMovement){
            // Normale Bewegungsmethode
            y += velY;
            x += velX;

            if (y <= 105 || y >= 385) velY *= -1;
            if (x <= 85 || x >= 375) velX *= -1;

            setPosition(x, y);
        }
        else {
            // Alternative Bewegungsmethode
            alternateMovement();
        }

    }
    public void changeMovement(){
        // Hier kannst du die Bewegungsparameter ändern, um die alternative Bewegung zu definieren
        // Beispiel: Ändere die Geschwindigkeit
        velX=3;
        velY=3;
    }
    public void alternateMovement(){
        //alternatives Bewegungsmuster
        x+=2*Math.sin(y*0.1); // Verändere die X-Koordinate basierend auf der Y-Koordinate



        if (y <= 105 || y >= 385) y *= -1;
        if (x <= 85 || x >= 375) x *= -1;
        setPosition(x,y);




    }

    @Override
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        sprite.setPosition(x, y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

}
