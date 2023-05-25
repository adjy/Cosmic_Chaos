package spacecraft;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gift.BonusScore;
import gift.Gift;
import weapon.SkyBladeWeapons.PredatorFury;
import weapon.Weapon;
import weapon.ammo.Ammo;
import com.badlogic.gdx.Gdx;

import java.util.HashSet;

import gift.Shield;

public class Skyblade extends Spacecraft {
    private static final int DEFAULT_MAX_PUISSANCE = 50 ;
    private static final int DEFAULT_PUISSANCE = 50 ;
    private static final String DEFAULT_PICTURE ="pictures/ships/skyblade.png";
    private static final String DEFAULT_NAME = "captain";

   /* public HashSet<Ammo> Ammos ;*/
    public Shield shield;//bouclier du Capitaine.

    private boolean Protected;//si le vaisseau du Héro possède un bouclier !

    public BonusScore bonusScore;
    private PredatorFury predatorFury; // Missiles tete chercheuses

    public Shield getShield() {
        return shield;
    }


    public void setShield(Shield shield) {
        this.shield = shield;
    }

    public void protect(boolean value){ this.Protected = value;}

    public PredatorFury getPredatorFury() {
        return predatorFury;
    }

    public void setPredatorFury(PredatorFury predatorFury) {
        this.predatorFury = predatorFury;
    }

    public Skyblade(String name, SpriteBatch batch){
        super(name,DEFAULT_PICTURE, batch);
       // Ammos=new HashSet<>();
        setPuissance(DEFAULT_PUISSANCE);
        setMaxPuissance( DEFAULT_MAX_PUISSANCE );
        setPosX(0);
        setPosY(0);

        protect(false);
        setShield(new Shield(this, getPosX(), getPosY(), getBatch()));
        setPredatorFury(new PredatorFury(batch, this));

    }

    public Skyblade(SpriteBatch batch){
       this(DEFAULT_NAME, batch);
    }

    @Override
    public void move(Spacecraft spacecraft) {

        getBatch().begin();

        getBatch().draw(getPicture(), getPosX(), getPosY());
        if(isProtected())
            getBatch().draw(getShield().getShieldPicture(), getPosX()- 25, getPosY() );

        getBatch().end();




        float positionX = Gdx.input.getX() - ((float) getPicture().getWidth() /2);

        if(positionX < 0)
            setPosX(0);

        else if(positionX > Gdx.graphics.getWidth() - getPicture().getWidth())
            setPosX(Gdx.graphics.getWidth() - getPicture().getWidth());
        else
            setPosX(positionX);

        float positionY = Gdx.graphics.getHeight() - Gdx.input.getY() - ((float) getPicture().getHeight() /2); // Calculate paddle y position based on mouse position

        if(positionY < 0)
            setPosY(0);
        else if(positionY > Gdx.graphics.getHeight() - getPicture().getHeight())
            setPosY(Gdx.graphics.getHeight() - getPicture().getHeight());
        else
            setPosY(positionY);

    }

    public boolean isProtected(){
        return this.Protected;
    }

    public void equipShield(){//pour renforcer le vaisseau
        this.Protected = true;
    }

    public void shotBy(Ammo ammo) {// qund il a ete tire
       if(isProtected()){
           shield.touched();//on décrémente la charge du bouclier
       }
       else {
           setPuissance(getPuissance() - ammo.getDegats());
       }
    }




}
