package wxmod.Card.Common;

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
import wxmod.Power.showtime;
import wxmod.Relic.SSS;

public class Dash2 extends CustomCard {
	public static final String ID = "Dash2";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String IMG_PATH = "img/cards/fire_ball.png";
	private static final int COST = 1;

	public Dash2() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.COMMON, 
				AbstractCard.CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = 1;
		this.baseBlock = 6;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p, this.magicNumber), this.magicNumber));	
			SSS.WeaponPonit +=this.magicNumber;
	}

	public AbstractCard makeCopy() {
		return new Dash2();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(3);
			this.upgradeMagicNumber(1);
		}
	}
	
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Dash2");
        NAME = Dash2.cardStrings.NAME;
        DESCRIPTION = Dash2.cardStrings.DESCRIPTION;
    }
}