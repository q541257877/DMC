package wxmod.Card.Uncommon;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import basemod.helpers.TooltipInfo;
import wxmod.Card.Common.Millionstab;
import wxmod.Patches.AbstractCardEnum;

public class Stinger extends CustomCard{
	
	public static final String ID = "Stinger";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Stinger.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;
	private List<TooltipInfo> tips;
	
	
	public Stinger() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DMC,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
		this.exhaust = true;
		this.baseDamage = this.damage =  ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = 1;
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		AbstractCard c = new Millionstab();                                                          
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, this.magicNumber));
        if(p.hasPower("Rebellionpower")||p.hasPower("Rebellionpower2")) {
        	c.setCostForTurn(0);
        }
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Stinger();
    }
	
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
    }
    
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
	
    }

    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Stinger");
    	NAME = Stinger.cardStrings.NAME;
    	DESCRIPTION = Stinger.cardStrings.DESCRIPTION;
    	EXTENDED_DESCRIPTION = Stinger.cardStrings.EXTENDED_DESCRIPTION;
    }
	
}
