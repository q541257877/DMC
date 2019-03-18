package wxmod.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import wxmod.WxMod;
import wxmod.Actions.ReturnRandomNumberAction;

public class Royalguardpower extends AbstractPower
 {
  public static final String POWER_ID = "Royalguardpower";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  int power;
  private int number;
   
   public Royalguardpower(AbstractCreature owner, int amt)
   {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amt;
    this.img = ImageMaster.loadImage("img/powers/Track.png");
   updateDescription();
   loadRegion("Royalguardpower");
 }

  
   public void updateDescription() {
		 this.description = DESCRIPTIONS[0];}
   
  
   //受到攻击时免疫并给予怒气
   public int onAttacked(DamageInfo info, int damageAmount){     
       AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Angrypower(AbstractDungeon.player,1), 1));
       this.number = ReturnRandomNumberAction.ReturnRandomNumber();
       if(this.number > 5 && info.owner != null && info.type != DamageType.HP_LOSS && info.type != DamageType.THORNS) {
    	   if(!AbstractDungeon.player.hasPower("Devilpower")&&!AbstractDungeon.player.hasPower("Flex1")) {
    		   AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_RB.png"));
    	   } 
    	   else {
    		   AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_DTRB.png"));
    	   }
    	   return 0;
       }
       else {
    	   if (info.owner != null && info.type != DamageType.HP_LOSS && info.type != DamageType.THORNS && damageAmount < 1) {
    		   if(!AbstractDungeon.player.hasPower("Devilpower")&&!AbstractDungeon.player.hasPower("Flex1")) {
				AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_B.png"));
				return damageAmount;
    		   } 
    		   else {
    			   AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_DTB.png"));
    			   return damageAmount;
    		   }
    	   }
       }
	return damageAmount;
   }
 
   public void atStartOfTurn() {
	   if(AbstractDungeon.player.hasPower("Skystarpower")) {}
	   else { 
		   if (this.amount == 0) {
			   AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Royalguardpower"));
		   } 
		   else {
			   AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "Royalguardpower", 1));
		   }
	   }
   }
   
   static {
       powerStrings = CardCrawlGame.languagePack.getPowerStrings("Royalguardpower");
       NAME = Royalguardpower.powerStrings.NAME;
       DESCRIPTIONS = Royalguardpower.powerStrings.DESCRIPTIONS;
   }
   
 }