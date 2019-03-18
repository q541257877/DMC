package wxmod.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import wxmod.Actions.ReturnRandomNumberAction;
import wxmod.Card.Special.*;
import wxmod.Card.Uncommon.Pandora;
import wxmod.Relic.SSS;

public class Pandorapower extends AbstractPower
 {
  public static final String POWER_ID = "Pandorapower";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  int power;
  int x = 0;
  private int number;
   
   public Pandorapower(AbstractCreature owner, int amt)
   {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = -1;
    this.img = ImageMaster.loadImage("img/powers/Elucidator.png");
   updateDescription();
   loadRegion("Pandorapower");
 }

  
   public void updateDescription() {
		 this.description = DESCRIPTIONS[0];}
   
   public void onRemove()
   {
	   if(AbstractDungeon.player.hasPower("Weaponreturnpower")) {
		   AbstractCard c = new Pandora();                                                          
		   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c, 1, true, true));
	   }
	   else if(AbstractDungeon.player.hasPower("Weaponreturnpower2")) {
		   AbstractCard c = new Pandora();                                                          
		   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c, 1, true, true));
	       c.upgrade();
           c.superFlash();
	   }
   }
   
   //风格系统触发效果
   public void onAfterUseCard(final AbstractCard card, final UseCardAction action) {
	   if(AbstractDungeon.player.hasPower("Gunslingerpower")){
		   this.amount = SSS.WeaponPonit;
	   }
	   else{
		   this.amount = 0;
	   }
	   if ((SSS.WeaponPonit >= 4)&&(x < 3)) {
		   if(AbstractDungeon.player.hasPower("Gunslingerpower")){
			   this.number = ReturnRandomNumberAction.ReturnRandomNumber();
			   if (this.number <= 3.0D ) { 
				   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new PF666(), 1));
				   flash();
			   }
			   else if(this.number <= 6.0D ) {
				   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new PF398(), 1));
				   flash(); 
			   }
			   else if(this.number > 6.0D ) {
				   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new PF594(), 1));
				   flash();
			   }  
			   x++;
			   SSS.WeaponPonit -= 4;
			   this.amount = SSS.WeaponPonit;
		   }
	   }
   }
   
   //风格系统触发效果（回合开始）
   public void atStartOfTurn() {
	   x = 0;
	   if(AbstractDungeon.player.hasPower("Gunslingerpower")){
		   this.amount = SSS.WeaponPonit;
	   }
	   else{
		   this.amount = 0;
	   }
	   if (ReturnRandomNumberAction.ReturnRandomNumber() <= 5.0D ) { 
		   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new PF013(), 1));
		   flash();
	   }
	   else {
		   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new PF262(), 1));
	   }
	   if ((SSS.WeaponPonit >= 4)&&(x < 3)) {
		   if(AbstractDungeon.player.hasPower("Gunslingerpower")){
			   this.number = ReturnRandomNumberAction.ReturnRandomNumber();
			   if (this.number <= 3.0D ) { 
				   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new PF666(), 1));
				   flash();
			   }
			   else if(this.number <= 6.0D ) {
				   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new PF398(), 1));
				   flash(); 
			   }
			   else if(this.number > 6.0D ) {
				   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new PF594(), 1));
				   flash();
			   }  
			   x++;
			   SSS.WeaponPonit -= 4;
			   this.amount = SSS.WeaponPonit;
		   }
	   }
   }
   
   //灾厄增加
   public void onUseCard(AbstractCard card, UseCardAction action)
   {
     if ((!card.purgeOnUse) && (card.type == AbstractCard.CardType.ATTACK)) {
    	 if((card.cardID == "Shoot") || (card.cardID == "Chargeshoot") || (card.cardID == "Doubleshoot") || (card.cardID == "PF013") || (card.cardID == "PF262") || (card.cardID == "PF398") || (card.cardID == "PF666") || (card.cardID == "PF594") || (card.cardID == "Rainstorm") || (card.cardID == "Chargeshoot2")|| (card.cardID == "Gunstinger") || (card.cardID == "Fireworks")) {
    		 this.number = ReturnRandomNumberAction.ReturnRandomNumber();
    		 if(this.number <= 8) {
    		 AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Catastrophepower(AbstractDungeon.player,1), 1));
    		  }
    		 } 
    	   }
    	 }
   
   static {
       powerStrings = CardCrawlGame.languagePack.getPowerStrings("Pandorapower");
       NAME = Pandorapower.powerStrings.NAME;
       DESCRIPTIONS = Pandorapower.powerStrings.DESCRIPTIONS;
   }
   
	   	   
   }
  	
  