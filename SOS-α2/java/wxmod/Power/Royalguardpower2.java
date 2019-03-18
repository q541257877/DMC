package wxmod.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
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

public class Royalguardpower2 extends AbstractPower
 {
  public static final String POWER_ID = "Royalguardpower2";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  int power;
  private int number;
   
   public Royalguardpower2(AbstractCreature owner, int amt)
   {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amt;
    this.img = ImageMaster.loadImage("img/powers/Track.png");
   updateDescription();
   loadRegion("Royalguardpower2");
 }

  
   public void updateDescription() {
		 this.description = DESCRIPTIONS[0];}
   

  
   //受到攻击时免疫并给予怒气和能量
   public int onAttacked(DamageInfo info, int damageAmount){
       AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Angrypower(AbstractDungeon.player,2), 2));
       AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(2));
       this.number = ReturnRandomNumberAction.ReturnRandomNumber();
       if(this.number > 4  && info.owner != null && info.type != DamageType.HP_LOSS && info.type != DamageType.THORNS) {
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
	   else{
		   if (this.amount == 0) {
			   AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, "Royalguardpower2"));
		   } 
		   else {
			   AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ReducePowerAction(this.owner, this.owner, "Royalguardpower2", 1));
		   }
	   }
   }
   
   static {
       powerStrings = CardCrawlGame.languagePack.getPowerStrings("Royalguardpower2");
       NAME = Royalguardpower2.powerStrings.NAME;
       DESCRIPTIONS = Royalguardpower2.powerStrings.DESCRIPTIONS;
   }
   
 }