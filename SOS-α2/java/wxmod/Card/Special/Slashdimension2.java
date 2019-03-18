package wxmod.Card.Special;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import basemod.abstracts.CustomCard;
import wxmod.Power.Dimensionpower;
import wxmod.Power.showtime;
import wxmod.Relic.SSS;

public class Slashdimension2 extends CustomCard{
	
	public static final String ID = "Slashdimension2";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Slashdimension2.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;

	
	
	public Slashdimension2() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCard.CardColor.COLORLESS,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.exhaust = true;
		this.isEthereal = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		for(int i = 0;i < 5; i++) {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.utility.SFXAction("ATTACK_HEAVY"));
	        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.1F));
	        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));   
		}	
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(m, p, new Dimensionpower(m, 5), 5)); 
		    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p,2), 2));
		    SSS.WeaponPonit += 2;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Slashdimension2();
    }
    
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
        }
	
    }	

    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Slashdimension2");
    	NAME = Slashdimension2.cardStrings.NAME;
    	DESCRIPTION = Slashdimension2.cardStrings.DESCRIPTION;
    }
	
}
