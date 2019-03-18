package wxmod.Card.Special;

import com.badlogic.gdx.graphics.Color;
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
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;

import basemod.abstracts.CustomCard;
import wxmod.Power.Dimensionpower;
import wxmod.Power.showtime;
import wxmod.Relic.SSS;

public class Slashdimension1 extends CustomCard{
	
	public static final String ID = "Slashdimension1";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Slashdimension1.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;

	
	
	public Slashdimension1() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCard.CardColor.COLORLESS,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ALL_ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.exhaust = true;
		this.isEthereal = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderLongFlashEffect(Color.LIGHT_GRAY.cpy())));
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new DieDieDieEffect(), 0.7F));
		for(int i = 0;i < 3; i++) {
	        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage,true), DamageInfo.DamageType.HP_LOSS, AttackEffect.SLASH_HORIZONTAL));
		}
		for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
			 if ((!monster.isDead) && (!monster.isDying)) {
				 AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new VulnerablePower(monster, 3, false), 3));
				 AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new Dimensionpower(monster, 3), 3)); 
			 }
		  }
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p,1), 1));
		SSS.WeaponPonit += 1;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Slashdimension1();
    }
    
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
        }
	
    }	

    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Slashdimension1");
    	NAME = Slashdimension1.cardStrings.NAME;
    	DESCRIPTION = Slashdimension1.cardStrings.DESCRIPTION;
    }
	
}
