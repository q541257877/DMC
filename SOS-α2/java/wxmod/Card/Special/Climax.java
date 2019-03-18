package wxmod.Card.Special;

import com.megacrit.cardcrawl.actions.unique.SwordBoomerangAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import wxmod.Actions.GetPowerAmtAction;
import wxmod.Actions.RemoveEndlessswordpowerAction;

public class Climax extends CustomCard{
	
	public static final String ID = "Climax";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Climax.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;
	private int power;

	
	
	public Climax() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.SELF);
		this.exhaust = true;
		this.baseMagicNumber = 0;
		this.baseDamage = ATTACK_DMG;
		this.isEthereal = true;
	}

	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.hasPower("Endlessswordpower")) {this.power = p.getPower("Endlessswordpower").amount;
		AbstractDungeon.actionManager.addToBottom(new RemoveEndlessswordpowerAction(p, p));
		}
		AbstractDungeon.actionManager.addToBottom(new SwordBoomerangAction(AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(p, this.damage), this.power));
		}	



public AbstractCard makeCopy() {
    return new Climax();
}

public boolean canUse(AbstractPlayer p, AbstractMonster m) {
	boolean canUse = super.canUse(p, m);
	if(!canUse) return false;
	if(GetPowerAmtAction.PowerAmt(p, "Endlessswordpower") <1) {
		canUse = false;
		this.cantUseMessage = EXTENDED_DESCRIPTION[0];
	}
	return canUse;
}


public void upgrade() {
    if (!this.upgraded) {
        this.upgradeName();
  }
 }	

static {
	cardStrings = CardCrawlGame.languagePack.getCardStrings("Climax");
    NAME = Climax.cardStrings.NAME;
    DESCRIPTION = Climax.cardStrings.DESCRIPTION;
    EXTENDED_DESCRIPTION = Climax.cardStrings.EXTENDED_DESCRIPTION;
}	
}
