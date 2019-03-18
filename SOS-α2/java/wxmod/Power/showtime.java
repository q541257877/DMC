package wxmod.Power;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
 
public class showtime extends AbstractPower
 {
  public static final String POWER_ID = "showtime";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;

   
   public showtime(AbstractCreature owner, int amt)
   {
    this.name = NAME;
    this.ID = "showtime";
    this.owner = owner;
    this.amount = amt;
    this.img = ImageMaster.loadImage("img/powers/BrokenRibs.png");
   updateDescription();
   loadRegion("showtime");
 }

   public void stackPower(int stackAmount) {
	   if (this.amount == 0) {
       AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "showtime"));
     }
	   this.fontScale = 8.0F;
	   this.amount += stackAmount;
	   if (this.amount >= 7) {
       this.amount = 7;
     }

    }
   
 public void updateDescription()
   {
     if (this.owner.isPlayer) {
      this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
    } else {
     this.description = (DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3]);
   }
  }
 
   public int onAttacked(DamageInfo info, int damageAmount)
  {
    if ((info.owner != null) && (info.type != DamageInfo.DamageType.HP_LOSS) && (info.type != DamageInfo.DamageType.THORNS) && (damageAmount > 0))
    {
      AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ReducePowerAction(this.owner, this.owner, "showtime", 1));
    }
     return damageAmount;
  }
 


public void atEndOfTurn(boolean isPlayer)
{
 flash();
 AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.GainBlockAction(this.owner, this.owner, this.amount));
 if(AbstractDungeon.player.hasPower("Skystarpower")) {}
 else {
 AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "showtime"));}
}

static {
    powerStrings = CardCrawlGame.languagePack.getPowerStrings("showtime");
    NAME = showtime.powerStrings.NAME;
    DESCRIPTIONS = showtime.powerStrings.DESCRIPTIONS;
}

 }