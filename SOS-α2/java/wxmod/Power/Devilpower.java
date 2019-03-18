package wxmod.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

import wxmod.WxMod;

public class Devilpower extends AbstractPower
 {
  public static final String POWER_ID = "Devilpower";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  int power;
  int x = 0;
  int y = 0;
  
   public Devilpower(AbstractCreature owner, int amt)
   {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amt;
    this.img = ImageMaster.loadImage("img/powers/VampireTeeth.png");
   updateDescription();
   loadRegion("Devilpower");
 }

  
   public void updateDescription() {
		 this.description = DESCRIPTIONS[0];}
 
   //回血
   public void onAttack(final DamageInfo info, final int damageAmount, final AbstractCreature target) {
		   y++;
		   if(y == 3) {
			   AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 3));
			   y = 0;
		   }
   }
   
   //减少力量
   public void onRemove()
   {
	   AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "Strength", 18));
	   if(!AbstractDungeon.player.hasPower("Flex1")) {
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
   
   
   
   //风格系统触发效果(使用卡之后)
   public void onAfterUseCard(final AbstractCard card, final UseCardAction action) {
	   if(AbstractDungeon.player.hasPower("showtime")) {
			this.power = (AbstractDungeon.player.getPower("showtime").amount);			
			if ((this.power >= 7)&&(x <= 4)) {
				AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 7), 7));
				AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Devilpower(AbstractDungeon.player, 1), 1));
				flash();
				x++;
				}
			   }
              }
             
   public void atEndOfRound() {
	   if (this.amount == 0) {
		   AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, "Devilpower"));
	   } else {
		   AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ReducePowerAction(this.owner, this.owner, "Devilpower", 1));
	    }
      }
   	
 //风格系统触发效果（回合开始）
public void atStartOfTurn() {
	x = 0;
	if(AbstractDungeon.player.hasPower("showtime")) {
		this.power = (AbstractDungeon.player.getPower("showtime").amount);
		if ((this.power >= 7)&&(x <= 4)) {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 7), 7));
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Devilpower(AbstractDungeon.player, 1), 1));
			flash();
			x++;
		    }
		   }
          }

static {
    powerStrings = CardCrawlGame.languagePack.getPowerStrings("Devilpower");
    NAME = Devilpower.powerStrings.NAME;
    DESCRIPTIONS = Devilpower.powerStrings.DESCRIPTIONS;
}

  }
 
 