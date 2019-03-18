package wxmod.Card.Rare;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import wxmod.Card.Special.Yamato;
import wxmod.Patches.AbstractCardEnum;


public class Darkslayer extends CustomCard{
	public static final String ID = "Darkslayer";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Darkslayer.png";
	private static final int COST = 2;

	
	public Darkslayer() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.RARE, 
				AbstractCard.CardTarget.SELF);
		this.exhaust = true;
		
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractCard c = new Yamato();                                                          
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
        c.setCostForTurn(0);
	}
	
	
	public AbstractCard makeCopy() {
		return new Darkslayer();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(1);
			this.exhaust = false;
        	this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
		}
	}
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Darkslayer");
        NAME = Darkslayer.cardStrings.NAME;
        DESCRIPTION = Darkslayer.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = Darkslayer.cardStrings.UPGRADE_DESCRIPTION;
    }
	

}