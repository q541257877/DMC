package wxmod.Card.Rare;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import basemod.helpers.TooltipInfo;
import wxmod.Actions.RemoveEndlessswordpowerAction;
import wxmod.Actions.RemoveshowtimeAction;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Power.Endlessswordpower;
import wxmod.Power.Endlessswordpower2;

public class Splesh extends CustomCard{
	
	public static final String ID = "Splesh";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION ;
	public static final String IMG_PATH = "img/cards/Splesh.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 9;
	private int power;
	private int power2;
	private List<TooltipInfo> tips;
	
	
	public Splesh() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DMC,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ALL_ENEMY);
		this.exhaust = true;
		this.baseMagicNumber = this.magicNumber = 4;
		this.baseDamage = ATTACK_DMG;
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
	}

	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.hasPower("Endlessswordpower")) {
			this.power = p.getPower("Endlessswordpower").amount;
			if(this.power == 4) {
			   AbstractDungeon.actionManager.addToBottom(new RemoveEndlessswordpowerAction(p, p));
			    if(p.hasPower("showtime")) {
			    	if(p.getPower("showtime").amount > 0) {
				 this.power2 = p.getPower("showtime").amount * 3 ;
				 AbstractDungeon.actionManager.addToBottom(new RemoveshowtimeAction(p, p));
				 if((p.hasPower("Luciferpower"))||(p.hasPower("Luciferpower2"))) {
				for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
					 if ((!monster.isDead) && (!monster.isDying)) {
						 AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new Endlessswordpower2(monster, this.power2, p), this.power2, AbstractGameAction.AttackEffect.NONE));
					 }
				    }
				   }
				  }
				}
				
			}
			else if(this.power > 4) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Endlessswordpower(p,-4), -4));
				if(p.hasPower("showtime")) {
					if(p.getPower("showtime").amount > 0) {
						 this.power2 = p.getPower("showtime").amount * 3 ;
						 AbstractDungeon.actionManager.addToBottom(new RemoveshowtimeAction(p, p));   
						 if((p.hasPower("Luciferpower"))||(p.hasPower("Luciferpower2"))) {
								for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
									 if ((!monster.isDead) && (!monster.isDying)) {
										 AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new Endlessswordpower2(monster, this.power2, p), this.power2, AbstractGameAction.AttackEffect.NONE));
									 }
								    }
								   }
						  }
						}
		              }
		}
		
			
			
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage,true), DamageInfo.DamageType.NORMAL, AttackEffect.SMASH));
		}	



public AbstractCard makeCopy() {
    return new Splesh();
}

public List<TooltipInfo> getCustomTooltips() {
	return this.tips;
}

public void upgrade() {
    if (!this.upgraded) {
        this.upgradeName();
        this.exhaust = false;
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
  }
 }

static {
	cardStrings = CardCrawlGame.languagePack.getCardStrings("Splesh");
	NAME = Splesh.cardStrings.NAME;
	DESCRIPTION = Splesh.cardStrings.DESCRIPTION;
	UPGRADE_DESCRIPTION = Splesh.cardStrings.UPGRADE_DESCRIPTION;
	EXTENDED_DESCRIPTION = Splesh.cardStrings.EXTENDED_DESCRIPTION;
}
}
