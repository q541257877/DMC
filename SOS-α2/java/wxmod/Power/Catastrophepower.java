package wxmod.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import wxmod.Actions.ReturnRandomNumberAction;
import wxmod.Relic.SSS;

public class Catastrophepower extends AbstractPower
 {
  public static final String POWER_ID = "Catastrophepower";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  int power;
private int number;
   
   public Catastrophepower(AbstractCreature owner, int amt)
   {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amt;
    this.img = ImageMaster.loadImage("img/powers/Stick.png");
   updateDescription();
   loadRegion("Catastrophepower");
 }
   
   public void onUseCard(AbstractCard card, UseCardAction action)
   {
     if ((!card.purgeOnUse) && (card.type == AbstractCard.CardType.ATTACK)) {
    	 if((card.cardID == "Shoot") || (card.cardID == "Chargeshoot") || (card.cardID == "Doubleshoot") || (card.cardID == "PF013") || (card.cardID == "PF262") || (card.cardID == "PF398") || (card.cardID == "PF666") || (card.cardID == "PF594") || (card.cardID == "Rainstorm") || (card.cardID == "Chargeshoot2")|| (card.cardID == "Gunstinger") || (card.cardID == "Fireworks")) {	 
    		 this.number = ReturnRandomNumberAction.ReturnRandomNumber();
    		 if(this.amount >= this.number) {
    			 AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new showtime(AbstractDungeon.player,1), 1));
    			 SSS.WeaponPonit += 1;
    		 } 
    	  }
    	 }
       }
   
   

   public void stackPower(int stackAmount) {
	   if (this.amount == 0) {
       AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "Catastrophepower"));
     }
	   this.fontScale = 8.0F;
	   this.amount += stackAmount;
	   if (this.amount >= 9) {
       this.amount = 9;
     }
   }
   
  
   public void updateDescription() {
		 this.description = DESCRIPTIONS[0];}
   
   static {
       powerStrings = CardCrawlGame.languagePack.getPowerStrings("Catastrophepower");
       NAME = Catastrophepower.powerStrings.NAME;
       DESCRIPTIONS = Catastrophepower.powerStrings.DESCRIPTIONS;
   }
 
   
 }
 
 