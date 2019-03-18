package wxmod.Power;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Dimensionpower extends AbstractPower
 {
  public static final String POWER_ID = "Dimensionpower";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  int power;
   
   public Dimensionpower(AbstractCreature owner, int amt)
   {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amt;
    this.img = ImageMaster.loadImage("img/powers/Stick.png");
   updateDescription();
   loadRegion("Dimensionpower");
 }

  
   public void updateDescription() {
		 this.description = DESCRIPTIONS[0];}
 
   
             
   public void atEndOfRound() {
	   if(AbstractDungeon.player.hasPower("Skystarpower")) {}
	   else{if (this.amount == 0) {
		   AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, "Dimensionpower"));
	   } else {
		   AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ReducePowerAction(this.owner, this.owner, "Dimensionpower", 1));
	    }
      }
   }
   
   static {
	     powerStrings = CardCrawlGame.languagePack.getPowerStrings("Dimensionpower");
	     NAME = Dimensionpower.powerStrings.NAME;
	     DESCRIPTIONS = Dimensionpower.powerStrings.DESCRIPTIONS;
	 }
   	
  }
 
 