package wxmod.Card.Rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Power.showtime;
import wxmod.Relic.SSS;


public class Airtrick extends CustomCard{
	public static final String ID = "Airtrick";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Airtrick.png";
	private static final int COST = 0;

	
	public Airtrick() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.RARE, 
				AbstractCard.CardTarget.SELF);
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		int theSize = p.hand.size();
		AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DiscardAction(p, p, theSize, false));
		if (this.upgraded) {		
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p, theSize + 1), theSize + 1));	
			SSS.WeaponPonit += theSize + 1;
		}
		else{
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p, theSize), theSize));
			SSS.WeaponPonit += theSize;
		}  
	}
	
	
	public AbstractCard makeCopy() {
		return new Airtrick();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
        	this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
		}
	}
	
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Airtrick");
        NAME = Airtrick.cardStrings.NAME;
        DESCRIPTION = Airtrick.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = Airtrick.cardStrings.UPGRADE_DESCRIPTION;
    }
	

}