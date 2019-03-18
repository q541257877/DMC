package wxmod.Card.Uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import wxmod.Actions.GetPowerAmtAction;
import wxmod.Actions.RemoveEndlessswordpower2Action;
import wxmod.Patches.AbstractCardEnum;

public class Ecstasy extends CustomCard{
	
	public static final String ID = "Ecstasy";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Ecstasy.png";
	private static final int COST = 2;
	private static final int ATTACK_DMG = 4;
	private int power;

	
	
	public Ecstasy() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DMC,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
		this.baseMagicNumber = this.magicNumber = 0;
		this.baseDamage = ATTACK_DMG;
	}

	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(m.hasPower("Endlessswordpower2")&&m.getPower("Endlessswordpower2").amount>=1) {		
			this.power = m.getPower("Endlessswordpower2").amount;
			AbstractDungeon.actionManager.addToBottom(new RemoveEndlessswordpower2Action(m, p));
		}
		for(int i = 0;i < this.power; i++) {
			AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage,true), DamageInfo.DamageType.NORMAL, AttackEffect.SMASH));
		}
		
	}	



public AbstractCard makeCopy() {
    return new Ecstasy();
}

public boolean canUse(AbstractPlayer p, AbstractMonster m) {
	boolean canUse = super.canUse(p, m);
	if(!canUse) return false;
	if(GetPowerAmtAction.PowerAmt(m, "Endlessswordpower2") <1) {
		canUse = false;
		this.cantUseMessage = EXTENDED_DESCRIPTION[0];
	}
	return canUse;
}

public void upgrade() {
    if (!this.upgraded) {
        this.upgradeName();
        this.upgradeBaseCost(1);
  }
 }

static {
	cardStrings = CardCrawlGame.languagePack.getCardStrings("Ecstasy");
    NAME = Ecstasy.cardStrings.NAME;
    DESCRIPTION = Ecstasy.cardStrings.DESCRIPTION;
    EXTENDED_DESCRIPTION = Ecstasy.cardStrings.EXTENDED_DESCRIPTION;
}

}
