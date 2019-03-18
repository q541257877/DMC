package wxmod.Card.Common;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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

public class BeastUppercut extends CustomCard{
	
	public static final String ID = "BeastUppercut";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION ;
	public static final String IMG_PATH = "img/cards/BeastUppercut.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 9;
	private List<TooltipInfo> tips;

	
	
	public BeastUppercut() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DMC,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
		this.baseMagicNumber = this.magicNumber = 1;
		this.baseDamage = ATTACK_DMG;
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
	}

	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.hasPower("Gilgameshpower")) {
			this.damage = this.damage * 2;
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p,2), 2));		
		}
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Flex2(p, 2), 2));	  
		if (this.upgraded) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p,this.magicNumber), this.magicNumber));	
			SSS.WeaponPonit +=this.magicNumber;
		}
	}	



public AbstractCard makeCopy() {
    return new BeastUppercut();
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
	cardStrings = CardCrawlGame.languagePack.getCardStrings("BeastUppercut");
    NAME = BeastUppercut.cardStrings.NAME;
    DESCRIPTION = BeastUppercut.cardStrings.DESCRIPTION;
    UPGRADE_DESCRIPTION = BeastUppercut.cardStrings.UPGRADE_DESCRIPTION;
    EXTENDED_DESCRIPTION = BeastUppercut.cardStrings.EXTENDED_DESCRIPTION;
}

}
