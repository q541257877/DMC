package wxmod.Card.Rare;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.SeekAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import wxmod.Card.Uncommon.Gunslinger;
import wxmod.Card.Uncommon.Swordmaster;
import wxmod.Patches.AbstractCardEnum;


public class Stylechange extends CustomCard{
	public static final String ID = "Stylechange";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Stylechange.png";
	private static final int COST = 2;



	
	public Stylechange() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.RARE, 
				AbstractCard.CardTarget.SELF);
		
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		int x=1+(int)(Math.random()*5);
	switch(x){
	case 1:
		AbstractCard c = new Swordmaster();
		c.setCostForTurn(-9);
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
		break;
	case 2:
		AbstractCard c1 = new Gunslinger();
		c1.setCostForTurn(-9);
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c1, 1));
		break;
	case 3:
		AbstractCard c2 = new Royalguard(); 
		c2.setCostForTurn(-9);
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c2, 1));
		break;
	case 4:
		AbstractCard c3 = new Trickster();  
		c3.setCostForTurn(-9);
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c3, 1));
		break;
	case 5:
		AbstractCard c4 = new Darkslayer();  
		c4.setCostForTurn(-9);
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c4, 1));
		break;
	  }
	if(upgraded) {
		AbstractDungeon.actionManager.addToBottom(new SeekAction(1));
	 }
	}
	
	


	public AbstractCard makeCopy() {
		return new Stylechange();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
			this.upgradeBaseCost(1);
			this.isInnate = true;
		}
	}
	
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Stylechange");
        NAME = Stylechange.cardStrings.NAME;
        DESCRIPTION = Stylechange.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = Stylechange.cardStrings.UPGRADE_DESCRIPTION;
    }
	

}