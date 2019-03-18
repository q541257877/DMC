package wxmod.Card.Basic;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Power.Redorbpower;

public class Redorb extends CustomCard{
	
	public static final String ID = "Redorb";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Redorb.png";
	private static final int COST = 1;

	
	
	public Redorb() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DMC,
        		AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.ENEMY);
		this.exhaust = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new Redorbpower(m,1), 1));	
    	}
	
	
	@Override
    public AbstractCard makeCopy() {
        return new Redorb();
    }
    
    public void upgrade() {
    	
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
            this.exhaust = false;
        	this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
            this.isInnate = true;
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Redorb");
        NAME = Redorb.cardStrings.NAME;
        DESCRIPTION = Redorb.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = Redorb.cardStrings.UPGRADE_DESCRIPTION;
    }
	
}
