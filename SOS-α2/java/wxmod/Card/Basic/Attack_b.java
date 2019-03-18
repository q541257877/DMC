package wxmod.Card.Basic;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Patches.AbstractMulitTypeCard;
import wxmod.Power.Dimensionpower;
import wxmod.Power.Endlessswordpower2;
import wxmod.Power.Flex2;
import wxmod.Power.showtime;
import wxmod.Relic.SSS;

public class Attack_b extends AbstractMulitTypeCard{
	
	public static final String ID = "Attack_b";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/strike_gold.png";
	private static final int COST = 2;
	private static final int ATTACK_DMG = 9;
	
	
	public Attack_b() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DMC,
        		AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.ALL_ENEMY);
		
		this.baseDamage = ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = 2;
	}
	

    public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.hasPower("Yamatopower")) {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.utility.SFXAction("ATTACK_HEAVY"));
	        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.1F));
			AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage,true), DamageInfo.DamageType.HP_LOSS, AttackEffect.BLUNT_HEAVY));
			for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
				if ((!monster.isDead) && (!monster.isDying)) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new Dimensionpower(monster, 3), 3)); 
				}
			}
		}
		else {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.utility.SFXAction("ATTACK_HEAVY"));
	        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.1F));
	        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage,true), DamageInfo.DamageType.NORMAL, AttackEffect.NONE));
		}
		if(p.hasPower("Rebellionpower")||p.hasPower("Rebellionpower2")) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p,1), 1));
			SSS.WeaponPonit +=1;
		}
		if(p.hasPower("Gilgameshpower")) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
		    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Flex2(p, 2), 2));
		    for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
				 if ((!monster.isDead) && (!monster.isDying)) {
					 AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new VulnerablePower(monster, 1, false), 1)); 
				 }
			 }
		}
		if(p.hasPower("Luciferpower")||p.hasPower("Luciferpower2")) {
			for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
				 if ((!monster.isDead) && (!monster.isDying)) {
				 AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new Endlessswordpower2(monster, 2, p), 2)); 
				 }
			 }
		}
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p,this.magicNumber), this.magicNumber));	      
		SSS.WeaponPonit +=this.magicNumber;
	}
	
	
	
	
	@Override
    public AbstractCard makeCopy() {
        return new Attack_b();
    }
    
	public void optionYamato() {
		this.rawDescription = (EXTENDED_DESCRIPTION[0]);
		initializeDescription();
	}
   
	public void optionRebellion() {
		if(upgraded) {
			this.rawDescription = (EXTENDED_DESCRIPTION[1]);
			initializeDescription();
		}
		else {
			this.rawDescription = (EXTENDED_DESCRIPTION[2]);
			initializeDescription();
		}
	}
   
	public void optionGilgamesh() {
		this.rawDescription = (EXTENDED_DESCRIPTION[3]);
     	initializeDescription();
	}
   
	public void optionLucifer() {
		this.rawDescription = (EXTENDED_DESCRIPTION[4]);
		initializeDescription();
	}
	
	public void optionEbonyIvory() {
     	 this.rawDescription = DESCRIPTION;
      	 initializeDescription();
	}

	public void optionCoyoteA() {
	  	 this.rawDescription = DESCRIPTION;
	  	 initializeDescription();
	}

	public void optionPandora() {
	  	 this.rawDescription = DESCRIPTION;
	  	 initializeDescription();
	}
	
	public void optionNeutral() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	}
	
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Attack_b");
        NAME = Attack_b.cardStrings.NAME;
        DESCRIPTION = Attack_b.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Attack_b.cardStrings.EXTENDED_DESCRIPTION;
    }
	
}
