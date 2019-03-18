package wxmod.Card.Common;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import wxmod.Card.Vfx.slashGold;
import wxmod.Patches.AbstractCardEnum;


public class VitalstarS extends CustomCard{
	public static final String ID = "VitalstarS";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Vitalstar.png";
	private static final int COST = 1;
	
	public VitalstarS() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.COMMON, 
				AbstractCard.CardTarget.SELF);
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.gold >=15) {
		p.gold -= 15;
		 for(int i = 0;i < 15; i++) {
			    AbstractDungeon.effectsQueue.add(new slashGold(p));
			    }
		 AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, 5));
		}
	}
	
	public AbstractCard makeCopy() {
		return new VitalstarS();
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(p.gold <15) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		}
		return canUse;
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
	        this.upgradeBaseCost(0);
		}
	}
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("VitalstarS");
        NAME = VitalstarS.cardStrings.NAME;
        DESCRIPTION = VitalstarS.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = VitalstarS.cardStrings.EXTENDED_DESCRIPTION;
    }
	

}