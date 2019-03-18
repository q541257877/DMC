package wxmod.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import wxmod.Card.Special.Release;
import wxmod.Card.Special.Royalrelease;
import wxmod.Relic.SSS;
 
public class Angrypower extends AbstractPower
 {
  public static final String POWER_ID = "Angrypower";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  private int power;

   
   public Angrypower(AbstractCreature owner, int amt)
   {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amt;
    this.img = ImageMaster.loadImage("img/powers/Exercise2.png");
   updateDescription();
   loadRegion("Angrypower");
 }

  
 public void updateDescription()
   {
     if (this.owner.isPlayer) {
      this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
    } else {
     this.description = (DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3]);
   }
  }
 
 public void stackPower(int stackAmount) {
	   if (this.amount == 0) {
     AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "Angrypower"));
   }
	   this.fontScale = 8.0F;
	   this.amount += stackAmount;
	   if (this.amount >= 9) {
     this.amount = 9;
   }
 }
 
 
 public void atStartOfTurn() {
	 AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.amount));
	 AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new showtime(AbstractDungeon.player, this.amount ), this.amount));
	 if(AbstractDungeon.player.hasPower("Angrypower")) {
			this.power = AbstractDungeon.player.getPower("Angrypower").amount;
			if ( this.power >= 3) {
				AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Release(), 1));
				flash();
			   }
			if(AbstractDungeon.player.hasPower("Royalguardpower2")) {
			if (this.power >= 6) {
				AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Royalrelease(), 1));
				flash();
			   }
           }
	      }
 
 
 }
 



 public void atEndOfTurn(boolean isPlayer) {
	 if(AbstractDungeon.player.hasPower("Skystarpower")) {}
     else {
    	 if (this.amount == 0) {
    		 AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Angrypower"));
    	 } 
    	 else {
    		 AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "Angrypower", 1));
    	 }
     }
	 SSS.WeaponPonit += this.amount;
   }
 
 static {
     powerStrings = CardCrawlGame.languagePack.getPowerStrings("Angrypower");
     NAME = Angrypower.powerStrings.NAME;
     DESCRIPTIONS = Angrypower.powerStrings.DESCRIPTIONS;
 }
 
  }