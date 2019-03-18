package wxmod.Card.Special;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import wxmod.Power.Skystarpower;

public class Skystar extends CustomCard{
	
	public static final String ID = "Skystar";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Skystar.png";
	private static final int COST = 0;

	
	
	public Skystar() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.SELF);
		this.exhaust = true;
	}

	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Skystarpower(p,1), 1));
		}	



public AbstractCard makeCopy() {
    return new Skystar();
}


public void upgrade() {
    if (!this.upgraded) {
        this.upgradeName();
  }
 }	

static {
	cardStrings = CardCrawlGame.languagePack.getCardStrings("Skystar");
	NAME = Skystar.cardStrings.NAME;
	DESCRIPTION = Skystar.cardStrings.DESCRIPTION;
}
}
