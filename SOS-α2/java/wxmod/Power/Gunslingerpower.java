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
import wxmod.Card.Basic.*;
import wxmod.Card.Common.*;
import wxmod.Card.Uncommon.Gunstinger;
import wxmod.Relic.SSS;


public class Gunslingerpower extends AbstractPower
 {
  public static final String POWER_ID = "Gunslingerpower";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  int power;
   
   public Gunslingerpower(AbstractCreature owner, int amt)
   {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = -1;
    this.img = ImageMaster.loadImage("img/powers/Elucidator.png");
   updateDescription();
   loadRegion("Gunslingerpower");
   updatePowerInHand();
 }

  
   public void updateDescription() {
		 this.description = DESCRIPTIONS[0];}
   
   public void onUseCard(AbstractCard card, UseCardAction action) {
	   if ((!card.purgeOnUse) && (card.type == AbstractCard.CardType.ATTACK)) {
		   if((card.cardID == "Shoot") || (card.cardID == "Chargeshoot") || (card.cardID == "Doubleshoot") || (card.cardID == "PF013") || (card.cardID == "PF262") || (card.cardID == "PF398") || (card.cardID == "PF666") || (card.cardID == "PF594") || (card.cardID == "Rainstorm") || (card.cardID == "Chargeshoot2")|| (card.cardID == "Gunstinger") || (card.cardID == "Fireworks")) {
			   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new showtime(AbstractDungeon.player,1), 1));
			   SSS.WeaponPonit += 1;
			   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Catastrophepower(AbstractDungeon.player,1), 1));	 
		   } 
	   }
   }
       
 

   //枪械系卡牌耗能变0
   public void stackPower()
   {
     this.fontScale = 8.0F;
     updatePowerInHand();
   }
   
   private void updatePowerInHand() {
     for (AbstractCard c : AbstractDungeon.player.hand.group) {
       if ((c instanceof Shoot)||(c instanceof Chargeshoot)||(c instanceof Doubleshoot)||(c instanceof Chargeshoot2)||(c instanceof Gunstinger)) {
    	   c.setCostForTurn(-9);
         }
       }
     }
   
   public void onDrawOrDiscard() {
	   for (AbstractCard c : AbstractDungeon.player.hand.group) {
		   if ((c instanceof Shoot)||(c instanceof Chargeshoot)||(c instanceof Doubleshoot)||(c instanceof Chargeshoot2)||(c instanceof Gunstinger)) {
			   c.setCostForTurn(-9);
		   }
	   }
   }
   
   public void atEndOfTurn(boolean isPlayer) {
	   if(AbstractDungeon.player.hasPower("Skystarpower")) {}
	   else { 
		   if (isPlayer) {
			   AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Gunslingerpower"));
		   }
	   }
   }
   
   static {
       powerStrings = CardCrawlGame.languagePack.getPowerStrings("Gunslingerpower");
       NAME = Gunslingerpower.powerStrings.NAME;
       DESCRIPTIONS = Gunslingerpower.powerStrings.DESCRIPTIONS;
   }
   
   
 }