package wxmod.Card.Common;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import wxmod.Actions.ArmamentsAction;
import wxmod.Card.Vfx.slashGold;
import wxmod.Patches.AbstractCardEnum;


public class Weaponupgrade extends CustomCard{
	public static final String ID = "Weaponupgrade";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/fire_ball.png";
	private static final int COST = 1;
	
	public Weaponupgrade() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.COMMON, 
				AbstractCard.CardTarget.SELF);	
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.gold >=10) {
		p.gold -= 10;
		 for(int i = 0;i < 10; i++) {
			    AbstractDungeon.effectsQueue.add(new slashGold(p));
			    }
		AbstractDungeon.actionManager.addToBottom(new ArmamentsAction(false));
		}
	}
	
	public AbstractCard makeCopy() {
		return new Weaponupgrade();
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(p.gold <10) {
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
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Weaponupgrade");
        NAME = Weaponupgrade.cardStrings.NAME;
        DESCRIPTION = Weaponupgrade.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Weaponupgrade.cardStrings.EXTENDED_DESCRIPTION;
    }
	

}