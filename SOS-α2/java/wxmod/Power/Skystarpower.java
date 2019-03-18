package wxmod.Power;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Skystarpower extends AbstractPower
 {
  public static final String POWER_ID = "Skystarpower";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  int power;
  int x = 0;
   
   public Skystarpower(AbstractCreature owner, int amt)
   {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amt;
    this.img = ImageMaster.loadImage("img/powers/WartimeReply.png");
   updateDescription();
   loadRegion("Skystarpower");
 }

  
   public void updateDescription() {
		 this.description = DESCRIPTIONS[0];}
 
   
   //风格系统触发效果(使用卡之后)
   public void onAfterUseCard(final AbstractCard card, final UseCardAction action) {
	  if(AbstractDungeon.player.hasPower("showtime")) {
			this.power = (AbstractDungeon.player.getPower("showtime").amount);
			if ((this.power >= 7)&&(x < 3)) {
				AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Skystarpower(AbstractDungeon.player, 1), 1));
				flash();
				x++;
				}
			   }
              }
             
   public void atEndOfRound() {
	   if (this.amount == 0) {
		   AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, "Skystarpower"));
	   } else {
		   flash();
		   AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ReducePowerAction(this.owner, this.owner, "Skystarpower", 1));
	    }
      }
   	
 //风格系统触发效果（回合开始）
public void atStartOfTurn() {
	x = 0;
	if(AbstractDungeon.player.hasPower("showtime")) {
		this.power = (AbstractDungeon.player.getPower("showtime").amount);
		if ((this.power >= 7)&&(x < 3)) {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Skystarpower(AbstractDungeon.player, 1), 1));
			flash();
			x++;
			}
		   }
          }

static {
    powerStrings = CardCrawlGame.languagePack.getPowerStrings("Skystarpower");
    NAME = Skystarpower.powerStrings.NAME;
    DESCRIPTIONS = Skystarpower.powerStrings.DESCRIPTIONS;
}
  }
 
 