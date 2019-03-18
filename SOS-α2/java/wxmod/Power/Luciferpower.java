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

import wxmod.Card.Special.PINUP;
import wxmod.Card.Uncommon.Lucifer;
import wxmod.Relic.SSS;


public class Luciferpower extends AbstractPower
 {
  public static final String POWER_ID = "Luciferpower";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  int x = 0;
   
   public Luciferpower(AbstractCreature owner, int amt)
   {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = -1;
    this.img = ImageMaster.loadImage("img/powers/DarkRepulser.png");
   updateDescription();
   loadRegion("Luciferpower");
 }

  
   public void updateDescription() {
		 this.description = DESCRIPTIONS[0];}
   
   public void onRemove()
   {
	   if(AbstractDungeon.player.hasPower("Weaponreturnpower")) {
		   AbstractCard c = new Lucifer();                                                          
		   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c, 1, true, true));
	   }
	   else if(AbstractDungeon.player.hasPower("Weaponreturnpower2")) {
		   AbstractCard c = new Lucifer();                                                          
		   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c, 1, true, true));
	       c.upgrade();
           c.superFlash();
	   }
   }
   
   //相关卡牌获得表演时间
   public void onUseCard(AbstractCard card, UseCardAction action) {
	   if ((!card.purgeOnUse)) {
		   if((card.cardID == "LucifercomboA") || (card.cardID == "LucifercomboB") || (card.cardID == "LucifercomboC") || (card.cardID == "LucifercomboD") || (card.cardID == "Ecstasy") || (card.cardID == "Splesh") || (card.cardID == "PINUP") || (card.cardID == "Climax")) {
			   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new showtime(AbstractDungeon.player,1), 1));
		   }
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
	   if ((SSS.WeaponPonit >= 4)&&(x < 3)) {
		   if(AbstractDungeon.player.hasPower("Swordmasterpower")){
			   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new PINUP(), 1));
			   flash();
			   x++;
			   SSS.WeaponPonit -= 4;
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
	   if ((SSS.WeaponPonit >= 4)&&(x < 3)) {
		   if(AbstractDungeon.player.hasPower("Swordmasterpower")){
			   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new PINUP(), 1));
			   flash();
			   x++;
			   SSS.WeaponPonit -= 4;
			   this.amount = SSS.WeaponPonit;
		   }
	   }
   }
   
   static {
       powerStrings = CardCrawlGame.languagePack.getPowerStrings("Luciferpower");
       NAME = Luciferpower.powerStrings.NAME;
       DESCRIPTIONS = Luciferpower.powerStrings.DESCRIPTIONS;
   }

  
 }