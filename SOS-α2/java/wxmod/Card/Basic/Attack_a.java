package wxmod.Card.Basic;

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
import com.megacrit.cardcrawl.powers.StrengthPower;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Patches.AbstractMulitTypeCard;
import wxmod.Power.Dimensionpower;
import wxmod.Power.Endlessswordpower;
import wxmod.Power.Endlessswordpower2;
import wxmod.Power.Flex2;
import wxmod.Power.showtime;
import wxmod.Relic.SSS;


public class Attack_a extends AbstractMulitTypeCard{
	
	public static final String ID = "Attack_a";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/strike_gold.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;
	private static final int UPGRADE_PLUS_DMG = 3;

	
	
	public Attack_a() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DMC,
        		AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.ENEMY);
		
		this.baseDamage = ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = 1;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.hasPower("Yamatopower")) {
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.HP_LOSS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));   
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new Dimensionpower(m, this.magicNumber), this.magicNumber)); 
		}
		else {
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));	
		}
		if(p.hasPower("Rebellionpower")||p.hasPower("Rebellionpower2")) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p,1), 1));
			SSS.WeaponPonit +=1;
		}
		if(p.hasPower("Gilgameshpower")) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
		    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Flex2(p, this.magicNumber), this.magicNumber));  
		}
		if((p.hasPower("Luciferpower"))||(p.hasPower("Luciferpower2"))) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Endlessswordpower(p, 2), 2));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new Endlessswordpower2(m, 2, p), 2));
		}
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p,this.magicNumber), this.magicNumber));
		SSS.WeaponPonit +=this.magicNumber;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Attack_a();
    }
	
	@Override
    public boolean isStrike() {
        return true;
    }
	

	@Override
	public void optionYamato() {
		this.rawDescription = (EXTENDED_DESCRIPTION[0]);
		initializeDescription();
	}
   
	@Override
	public void optionRebellion() {
		this.rawDescription = (EXTENDED_DESCRIPTION[1]);
	   	initializeDescription();
	}
   
	@Override
	public void optionGilgamesh() {
		this.rawDescription = (EXTENDED_DESCRIPTION[2]);
     	initializeDescription();
	}
   
	@Override
	public void optionLucifer() {
		this.rawDescription = (EXTENDED_DESCRIPTION[3]);
		initializeDescription();
	}
	
	@Override
	public void optionEbonyIvory() {
     	 this.rawDescription = DESCRIPTION;
      	 initializeDescription();
	}

	@Override
	public void optionCoyoteA() {
	  	 this.rawDescription = DESCRIPTION;
	  	 initializeDescription();
	}

	@Override
	public void optionPandora() {
	  	 this.rawDescription = DESCRIPTION;
	  	 initializeDescription();
	}
	
	@Override
	public void optionNeutral() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	}
    
	@Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Attack_a");
        NAME = Attack_a.cardStrings.NAME;
        DESCRIPTION = Attack_a.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Attack_a.cardStrings.EXTENDED_DESCRIPTION;
    }
	
}
