package wxmod.Card.Uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Power.Angrypower;

public class Royaldefend extends CustomCard {
	public static final String ID = "Royaldefend";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;	
	public static final String IMG_PATH = "img/cards/Royaldefend.png";
	private static final int COST = 1;

	public Royaldefend() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.UNCOMMON, 
				AbstractCard.CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = 3;
		this.baseBlock = 10;
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Angrypower(p, this.magicNumber), this.magicNumber));		
	}

	public AbstractCard makeCopy() {
		return new Royaldefend();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(5);
		}
	}	

    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Royaldefend");
    	NAME = Royaldefend.cardStrings.NAME;
    	DESCRIPTION = Royaldefend.cardStrings.DESCRIPTION;
    }
}