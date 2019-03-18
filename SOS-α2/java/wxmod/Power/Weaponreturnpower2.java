package wxmod.Power;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Weaponreturnpower2 extends AbstractPower
 {
  public static final String POWER_ID = "Weaponreturnpower2";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  int power;
  int x = 0;
   
   public Weaponreturnpower2(AbstractCreature owner, int amt)
   {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = -1;
    this.img = ImageMaster.loadImage("img/powers/Track.png");
   updateDescription();
   loadRegion("Weaponreturnpower2");
 }

  
   public void updateDescription() {
		 this.description = DESCRIPTIONS[0];}
   
   static {
       powerStrings = CardCrawlGame.languagePack.getPowerStrings("Weaponreturnpower2");
       NAME = Weaponreturnpower2.powerStrings.NAME;
       DESCRIPTIONS = Weaponreturnpower2.powerStrings.DESCRIPTIONS;
   }
   
 
   
  
  }
 
 