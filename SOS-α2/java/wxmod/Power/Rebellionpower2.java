package wxmod.Power;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import wxmod.Card.Common.Millionstab;
import wxmod.Card.Common.Rebellion;
import wxmod.Card.Special.Rebellionsword;
import wxmod.Relic.SSS;

public class Rebellionpower2 extends AbstractPower
 {
  public static final String POWER_ID = "Rebellionpower2";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  int x = 0;
   
   public Rebellionpower2(AbstractCreature owner, int amt)
   {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = -1;
    this.img = ImageMaster.loadImage("img/powers/DarkRepulser.png");
   updateDescription();
   loadRegion("Rebellionpower2");
   updateMillionstabInHand();
 }

  
   public void updateDescription() {
		 this.description = DESCRIPTIONS[0];}
   
   public void onRemove()
   {
	   AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "Strength", 2));
	   if(AbstractDungeon.player.hasPower("Weaponreturnpower")) {
		   AbstractCard c = new Rebellion();                                                          
		   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c, 1, true, true));
	   }
	   else if(AbstractDungeon.player.hasPower("Weaponreturnpower2")) {
		   AbstractCard c = new Rebellion();                                                          
		   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c, 1, true, true));
	       c.upgrade();
           c.superFlash();
	   }
	   
   }
   
   //风格系统触发效果（使用卡之后）
   public void onAfterUseCard(final AbstractCard card, final UseCardAction action) {
	   if(AbstractDungeon.player.hasPower("Swordmasterpower")){
		   this.amount = SSS.WeaponPonit;
	   }
	   else{
		   this.amount = 0;
	   }
	   if ((SSS.WeaponPonit >= 3)&&(x < 3)) {
		   if(AbstractDungeon.player.hasPower("Swordmasterpower")){
			   AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction(new Rebellionsword(), 1));
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
	   if(AbstractDungeon.player.hasPower("Swordmasterpower")){
		   this.amount = SSS.WeaponPonit;
	   }
	   else{
		   this.amount = 0;
	   }
	   if ((SSS.WeaponPonit >= 3)&&(x < 3)) {
		   if(AbstractDungeon.player.hasPower("Swordmasterpower")){
			   AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction(new Rebellionsword(), 1));
			   flash();
			   x++;
			   SSS.WeaponPonit -= 3;
			   this.amount = SSS.WeaponPonit;
		   }
	   }
   }
   
   
   
   //百烈刺基础伤害+2
   public void stackPower()
   {
     this.fontScale = 8.0F;
     updateMillionstabInHand();
   }
   
   private void updateMillionstabInHand() {
     for (AbstractCard c : com.megacrit.cardcrawl.dungeons.AbstractDungeon.player.hand.group) {
       if ((c instanceof Millionstab)) {
    	   c.baseDamage = (3 + 2);
         }
       }
     }
   
   public void onDrawOrDiscard()
   {
     for (AbstractCard c : com.megacrit.cardcrawl.dungeons.AbstractDungeon.player.hand.group) {
       if ((c instanceof Millionstab)) {
           c.baseDamage = (3 + 2);
       }
     }
   }
   
   static {
       powerStrings = CardCrawlGame.languagePack.getPowerStrings("Rebellionpower2");
       NAME = Rebellionpower2.powerStrings.NAME;
       DESCRIPTIONS = Rebellionpower2.powerStrings.DESCRIPTIONS;
   }
 }