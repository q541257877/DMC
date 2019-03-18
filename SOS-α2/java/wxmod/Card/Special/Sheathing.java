package wxmod.Card.Special;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import wxmod.Actions.RemoveDimensionpowerAction;
import wxmod.Power.showtime;
import wxmod.Relic.SSS;

public class Sheathing extends CustomCard{
	
	public static final String ID = "Sheathing";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Sheathing.png";
	private static final int COST = 0;

	
	
	public Sheathing() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ENEMY);
		this.exhaust = true;
		this.baseDamage = 3;
	}

	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(m.hasPower("Dimensionpower")) {
		this.baseDamage = m.getPower("Dimensionpower").amount;
		this.damage = this.baseDamage * 3;
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage,true), DamageInfo.DamageType.HP_LOSS, AttackEffect.SLASH_DIAGONAL));
		AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(m.getPower("Dimensionpower").amount / 3));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p,m.getPower("Dimensionpower").amount / 3 ), m.getPower("Dimensionpower").amount / 3 ));
		SSS.WeaponPonit += m.getPower("Dimensionpower").amount / 3;
		AbstractDungeon.actionManager.addToBottom(new RemoveDimensionpowerAction(m, p));
		}
		}	



public AbstractCard makeCopy() {
    return new Sheathing();
}


public void upgrade() {
    if (!this.upgraded) {
        this.upgradeName();
  }
 }	

static {
	cardStrings = CardCrawlGame.languagePack.getCardStrings("Sheathing");
	NAME = Sheathing.cardStrings.NAME;
	DESCRIPTION = Sheathing.cardStrings.DESCRIPTION;
}
}
