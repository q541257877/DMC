package wxmod.Card.Common;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.FireBurstParticleEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

import basemod.abstracts.CustomCard;
import wxmod.WxMod;
import wxmod.Actions.RemoveWeaponpowerAction;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Power.Rebellionpower;
import wxmod.Power.Rebellionpower2;
import wxmod.Relic.SSS;

public class Rebellion extends CustomCard{
	
	public static final String ID = "Rebellion";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Rebellion.png";
	private static final int COST = 1;

	
	
	public Rebellion() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.POWER, AbstractCardEnum.DMC,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
		

		this.baseMagicNumber = this.magicNumber = 0;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		SSS.WeaponPonit = 0;
		AbstractDungeon.actionManager.addToBottom(new RemoveWeaponpowerAction(p, p));
		if(!p.hasPower("Devilpower")&&!p.hasPower("Flex1")) {
		AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new InflameEffect(p), 1.0F));
		AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_FIRE"));
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new FireBurstParticleEffect(p.drawX, p.drawY), 0.05F));
		p.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_R.png"));
		}				    
		if (this.upgraded) {
			AbstractDungeon.actionManager.addToBottom(new RemoveWeaponpowerAction(p, p));
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Rebellionpower2(p,1), 1));	
		}
		else {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Rebellionpower(p,1), 1));	
		}
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Rebellion();
    }
    
    public void upgrade() {
    	
        if (!this.upgraded) {
        	this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
            this.upgradeName();
            this.upgradeBaseCost(1);
            this.upgradeMagicNumber(2);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Rebellion");
        NAME = Rebellion.cardStrings.NAME;
        DESCRIPTION = Rebellion.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = Rebellion.cardStrings.UPGRADE_DESCRIPTION;
    }
	
}
