 package wxmod.Power;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

import wxmod.WxMod;
 
 public class Flex1 extends AbstractPower
 {
   public static final String POWER_ID = "Flex1";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   
   public Flex1(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     this.img = ImageMaster.loadImage("img/powers/Exercise1.png");
     updateDescription();
   }
   
   public void updateDescription() {
	   this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
		 }
   
   public void onUseCard(AbstractCard card, UseCardAction action)
   {
     if ((!card.purgeOnUse) && (card.type == AbstractCard.CardType.ATTACK) && (this.amount > 0)) {
    	 AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "Strength", this.amount));
         AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, "Flex1"));
         if(!AbstractDungeon.player.hasPower("Devilpower")) {
         if(AbstractDungeon.player.hasPower("Yamatopower")) {
		   AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new InflameEffect(AbstractDungeon.player), 1.0F));
		   AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_Y.png"));
	        }
         else if((AbstractDungeon.player.hasPower("Rebellionpower"))||(AbstractDungeon.player.hasPower("Rebellionpower2"))) {
		   AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new InflameEffect(AbstractDungeon.player), 1.0F));
		   AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_R.png"));
			}
         else if(AbstractDungeon.player.hasPower("Gilgameshpower")) {
		   AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new InflameEffect(AbstractDungeon.player), 1.0F));
		   AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_G.png"));
		    }
         else if((AbstractDungeon.player.hasPower("Luciferpower"))||(AbstractDungeon.player.hasPower("Luciferpower2"))) {
		   AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new InflameEffect(AbstractDungeon.player), 1.0F));
		   AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_L.png"));	
		}
         else if((AbstractDungeon.player.hasPower("Pandorapower"))||(AbstractDungeon.player.hasPower("Pandorapower2"))) {
		   AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new InflameEffect(AbstractDungeon.player), 1.0F));
		   AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_P.png"));	
		}
         else if(AbstractDungeon.player.hasPower("CoyoteApower")) {
		   AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new InflameEffect(AbstractDungeon.player), 1.0F));
		   AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_C.png"));	
	    }
         else if(AbstractDungeon.player.hasPower("EbonyIvorypower")) {
		   AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new InflameEffect(AbstractDungeon.player), 1.0F));
		   AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_E&I.png"));	
	    }
	     else{
		   AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_md.png"));  
	   }	
     }
   }
  }
   
   static {
	     powerStrings = CardCrawlGame.languagePack.getPowerStrings("Flex1");
	     NAME = Flex1.powerStrings.NAME;
	     DESCRIPTIONS = Flex1.powerStrings.DESCRIPTIONS;
	 }
   
 }

