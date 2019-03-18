package wxmod.Card.Rare;

import java.util.ArrayList;
import java.util.List;

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
import basemod.helpers.TooltipInfo;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Power.showtime;
import wxmod.Relic.SSS;

public class Swordcombo extends CustomCard{
	
	public static final String ID = "Swordcombo";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Swordcombo.png";
	private static final int COST = 2;
	private static final int ATTACK_DMG = 6;
	private List<TooltipInfo> tips;
	
	
	public Swordcombo() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DMC,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
		
		this.baseMagicNumber = this.magicNumber = 1;
		this.baseDamage = ATTACK_DMG;
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		for(int i = 0;i < 5; i++) {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.utility.SFXAction("ATTACK_HEAVY"));
	        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.1F));
	        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));   
		}	
		if(p.hasPower("Rebellionpower")||p.hasPower("Rebellionpower2")) {
		    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p,1), 1));
		    SSS.WeaponPonit +=1;
		}
		    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p,this.magicNumber), this.magicNumber));
		    SSS.WeaponPonit +=this.magicNumber;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Swordcombo();
    }
	
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
    }
    
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
	
    }
	
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Swordcombo");
        NAME = Swordcombo.cardStrings.NAME;
        DESCRIPTION = Swordcombo.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Swordcombo.cardStrings.EXTENDED_DESCRIPTION;
    }
	
}
