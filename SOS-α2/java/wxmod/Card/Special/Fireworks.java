package wxmod.Card.Special;

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
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import basemod.abstracts.CustomCard;
import wxmod.Power.showtime;
import wxmod.Relic.SSS;

public class Fireworks extends CustomCard{
	
	public static final String ID = "Fireworks";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String IMG_PATH = "img/cards/strike_gold.png";
	private static final int COST = 1;

	
	
	public Fireworks() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCard.CardColor.COLORLESS,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ALL_ENEMY);
		this.baseDamage = this.damage = 0;
		this.exhaust = true;
		this.isEthereal = true;
		this.baseMagicNumber = this.magicNumber = 1;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		for(int i = 0;i < 3; i++) {
			this.damage =(int)Math.round(Math.random()*(20-10)+10);
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.utility.SFXAction("ATTACK_HEAVY"));
	        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.1F));
	        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage,true), DamageInfo.DamageType.NORMAL, AttackEffect.NONE));
		}
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p,this.magicNumber), this.magicNumber));
		SSS.WeaponPonit +=this.magicNumber;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Fireworks();
    }
    
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
        }
	
    }	
	
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Fireworks");
        NAME = Fireworks.cardStrings.NAME;
        DESCRIPTION = Fireworks.cardStrings.DESCRIPTION;
    }
	
}
