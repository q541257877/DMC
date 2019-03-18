package wxmod.Power;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class Greenorbpower extends AbstractPower
 {
  public static final String POWER_ID = "Greenorbpower";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  private int number;

   
   public Greenorbpower(AbstractCreature owner, int amt)
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
	this.number = (int)Math.round(Math.random()*(10-1)+1);
	AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, AbstractDungeon.player, this.number));
   }
   
   static {
       powerStrings = CardCrawlGame.languagePack.getPowerStrings("Greenorbpower");
       NAME = Greenorbpower.powerStrings.NAME;
       DESCRIPTIONS = Greenorbpower.powerStrings.DESCRIPTIONS;
   }
   
}
   
   
  
 
 