package wxmod.Card.Uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import wxmod.Card.Vfx.slashGold;
import wxmod.Patches.AbstractCardEnum;


public class Holywater extends CustomCard{
	public static final String ID = "Holywater";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/fire_ball.png";
	private static final int COST = 1;
	
	public Holywater() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.UNCOMMON, 
				AbstractCard.CardTarget.ALL_ENEMY);	
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.gold >=80) {
		p.gold -= 80;
		 for(int i = 0;i < 80; i++) {
			    AbstractDungeon.effectsQueue.add(new slashGold(p));
			    }
		 AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(20,true), DamageInfo.DamageType.HP_LOSS, AttackEffect.POISON));
		}
	}
	
	public AbstractCard makeCopy() {
		return new Holywater();
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(p.gold <80) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		}
		return canUse;
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
	        this.upgradeBaseCost(0);
		}
	}
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Holywater");
        NAME = Holywater.cardStrings.NAME;
        DESCRIPTION = Holywater.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Holywater.cardStrings.EXTENDED_DESCRIPTION;
    }
	

}