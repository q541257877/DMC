package wxmod.Power;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Endlessswordpower extends AbstractPower
 {
  public static final String POWER_ID = "Endlessswordpower";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  int power;
   
   public Endlessswordpower(AbstractCreature owner, int amt) {
	   this.name = NAME;
	   this.ID = POWER_ID;
	   this.owner = owner;
	   this.amount = amt;
	   this.img = ImageMaster.loadImage("img/powers/Flash.png"); 
	   updateDescription();
	   loadRegion("Endlessswordpower");
   }

  
   public void updateDescription() {
	   this.description = (DESCRIPTIONS[0] + this.amount*2 + DESCRIPTIONS[1]);
   }
 
   public int onAttacked(DamageInfo info, int damageAmount) {
	   if ((info.type != DamageInfo.DamageType.THORNS) && (info.type != DamageInfo.DamageType.HP_LOSS) && (info.owner != null) && (info.owner != this.owner)) {
		   flash();
		   AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.DamageAction(info.owner, new DamageInfo(null, this.amount * 2, DamageInfo.DamageType.THORNS), com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
	   }
	   return damageAmount;
   }
   
   public void stackPower(int stackAmount) {
	   if (this.amount == 0) {
       AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "Endlessswordpower"));
     }
	   this.fontScale = 8.0F;
	   this.amount += stackAmount;
	   if (this.amount >= 15) {
       this.amount = 15;
      }
     }
   
   static {
	     powerStrings = CardCrawlGame.languagePack.getPowerStrings("Endlessswordpower");
	     NAME = Endlessswordpower.powerStrings.NAME;
	     DESCRIPTIONS = Endlessswordpower.powerStrings.DESCRIPTIONS;
	 }
   
    }
   

  
 
 