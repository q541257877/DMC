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

public class Rebellionsword extends CustomCard {

	public static final String ID = "Rebellionsword";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Rebellionsword.png";
	private static final int COST = 1;
	private int power;

	
	
	public Rebellionsword() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCard.CardColor.COLORLESS,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ALL_ENEMY);
		this.exhaust = true;
		this.baseMagicNumber = this.magicNumber = 2;
		this.baseDamage = 0 ;
		this.isEthereal = true;
	}
	
	
    public void use(AbstractPlayer p, AbstractMonster m) {
    	if(p.hasPower("showtime")) {
    	this.power = p.getPower("showtime").amount;
    	if(this.power > 0) {
    	this.baseDamage = this.power * 3;
    	this.damage = this.baseDamage;
		AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.utility.SFXAction("ATTACK_HEAVY"));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.1F));     
        for(int i = 0;i < 3; i++) {
        	AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage,true), DamageInfo.DamageType.NORMAL, AttackEffect.NONE));
               }
    	    }
    	}
    	else {for(int i = 0;i < 3; i++) {
        	AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage,true), DamageInfo.DamageType.NORMAL, AttackEffect.NONE));
            }
        }      
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p,this.magicNumber), this.magicNumber));
		SSS.WeaponPonit +=this.magicNumber;
	}
	
	
    public AbstractCard makeCopy() {
        return new Rebellionsword();
    }
    
    public void upgrade() {
        if (!this.upgraded) {
        	this.upgradeName();
        }
    }	

    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Rebellionsword");
    	NAME = Rebellionsword.cardStrings.NAME;
    	DESCRIPTION = Rebellionsword.cardStrings.DESCRIPTION;
    }
	
}