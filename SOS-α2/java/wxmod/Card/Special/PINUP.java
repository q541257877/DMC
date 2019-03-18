package wxmod.Card.Special;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import wxmod.Power.Endlessswordpower;

public class PINUP extends CustomCard{
	
	public static final String ID = "PINUP";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String IMG_PATH = "img/cards/PINUP.png";
	private static final int COST = 0;

	
	
	public PINUP() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.SELF);
		this.exhaust = true;
		this.baseMagicNumber = 0;
		this.isEthereal = true;
	}

	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.hasPower("showtime")) {this.baseMagicNumber = this.magicNumber = p.getPower("showtime").amount * 3 ;}
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Endlessswordpower(p, this.magicNumber), this.magicNumber));
		}	



public AbstractCard makeCopy() {
    return new PINUP();
}


public void upgrade() {
    if (!this.upgraded) {
        this.upgradeName();
  }
 }	

static {
	cardStrings = CardCrawlGame.languagePack.getCardStrings("PINUP");
	NAME = PINUP.cardStrings.NAME;
	DESCRIPTION = PINUP.cardStrings.DESCRIPTION;
}
}
