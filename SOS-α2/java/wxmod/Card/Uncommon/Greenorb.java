package wxmod.Card.Uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Power.Greenorbpower;

public class Greenorb extends CustomCard{
	
	public static final String ID = "Greenorb";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Greenorb.png";
	private static final int COST = 0;

	
	
	public Greenorb() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DMC,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
		this.exhaust = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new Greenorbpower(m,1), 1));	
    	}
	
	
	@Override
    public AbstractCard makeCopy() {
        return new Greenorb();
    }
    
    public void upgrade() {
    	
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false;
			this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Greenorb");
        NAME = Greenorb.cardStrings.NAME;
        DESCRIPTION = Greenorb.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = Greenorb.cardStrings.UPGRADE_DESCRIPTION;
    }
	
}
