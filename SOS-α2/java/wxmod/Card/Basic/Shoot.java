package wxmod.Card.Basic;

import java.util.Random;

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
import wxmod.Patches.AbstractCardEnum;
import wxmod.Patches.AbstractMulitTypeCard;

public class Shoot extends AbstractMulitTypeCard{
	
	public static final String ID = "Shoot";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Shoot.png";
	private static final int COST = 1;
	public static final int POOL = 2;
	private int number;
	
	
	public Shoot() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DMC,
        		AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.ENEMY);
		
		
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.hasPower("EbonyIvorypower")) {
			this.damage = 9;
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));		
		}
		else {
			this.damage = 6;
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));		
		}
		if((p.hasPower("Pandorapower"))||(p.hasPower("Pandorapower2"))) {
			this.damage = 6;
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));		
		}
		if(p.hasPower("CoyoteApower")) {
			this.number = new Random().nextInt(2)+1;
			this.damage = 6;
			for(int i = 0;i < this.number; i++) {
				AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.getMonsters().getRandomMonster(m, true), new com.megacrit.cardcrawl.cards.DamageInfo(p, this.damage ,DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
			}
		}
	}	


	@Override
	public AbstractCard makeCopy() {
		return new Shoot();
	}

	@Override
	public boolean isStrike() {
		return true;
	}

	@Override
	public void optionYamato() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	}

	@Override
	public void optionRebellion() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	}

	@Override
	public void optionGilgamesh() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	}

	@Override
	public void optionLucifer() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	}

	@Override
	public void optionEbonyIvory() {
		this.rawDescription = EXTENDED_DESCRIPTION[0];
		initializeDescription();
	}

	@Override
	public void optionCoyoteA() {
		this.rawDescription = EXTENDED_DESCRIPTION[1];
		initializeDescription();
	}

	@Override
	public void optionPandora() {
		this.rawDescription = EXTENDED_DESCRIPTION[2];
		initializeDescription();
	}

	@Override
	public void optionNeutral() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	}

public void upgrade() {
    if (!this.upgraded) {
        this.upgradeName();
        this.upgradeBaseCost(0);
  }
 }

static {
	cardStrings = CardCrawlGame.languagePack.getCardStrings("Shoot");
    NAME = Shoot.cardStrings.NAME;
    DESCRIPTION = Shoot.cardStrings.DESCRIPTION;
    EXTENDED_DESCRIPTION = Shoot.cardStrings.EXTENDED_DESCRIPTION;
}

}
