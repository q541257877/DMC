package wxmod.Card.Special;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import basemod.abstracts.CustomCard;
import wxmod.Actions.GetPowerAmtAction;

public class PF594 extends CustomCard{
	
	public static final String ID = "PF594";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/PF594.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 9;
	private int power;

	
	
	public PF594() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCard.CardColor.COLORLESS,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ALL_ENEMY);
		this.damage = ATTACK_DMG;
		this.exhaust = true;
		this.isEthereal = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "Catastrophepower", 5));
		for(int i = 0;i < 4; i++) {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.utility.SFXAction("ATTACK_HEAVY"));
	        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.1F));
	        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(9,true), damageTypeForTurn, AttackEffect.NONE));
		}
		if(p.hasPower("showtime")) {
			this.power = p.getPower("showtime").amount;
			if (this.power > 0) {
		        for (int i = 0; i < this.power; i++) {
		        	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.utility.SFXAction("ATTACK_HEAVY"));
		            AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.1F));
		            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(9,true), damageTypeForTurn, AttackEffect.NONE));
		        }
			  }
		     }
		  }
	
	@Override
    public AbstractCard makeCopy() {
        return new PF594();
    }
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(GetPowerAmtAction.PowerAmt(p, "Catastrophepower") <5) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		}
		return canUse;
	}
    
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
        }
	
    }	

    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("PF594");
    	NAME = PF594.cardStrings.NAME;
    	DESCRIPTION = PF594.cardStrings.DESCRIPTION;
    	EXTENDED_DESCRIPTION = PF594.cardStrings.EXTENDED_DESCRIPTION;
    }
	
}
