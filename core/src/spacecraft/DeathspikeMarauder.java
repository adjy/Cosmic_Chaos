package spacecraft;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import screen.AllAssets;
import weapon.AlienWeapons.SingleRocket;
import weapon.SkyBladeWeapons.RocketCyclone;

public class DeathspikeMarauder extends Alien {
    private static final int DEFAULT_MAX_LIFE = 100 ;

    private static final String DEFAULT_NAME = "Death Spike Marauder" ;
    public DeathspikeMarauder(SpriteBatch batch, AllAssets assets) {
        super(DEFAULT_NAME, DEFAULT_MAX_LIFE, batch,assets.getDeathSpikeMarauder());
       setWeapon(new SingleRocket(batch,this, assets));
    }
}
