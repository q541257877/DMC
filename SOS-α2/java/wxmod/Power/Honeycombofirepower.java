 package wxmod.Power;
 import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
 import com.megacrit.cardcrawl.actions.utility.UseCardAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;

import wxmod.Actions.ReturnRandomNumberAction;
 
 public class Honeycombofirepower extends AbstractPower
 {
   public static final String POWER_ID = "Honeycombofirepower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   private int number;
   
   public Honeycombofirepower(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = -1;
     this.img = ImageMaster.loadImage("img/powers/DoubleSword.png");
     updateDescription();
   }
   
   public void updateDescription() {
		 this.description = DESCRIPTIONS[0];}
   
   public void onUseCard(AbstractCard card, UseCardAction action)
   {
     if ((!card.purgeOnUse) && (card.type == AbstractCard.CardType.ATTACK)) {
    	 if((card.cardID == "Shoot") || (card.cardID == "Chargeshoot") || (card.cardID == "Doubleshoot") || (card.cardID == "PF013") || (card.cardID == "PF262") || (card.cardID == "PF398") || (card.cardID == "PF666") || (card.cardID == "PF594") || (card.cardID == "Rainstorm") || (card.cardID == "Chargeshoot2")|| (card.cardID == "Gunstinger") || (card.cardID == "Fireworks")) {
    		 if(AbstractDungeon.player.hasPower("EbonyIvorypower")) {
    			 this.number = 5;
    		 }
    		 else {
    			 this.number = 2;
    		 }
    		 if(ReturnRandomNumberAction.ReturnRandomNumber() <= this.number && ReturnRandomNumberAction.ReturnRandomNumber() != 0) {
       flash();
       AbstractMonster m = null;
       
       if (action.target != null) {
         m = (AbstractMonster)action.target;
       }
       
       AbstractCard tmp = card.makeStatEquivalentCopy();
       AbstractDungeon.player.limbo.addToBottom(tmp);
       tmp.current_x = card.current_x;
       tmp.current_y = card.current_y;
       tmp.target_x = (Settings.WIDTH / 2.0F - 300.0F * Settings.scale);
       tmp.target_y = (Settings.HEIGHT / 2.0F);
       tmp.freeToPlayOnce = true;
       
       if (m != null) {
       tmp.calculateCardDamage(m);
       }
       tmp.purgeOnUse = true;
       AbstractDungeon.actionManager.cardQueue.add(new com.megacrit.cardcrawl.cards.CardQueueItem(tmp, m, card.energyOnUse));
     }
    	 }
     }
   }
   
   public void atEndOfTurn(boolean isPlayer)
   {
	   if(AbstractDungeon.player.hasPower("Skystarpower")) {}
	   else {
		   if (isPlayer) {
       AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Honeycombofirepower"));
           }
       }
   }
   
   static {
       powerStrings = CardCrawlGame.languagePack.getPowerStrings("Honeycombofirepower");
       NAME = Honeycombofirepower.powerStrings.NAME;
       DESCRIPTIONS = Honeycombofirepower.powerStrings.DESCRIPTIONS;
   }
   
 }


