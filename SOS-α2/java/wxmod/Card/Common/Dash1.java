package wxmod.Card.Common;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import wxmod.Patches.AbstractCardEnum;


public class Dash1 extends CustomCard{
	public static final String ID = "Dash1";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/fire_ball.png";
	private static final int COST = 1;

	
	public Dash1() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.COMMON, 
				AbstractCard.CardTarget.SELF);
		
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		int theSize = p.hand.size();
		AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, theSize, false));
		if (this.upgraded) {		
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, theSize));	
		}
		else{
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, theSize -1));
		}  
	}
	
	
	public AbstractCard makeCopy() {
		return new Dash1();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
        	this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
		}
	}
	
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Dash1");
        NAME = Dash1.cardStrings.NAME;
        DESCRIPTION = Dash1.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = Dash1.cardStrings.UPGRADE_DESCRIPTION;
    }

	
}