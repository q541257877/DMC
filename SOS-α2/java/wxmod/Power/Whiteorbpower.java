package wxmod.Power;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class Whiteorbpower extends AbstractPower
 {
  public static final String POWER_ID = "Whiteorbpower";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  private int number;

   
   public Whiteorbpower(AbstractCreature owner, int amt)
   {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = -1;
    this.img = ImageMaster.loadImage("img/powers/Mark.png");
   updateDescription();
   loadRegion("Whiteorbpower");
 }

  
   public void updateDescription() {
		 this.description = DESCRIPTIONS[0];}
 
   
   public void onDeath()
   {
	this.number = (int)Math.round(Math.random()*(3-1)+1);
	AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.number));
   }
   
   static {
       powerStrings = CardCrawlGame.languagePack.getPowerStrings("Whiteorbpower");
       NAME = Whiteorbpower.powerStrings.NAME;
       DESCRIPTIONS = Whiteorbpower.powerStrings.DESCRIPTIONS;
   }
}
   
   
  
 
 