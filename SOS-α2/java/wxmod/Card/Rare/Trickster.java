package wxmod.Card.Rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import wxmod.Card.Special.Skystar;
import wxmod.Patches.AbstractCardEnum;


public class Trickster extends CustomCard{
	public static final String ID = "Trickster";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Trickster.png";
	private static final int COST = 2;

	
	public Trickster() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.RARE, 
				AbstractCard.CardTarget.SELF);

		
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new wxmod.Actions.TricksterAction());
	    AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction(new Skystar(), 1));
		  }
	
	
	public AbstractCard makeCopy() {
		return new Trickster();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(1);
		}
	}

	static {
		cardStrings = CardCrawlGame.languagePack.getCardStrings("Trickster");
		NAME = Trickster.cardStrings.NAME;
		DESCRIPTION = Trickster.cardStrings.DESCRIPTION;
	}
	

}