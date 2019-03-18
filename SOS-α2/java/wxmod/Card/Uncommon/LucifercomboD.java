package wxmod.Card.Uncommon;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
import wxmod.Actions.RemoveEndlessswordpower2Action;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Power.Endlessswordpower;

public class LucifercomboD extends CustomCard{
	
	public static final String ID = "LucifercomboD";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/LucifercomboD.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 3;
	private int power;
	private List<TooltipInfo> tips;
	
	
	public LucifercomboD() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DMC,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
		this.baseMagicNumber = this.magicNumber = 4;
		this.baseDamage = ATTACK_DMG;
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
	}

	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(m.hasPower("Endlessswordpower2")) {this.power = m.getPower("Endlessswordpower2").amount;
		AbstractDungeon.actionManager.addToBottom(new RemoveEndlessswordpower2Action(m, p));
		}
		for(int i = 0;i < this.power; i++) {
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
		}
		if((p.hasPower("Luciferpower"))||(p.hasPower("Luciferpower2"))) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Endlessswordpower(p,this.magicNumber), this.magicNumber));
		}
	}	



public AbstractCard makeCopy() {
    return new LucifercomboD();
}

public List<TooltipInfo> getCustomTooltips() {
	return this.tips;
}

public void upgrade() {
    if (!this.upgraded) {
        this.upgradeName();
        this.upgradeDamage(1);
  }
 }

static {
	cardStrings = CardCrawlGame.languagePack.getCardStrings("LucifercomboD");
    NAME = LucifercomboD.cardStrings.NAME;
    DESCRIPTION = LucifercomboD.cardStrings.DESCRIPTION;
    EXTENDED_DESCRIPTION = LucifercomboD.cardStrings.EXTENDED_DESCRIPTION;
}
}
