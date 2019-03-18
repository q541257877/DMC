package wxmod.Card.Special;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import basemod.helpers.TooltipInfo;
import wxmod.Actions.GetPowerAmtAction;
import wxmod.Actions.RemoveAngrypowerAction;

public class Royalrelease extends CustomCard{
	
	public static final String ID = "Royalrelease";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Royalrelease.png";
	private static final int COST = 2;
	private static final int ATTACK_DMG = 0;
	private int power;
	private List<TooltipInfo> tips;
	
	public Royalrelease() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCard.CardColor.COLORLESS,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ENEMY);
		this.exhaust = true;
		this.baseDamage = ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = 5;
		this.isEthereal = true;
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
	}

	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(AbstractDungeon.player.hasPower("Angrypower")) {
			this.power = (AbstractDungeon.player.getPower("Angrypower").amount);
			if (this.power >= 6) {
				this.damage = (int) (m.maxHealth * 0.5);
				AbstractDungeon.actionManager.addToBottom(new RemoveAngrypowerAction(p, p));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.POISON));
				}else{
					AbstractDungeon.actionManager.addToBottom(new RemoveAngrypowerAction(p, p));
					this.damage = p.currentBlock * 5;
					AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
					  }
	 	      }
		else{
		AbstractDungeon.actionManager.addToBottom(new RemoveAngrypowerAction(p, p));
		this.damage = p.currentBlock * 5;
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		  }
		}	
		


public AbstractCard makeCopy() {
    return new Royalrelease();
}

public boolean canUse(AbstractPlayer p, AbstractMonster m) {
	boolean canUse = super.canUse(p, m);
	if(!canUse) return false;
	if(GetPowerAmtAction.PowerAmt(p, "Angrypower") <1) {
		canUse = false;
		this.cantUseMessage = EXTENDED_DESCRIPTION[2];
	}
	return canUse;
}

public List<TooltipInfo> getCustomTooltips() {
	return this.tips;
}

public void upgrade() {
    if (!this.upgraded) {
        this.upgradeName();
  }
 }	

static {
	cardStrings = CardCrawlGame.languagePack.getCardStrings("Royalrelease");
	NAME = Royalrelease.cardStrings.NAME;
	DESCRIPTION = Royalrelease.cardStrings.DESCRIPTION;
	EXTENDED_DESCRIPTION = Royalrelease.cardStrings.EXTENDED_DESCRIPTION;
}
}
