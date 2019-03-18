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
import basemod.abstracts.CustomCard;
import wxmod.Actions.GetPowerAmtAction;

public class PF398 extends CustomCard{
	
	public static final String ID = "PF398";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/PF398.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 0;
	private int power;

	
	
	public PF398() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCard.CardColor.COLORLESS,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ALL_ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.exhaust = true;
		this.isEthereal = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "Catastrophepower", 3));
		if(p.hasPower("showtime")) {
			this.power = (p.getPower("showtime").amount);
			if (this.power > 0) {
				this.baseDamage = this.power * 4;
				AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.baseDamage,true), DamageInfo.DamageType.NORMAL, AttackEffect.NONE));		        
				}
			   }	
		else{AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.baseDamage,true), DamageInfo.DamageType.NORMAL, AttackEffect.NONE));	
			}
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new com.megacrit.cardcrawl.vfx.combat.MindblastEffect(p.dialogX, p.dialogY, dontTriggerOnUseCard)));
		for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
			 if ((!monster.isDead) && (!monster.isDying)) {
			 AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(monster, p, new com.megacrit.cardcrawl.powers.WeakPower(monster, 3, false), 3));
			  }
			 }
		 }
	
	@Override
    public AbstractCard makeCopy() {
        return new PF398();
    }
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(GetPowerAmtAction.PowerAmt(p, "Catastrophepower") <3) {
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
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("PF398");
    	NAME = PF398.cardStrings.NAME;
    	DESCRIPTION = PF398.cardStrings.DESCRIPTION;
    	EXTENDED_DESCRIPTION = PF398.cardStrings.EXTENDED_DESCRIPTION;
    }
	
}
