package wxmod.Card.Common;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import basemod.abstracts.CustomCard;
import basemod.helpers.TooltipInfo;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Power.Flex2;
import wxmod.Power.showtime;
import wxmod.Relic.SSS;

public class Straight extends CustomCard{
	
	public static final String ID = "Straight";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION ;
	public static final String IMG_PATH = "img/cards/Straight.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;
	private List<TooltipInfo> tips;

	
	
	public Straight() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DMC,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
		this.baseMagicNumber = this.magicNumber = 1;
		this.baseDamage = ATTACK_DMG;
		this.baseBlock = 6;
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
	}

	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.hasPower("Gilgameshpower")) {
			this.damage = this.damage * 2;
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Flex2(p, 1), 1));	
		}
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		if (this.upgraded) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p,this.magicNumber), this.magicNumber));	
			SSS.WeaponPonit +=this.magicNumber;
		}
	}	



public AbstractCard makeCopy() {
    return new Straight();
}

public List<TooltipInfo> getCustomTooltips() {
	return this.tips;
}


public void upgrade() {
    if (!this.upgraded) {
        this.upgradeName();
    	this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
  }
 }

static {
	cardStrings = CardCrawlGame.languagePack.getCardStrings("Straight");
    NAME = Straight.cardStrings.NAME;
    DESCRIPTION = Straight.cardStrings.DESCRIPTION;
    UPGRADE_DESCRIPTION = Straight.cardStrings.UPGRADE_DESCRIPTION;
    EXTENDED_DESCRIPTION = Straight.cardStrings.EXTENDED_DESCRIPTION;
}
}
