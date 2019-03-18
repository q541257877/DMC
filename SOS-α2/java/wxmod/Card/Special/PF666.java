package wxmod.Card.Special;

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
import wxmod.Actions.GetPowerAmtAction;
import wxmod.Actions.RemoveCatastrophepowerAction;
import wxmod.Power.XuanyunPower;

import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

public class PF666 extends CustomCard{
	
	public static final String ID = "PF666";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;	
	public static final String IMG_PATH = "img/cards/PF666.png";
	private static final int COST = 2;
	private static final int ATTACK_DMG = 0;
	private int power;

	
	
	public PF666() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCard.CardColor.COLORLESS,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ALL_ENEMY);
		this.damage = ATTACK_DMG;
		this.exhaust = true;
		this.isEthereal = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		if((p.hasPower("Catastrophepower"))&&(p.getPower("Catastrophepower").amount != 0)) {
		AbstractDungeon.actionManager.addToBottom(new RemoveCatastrophepowerAction(p, p));	
		AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.animations.VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, com.megacrit.cardcrawl.core.Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 1.5F));
		for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
			 if ((!monster.isDead) && (!monster.isDying)) {
			 AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(monster, p, new XuanyunPower(monster), 1));
			 AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction(monster, p));
			  }
			 }
		if(p.hasPower("showtime")) {
			this.power = (p.getPower("showtime").amount);
			if (this.power > 0) {
				this.damage = this.power * 3;
				AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage,true), DamageInfo.DamageType.NORMAL, AttackEffect.NONE));		        
				}
			   }	
		else{AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage,true), DamageInfo.DamageType.NORMAL, AttackEffect.NONE));	
			}
		   }
		 }
	
	@Override
    public AbstractCard makeCopy() {
        return new PF666();
    }
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(GetPowerAmtAction.PowerAmt(p, "Catastrophepower") <1) {
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
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("PF666");
    	NAME = PF666.cardStrings.NAME;
    	DESCRIPTION = PF666.cardStrings.DESCRIPTION;
    	EXTENDED_DESCRIPTION = PF666.cardStrings.EXTENDED_DESCRIPTION;
    }
	
}
