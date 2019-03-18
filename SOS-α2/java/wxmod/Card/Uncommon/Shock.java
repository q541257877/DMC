package wxmod.Card.Uncommon;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import basemod.abstracts.CustomCard;
import basemod.helpers.TooltipInfo;
import wxmod.Actions.GetPowerAmtAction;
import wxmod.Actions.ReturnRandomNumberAction;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Power.XuanyunPower;
import wxmod.Power.showtime;
import wxmod.Relic.SSS;


public class Shock extends CustomCard{
	public static final String ID = "Shock";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION ;
	public static final String IMG_PATH = "img/cards/Shock.png";
	private static final int COST = 2;
	private static final int ATTACK_DMG = 9;
	private List<TooltipInfo> tips;
	
	public Shock() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.UNCOMMON, 
				AbstractCard.CardTarget.ALL_ENEMY);	
		this.baseDamage = ATTACK_DMG;
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "showtime", 2));
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage,true), DamageInfo.DamageType.NORMAL, AttackEffect.BLUNT_HEAVY));
		for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
			if ((!monster.isDead) && (!monster.isDying)) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new VulnerablePower(monster, 2, false), 2));
			}
		}
		if(p.hasPower("Gilgameshpower")) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p,1), 1));
			SSS.WeaponPonit += 1;
		}
		if((p.hasPower("Devilpower"))||(p.hasPower("Flex1"))) {
		    if (ReturnRandomNumberAction.ReturnRandomNumber() <= 6.0D ) {
		    	for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
		    		if ((!monster.isDead) && (!monster.isDying)) {
		    			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(monster, p, new XuanyunPower(monster), 1));
			 		}
			    }
		    }
		}
		else {
			if (ReturnRandomNumberAction.ReturnRandomNumber() <= 3.0D ) {
		    	for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
		    		if ((!monster.isDead) && (!monster.isDying)) {
		    			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(monster, p, new XuanyunPower(monster), 1));
			 		}
			    }
		    }
		}
		if (this.upgraded) {
		    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p,1), 1));
		    SSS.WeaponPonit += 1;
		}
	}
	
	public AbstractCard makeCopy() {
		return new Shock();
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(GetPowerAmtAction.PowerAmt(p, "showtime") <2) {
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
	        this.upgradeBaseCost(1);
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
		}
	}

	   static {
		   cardStrings = CardCrawlGame.languagePack.getCardStrings("Shock");
		   NAME = Shock.cardStrings.NAME;
		   DESCRIPTION = Shock.cardStrings.DESCRIPTION;
		   UPGRADE_DESCRIPTION = Shock.cardStrings.UPGRADE_DESCRIPTION;
		   EXTENDED_DESCRIPTION = Shock.cardStrings.EXTENDED_DESCRIPTION;
		}
	

}