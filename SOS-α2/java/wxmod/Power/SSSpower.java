package wxmod.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import wxmod.WxMod;
import wxmod.Actions.RemoveFlex2Action;

public class SSSpower extends AbstractPower
 {
  public static final String POWER_ID = "SSSpower";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  int x = 0;
  int power;
  boolean a = true;
   
   public SSSpower(AbstractCreature owner, int amt)
   {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amt;
    this.img = ImageMaster.loadImage("img/powers/Mark.png");
    updateDescription();
    loadRegion("SSSpower");
 }

  
   public void updateDescription()
   {
	   if (this.owner.isPlayer) {
		      this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
		    } else {
		     this.description = (DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3]);
		   }
  }

   

   public void onAfterUseCard(final AbstractCard card, final UseCardAction action) {
	  if(AbstractDungeon.player.hasPower("showtime")) {
			this.power = (AbstractDungeon.player.getPower("showtime").amount);
			if (this.power >= 7) {
				AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(4));
				flash();
				//获得4点能量
				AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 5));
				flash();
				//摸5张牌
				AbstractDungeon.actionManager.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 6));
				flash();
				//获得6点格挡
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "showtime"));
				//清零showtime层数
				x++;
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SSSpower(AbstractDungeon.player, 1), 1));
				if(this.owner.hasPower("Swordmasterpower")){
					AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "flex2"));
					AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Flex"));
				}
		}
	}
}	

 public void atStartOfTurn() 
 { this.a = true;
   x = 0;
 if(AbstractDungeon.player.hasPower("showtime")) {
		this.power = (AbstractDungeon.player.getPower("showtime").amount);
		if (this.power >= 7){
			flash();
			//获得4点能量
			AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(4));
			//摸5张牌
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 5));
			//获得6点格挡
			AbstractDungeon.actionManager.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 6));
			//清零showtime层数
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "showtime"));
			x++;
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SSSpower(AbstractDungeon.player, 1), 1));
			//清零临时力量层数
			if(AbstractDungeon.player.hasPower("Swordmasterpower")) {
				AbstractDungeon.actionManager.addToBottom(new RemoveFlex2Action(AbstractDungeon.player, AbstractDungeon.player));
			}
			if(this.owner.hasPower("Swordmasterpower")){
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "flex2"));
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Flex"));
			}
		}
 }
 }
 
 
 public int onLoseHp(final int damageAmount) {
     if (damageAmount > 0)
    {
      this.a = false;     
    }
     return damageAmount;
  }
 
 public void atEndOfRound() {  
	    if(this.a == true){
	    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new showtime(AbstractDungeon.player,2),2));
	   flash();
	   
   } 
	    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SSSpower(AbstractDungeon.player, -this.amount), -this.amount));
		if(AbstractDungeon.player.hasPower("Devilpower")||AbstractDungeon.player.hasPower("Flex1")) {
			AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_DT.png"));
		}
		else if(AbstractDungeon.player.hasPower("Yamatopower")) {
			AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_Y.png"));
		}
		else if((AbstractDungeon.player.hasPower("Rebellionpower"))||(AbstractDungeon.player.hasPower("Rebellionpower2"))) {
			AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_R.png"));
		}
		else if(AbstractDungeon.player.hasPower("Gilgameshpower")) {
			AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_G.png"));
		}
		else if((AbstractDungeon.player.hasPower("Luciferpower"))||(AbstractDungeon.player.hasPower("Luciferpower2"))) {
			AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_L.png"));	
		}
		else if((AbstractDungeon.player.hasPower("Pandorapower"))||(AbstractDungeon.player.hasPower("Pandorapower2"))) {
			AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_P.png"));	
		}
		else if(AbstractDungeon.player.hasPower("CoyoteApower")) {
			AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_C.png"));	
		}
		else if(AbstractDungeon.player.hasPower("EbonyIvorypower")) {
			AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_E&I.png"));	
		}
		else{
			AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_md.png"));  
		}
 }
 
 static {
     powerStrings = CardCrawlGame.languagePack.getPowerStrings("SSSpower");
     NAME = SSSpower.powerStrings.NAME;
     DESCRIPTIONS = SSSpower.powerStrings.DESCRIPTIONS;
 }
 }
