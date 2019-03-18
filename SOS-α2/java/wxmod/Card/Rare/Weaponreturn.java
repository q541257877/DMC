package wxmod.Card.Rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import wxmod.Actions.RemoveWeaponreturnpower2Action;
import wxmod.Actions.RemoveWeaponreturnpowerAction;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Power.Weaponreturnpower;
import wxmod.Power.Weaponreturnpower2;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;


public class Weaponreturn extends CustomCard{
	public static final String ID = "Weaponreturn";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/fire_ball.png";
	private static final int COST = 1;

	
	public Weaponreturn() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.RARE, 
				AbstractCard.CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = 1;
		this.exhaust = true;
		
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		
			
				if (this.upgraded) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Weaponreturnpower2(p,this.magicNumber), this.magicNumber));
				AbstractDungeon.actionManager.addToBottom(new RemoveWeaponreturnpowerAction(p, p));
				}else {			
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Weaponreturnpower(p,this.magicNumber), this.magicNumber));
				AbstractDungeon.actionManager.addToBottom(new RemoveWeaponreturnpower2Action(p, p));
			}
		  }
	
	
	public AbstractCard makeCopy() {
		return new Weaponreturn();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
        	this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
		}
	}
	
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Weaponreturn");
        NAME = Weaponreturn.cardStrings.NAME;
        DESCRIPTION = Weaponreturn.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = Weaponreturn.cardStrings.UPGRADE_DESCRIPTION;
    }
	

}