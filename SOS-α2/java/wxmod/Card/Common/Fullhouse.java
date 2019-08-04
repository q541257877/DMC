package wxmod.Card.Common;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
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
import wxmod.Actions.GetPowerAmtAction;
import wxmod.Card.Special.Airhike2;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Power.showtime;

public class Fullhouse extends CustomCard{
	
	public static final String ID = "Fullhouse";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Fullhouse.png";
	private static final int COST = 2;
	private static final int ATTACK_DMG = 7;
	private int power;
	private List<TooltipInfo> tips;
	
	public Fullhouse() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DMC,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
		
		this.baseDamage = ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = 1;
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "showtime", 2));
		this.power = GetPowerAmtAction.PowerAmt(p, "showtime");	
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		AbstractCard c = new Airhike2();                                                          
		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, this.magicNumber));
		if(p.hasPower("Gilgameshpower")) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.power), this.power));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p,this.magicNumber), this.magicNumber));	
			 }
		 }
	
	@Override
    public AbstractCard makeCopy() {
        return new Fullhouse();
    }
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(GetPowerAmtAction.PowerAmt(p, "showtime") <2) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[3];
		}
		return canUse;
	}
	
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
    }
    
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Fullhouse");
        NAME = Fullhouse.cardStrings.NAME;
        DESCRIPTION = Fullhouse.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Fullhouse.cardStrings.EXTENDED_DESCRIPTION;
    }
	
}
