package wxmod.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Endlessswordpower2 extends AbstractPower
 {
  public static final String POWER_ID = "Endlessswordpower2";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  private AbstractCreature source;
   
   public Endlessswordpower2(AbstractCreature owner, int amt, AbstractCreature source) {
	   this.name = NAME;
	   this.ID = POWER_ID;
	   this.owner = owner;
	   this.amount = amt;
	   this.img = ImageMaster.loadImage("img/powers/Flash.png");
	   this.source = source;
	   this.type = AbstractPower.PowerType.DEBUFF;
	   updateDescription();
	   loadRegion("Endlessswordpower2");
   }

   public void updateDescription() {
	   if (this.owner.isPlayer) {
		      this.description = (DESCRIPTIONS[0] + this.amount*3 + DESCRIPTIONS[1]);
		    } else {
		     this.description = (DESCRIPTIONS[2] + this.amount*3 + DESCRIPTIONS[3]);
		   }
   }

   public void atStartOfTurn() {
	   if (AbstractDungeon.getCurrRoom().phase == com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase.COMBAT) {
		   if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
			   flashWithoutSound();
			   AbstractDungeon.actionManager.addToBottom(new PoisonLoseHpAction(this.owner, this.source, this.amount * 3, AttackEffect.SLASH_HORIZONTAL));
		   }   
	   }
	   if(AbstractDungeon.player.hasPower("Skystarpower")) {}
	   else { 
		   AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Endlessswordpower2"));
	   }
   }
   
   public void stackPower(int stackAmount) {
	   if (this.amount == 0) {
		   AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "Endlessswordpower2"));
	   }
	   this.fontScale = 8.0F;
	   this.amount += stackAmount;
	   if (this.amount >= 15) {
		   this.amount = 15;
	   }
   }
   
   static {
	     powerStrings = CardCrawlGame.languagePack.getPowerStrings("Endlessswordpower2");
	     NAME = Endlessswordpower2.powerStrings.NAME;
	     DESCRIPTIONS = Endlessswordpower2.powerStrings.DESCRIPTIONS;
	 }

  }
 
 