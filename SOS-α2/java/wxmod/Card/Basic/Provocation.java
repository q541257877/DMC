package wxmod.Card.Basic;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import basemod.abstracts.CustomCard;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Power.showtime;
import wxmod.Relic.SSS;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;


public class Provocation extends CustomCard{
	public static final String ID = "Provocation";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String IMG_PATH = "img/cards/fire_ball.png";
	private static final int COST = 0;
	public static final int POOL = 0;	
	
	public Provocation() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.BASIC, 
				AbstractCard.CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = 2;
		
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p, this.magicNumber), this.magicNumber));	
		SSS.WeaponPonit +=this.magicNumber;
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
		AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(p, p, new VulnerablePower(p, 1, false), 1));
	}
	
	public AbstractCard makeCopy() {
		return new Provocation();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(1);
		}
	}
	
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Provocation");
        NAME = Provocation.cardStrings.NAME;
        DESCRIPTION = Provocation.cardStrings.DESCRIPTION;
    }

}