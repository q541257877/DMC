package wxmod.Card.Common;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import basemod.helpers.TooltipInfo;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Power.showtime;
import wxmod.Relic.SSS;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.AttackFromDeckToHandAction;


public class Hightime extends CustomCard{
	public static final String ID = "Hightime";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Hightime.png";
	private static final int COST = 1;
	private List<TooltipInfo> tips;
	
	public Hightime() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.COMMON, 
				AbstractCard.CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = 1;
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
  }
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new AttackFromDeckToHandAction(1));
		if(p.hasPower("Rebellionpower")||p.hasPower("Rebellionpower2")) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p, 1), 1));		
			SSS.WeaponPonit +=1;
		}
	}
   
   public boolean canUse(AbstractPlayer p, AbstractMonster m)
   {
     boolean canUse = super.canUse(p, m);
      if (!canUse) {return false;}    
       boolean hasAttack = false;
     for (AbstractCard c : p.drawPile.group) {
       if (c.type == AbstractCard.CardType.ATTACK) {
        hasAttack = true;
     }
    }
    
    if (!hasAttack) {
      this.cantUseMessage = EXTENDED_DESCRIPTION[2];
      canUse = false;
      }
     return canUse;   
     }
	
	public AbstractCard makeCopy() {
		return new Hightime();
	 }
	
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
    }
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(0);
		}
	}	
	
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Hightime");
        NAME = Hightime.cardStrings.NAME;
        DESCRIPTION = Hightime.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Hightime.cardStrings.EXTENDED_DESCRIPTION;
    }
}