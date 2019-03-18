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
 
 public class Swordmasterpower extends AbstractPower
 {
   public static final String POWER_ID = "Swordmasterpower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   
   public Swordmasterpower(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     this.img = ImageMaster.loadImage("img/powers/DoubleSword.png");
     updateDescription();
   }
   
   public void updateDescription() {
		 this.description = DESCRIPTIONS[0];}
   
   public void onUseCard(AbstractCard card, UseCardAction action) {
     if ((!card.purgeOnUse) && (card.type == AbstractCard.CardType.ATTACK) && (this.amount > 0)) {
    	 if((card.cardID == "Stinger") || (card.cardID == "Millionstab") || (card.cardID == "Rebellionsword") || (card.cardID == "Swordcombo") || (card.cardID == "LucifercomboA") || (card.cardID == "LucifercomboB") || (card.cardID == "LucifercomboC") || (card.cardID == "LucifercomboD") || (card.cardID == "Splesh")) {
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
    		 this.amount -= 1;
    		 if (this.amount == 0) {
    			 AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Swordmasterpower"));
    		 }
    	 }
    	 if((card.cardID == "Attack_a") || (card.cardID == "Attack_b")) {
    		 if(!AbstractDungeon.player.hasPower("Gilgameshpower")&&!AbstractDungeon.player.hasPower("Yamatopower")) {
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
    	       this.amount -= 1;
    	       if (this.amount == 0) {
    	         AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Swordmasterpower"));
    	       }
    	     }
    	 }
     }
   }
   
   public void atEndOfTurn(boolean isPlayer)
   {if(AbstractDungeon.player.hasPower("Skystarpower")) {}
   else{if (isPlayer) {
       AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Swordmasterpower"));
     }
   }
   }
   
   static {
       powerStrings = CardCrawlGame.languagePack.getPowerStrings("Swordmasterpower");
       NAME = Swordmasterpower.powerStrings.NAME;
       DESCRIPTIONS = Swordmasterpower.powerStrings.DESCRIPTIONS;
   }
   
 }


