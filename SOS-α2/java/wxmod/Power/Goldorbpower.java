package wxmod.Power;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Goldorbpower extends AbstractPower
 {
  public static final String POWER_ID = "Goldorbpower";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;

   
   public Goldorbpower(AbstractCreature owner, int amt)
   {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = -1;
    this.img = ImageMaster.loadImage("img/powers/WartimeReply.png");
   updateDescription();
   loadRegion("Goldorbpower");
 }

  
   public void updateDescription() {
		 this.description = DESCRIPTIONS[0];}
 
   public void onTrigger() {
		int healAmt = AbstractDungeon.player.maxHealth / 2;
		if (healAmt < 1) {
			healAmt = 1;
		}
		AbstractDungeon.player.heal(healAmt, true);
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Goldorbpower"));
   }
  
public int onAttacked(DamageInfo info, int damageAmount)
{  if(damageAmount > AbstractDungeon.player.currentHealth) {
   flash();
   AbstractDungeon.player.heal(AbstractDungeon.player.maxHealth / 2, true);
   AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Goldorbpower"));
	 return 0;
  }
	 return damageAmount;
 }
   
  // public int onLoseHp(final int damageAmount) {
  //	   if(damageAmount > AbstractDungeon.player.currentHealth) {
  //		     flash();
  //		     AbstractDungeon.player.heal(AbstractDungeon.player.maxHealth / 2, true);
  //	     AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Goldorbpower"));
  //		 return 0;
  //		    }
  //     return damageAmount;
  // }
   
   static {
       powerStrings = CardCrawlGame.languagePack.getPowerStrings("Goldorbpower");
       NAME = Goldorbpower.powerStrings.NAME;
       DESCRIPTIONS = Goldorbpower.powerStrings.DESCRIPTIONS;
   }
   
  
  }
 
 