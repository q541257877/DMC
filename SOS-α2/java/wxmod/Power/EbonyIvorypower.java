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
import wxmod.Card.Common.EbonyIvory;
import wxmod.Card.Special.Rainstorm;
import wxmod.Relic.SSS;

public class EbonyIvorypower extends AbstractPower
 {
  public static final String POWER_ID = "EbonyIvorypower";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  int power;
  int x = 0;
  private int number;
   
   public EbonyIvorypower(AbstractCreature owner, int amt) {
	   this.name = NAME;
	   this.ID = POWER_ID;
	   this.owner = owner;
	   this.amount = -1;
	   this.img = ImageMaster.loadImage("img/powers/Elucidator.png");
	   updateDescription();
	   loadRegion("EbonyIvorypower");
   }

  
   public void updateDescription() {
		 this.description = DESCRIPTIONS[0];}
 
   public void onRemove()
   {
	   if(AbstractDungeon.player.hasPower("Weaponreturnpower")) {
		   AbstractCard c = new EbonyIvory();                                                          
		   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c, 1, true, true));
	   }
	   else if(AbstractDungeon.player.hasPower("Weaponreturnpower2")) {
		   AbstractCard c = new EbonyIvory();                                                          
		   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c, 1, true, true));
	       c.upgrade();
           c.superFlash();
	   }
   }
   
   //风格系统触发效果（使用卡之后）
   public void onAfterUseCard(final AbstractCard card, final UseCardAction action) {
	   if(AbstractDungeon.player.hasPower("Gunslingerpower")){
		   this.amount = SSS.WeaponPonit;
	   }
	   else{
		   this.amount = 0;
	   }
	   if ((SSS.WeaponPonit >= 3)&&(x < 3)) {
		   if(AbstractDungeon.player.hasPower("Gunslingerpower")){
			   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Rainstorm(), 1));
			   flash();
			   x++;
			   SSS.WeaponPonit -= 3;
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
	   if ((SSS.WeaponPonit >= 3)&&(x < 3)) {
		   if(AbstractDungeon.player.hasPower("Gunslingerpower")){
			   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Rainstorm(), 1));
			   flash();
			   x++;
			   SSS.WeaponPonit -= 3;
			   this.amount = SSS.WeaponPonit;
		   }
	   }
   }
   
   //灾厄增加
   public void onUseCard(AbstractCard card, UseCardAction action) {
	   if ((!card.purgeOnUse) && (card.type == AbstractCard.CardType.ATTACK)) {
		   if((card.cardID == "Shoot") || (card.cardID == "Chargeshoot") || (card.cardID == "Doubleshoot")|| (card.cardID == "Rainstorm") ) {	 
			   this.number = ReturnRandomNumberAction.ReturnRandomNumber();
			   if(this.number <= 5) {
				   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Catastrophepower(AbstractDungeon.player,1), 1));	 
			   } 
		   }
       }
   }
   
   static {
	     powerStrings = CardCrawlGame.languagePack.getPowerStrings("EbonyIvorypower");
	     NAME = EbonyIvorypower.powerStrings.NAME;
	     DESCRIPTIONS = EbonyIvorypower.powerStrings.DESCRIPTIONS;
	 }
   
 }