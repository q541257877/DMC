package wxmod.Card.Rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import wxmod.Actions.RemoveRoyalguardpower2Action;
import wxmod.Actions.RemoveRoyalguardpowerAction;
import wxmod.Card.Special.Dreadnaught;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Power.Royalguardpower;
import wxmod.Power.Royalguardpower2;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;


public class Royalguard extends CustomCard{
	public static final String ID = "Royalguard";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Royalguard.png";
	private static final int COST = 2;

	
	public Royalguard() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.RARE, 
				AbstractCard.CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = 1;
		
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		
			
				if (this.upgraded) {
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Royalguardpower2(p,this.magicNumber), this.magicNumber));
					AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction(new Dreadnaught(), 1));
					AbstractDungeon.actionManager.addToBottom(new RemoveRoyalguardpowerAction(p, p));
				}else {			
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Royalguardpower(p,this.magicNumber), this.magicNumber));
				AbstractDungeon.actionManager.addToBottom(new RemoveRoyalguardpower2Action(p, p));
			}
		  }
	
	
	public AbstractCard makeCopy() {
		return new Royalguard();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(1);
        	this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
		}
	}
	
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Royalguard");
        NAME = Royalguard.cardStrings.NAME;
        DESCRIPTION = Royalguard.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = Royalguard.cardStrings.UPGRADE_DESCRIPTION;
    }
	

}