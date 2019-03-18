package wxmod.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
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
import wxmod.Actions.ReturnRandomNumberAction;
import wxmod.Card.Special.*;
import wxmod.Relic.SSS;

public class Yamatopower extends AbstractPower
 {
  public static final String POWER_ID = "Yamatopower";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  int power;
  int x = 0;
  private int number;
   
   public Yamatopower(AbstractCreature owner, int amt)
   {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = -1;
    this.img = ImageMaster.loadImage("img/powers/SwordSkillSystem.png");
   updateDescription();
   loadRegion("Yamatopower");
 }

  
   public void updateDescription() {
		 this.description = DESCRIPTIONS[0];
   }
  
   public void onRemove() {
	   if(!this.owner.hasPower("Devilpower")&&!this.owner.hasPower("Flex1")) {
		   if((AbstractDungeon.player.hasPower("Rebellionpower"))||(AbstractDungeon.player.hasPower("Rebellionpower2"))) {
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
   
   public void atStartOfTurn() {
	   //x = 0;
	   //if (ReturnRandomNumberAction.ReturnRandomNumber() <= 5.0D ) { 
		//AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction(new Slashdimension1(), 1));
		//flash();
		 //}else {AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction(new Slashdimension2(), 1));}
	   //if(AbstractDungeon.player.hasPower("showtime")) {
			//this.power = (AbstractDungeon.player.getPower("showtime").amount);
			//if ((this.power >= 4)&&(x < 2)) {
						//this.number = ReturnRandomNumberAction.ReturnRandomNumber();
					//if (this.number <= 5.0D ) { 
				      //AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction(new Slashdimension1(), 1));
				      //flash();
				  //}
					//else {
					  //AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction(new Slashdimension2(), 1));
					  //flash();
				  //}
				//x++;}
			  //}
	    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Sheathing(), 1));
	    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Yamatopower"));
   }
       


   
   //风格系统触发效果
   public void onAfterUseCard(final AbstractCard card, final UseCardAction action) {
	   if(AbstractDungeon.player.hasPower("Swordmasterpower")){
		   this.amount = SSS.WeaponPonit;
	   }
	   else{
		   this.amount = 0;
	   }
	   if ((SSS.WeaponPonit >= 4)&&(x < 2)) {
		   this.number = ReturnRandomNumberAction.ReturnRandomNumber();
		   if (this.number <= 5.0D ) { 
			   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Slashdimension1(), 1));
			   flash();
		   }
		   else {
			   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Slashdimension2(), 1));
			   flash();
		   }  
		   x++;
		   SSS.WeaponPonit -= 4;
		   this.amount = SSS.WeaponPonit;
	   }
   }
   
   static {
       powerStrings = CardCrawlGame.languagePack.getPowerStrings("Yamatopower");
       NAME = Yamatopower.powerStrings.NAME;
       DESCRIPTIONS = Yamatopower.powerStrings.DESCRIPTIONS;
   }
   
  }	
  