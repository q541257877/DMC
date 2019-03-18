package wxmod.Card.Uncommon;

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
import com.megacrit.cardcrawl.vfx.FireBurstParticleEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

import basemod.abstracts.CustomCard;
import wxmod.WxMod;
import wxmod.Actions.RemoveWeaponpowerAction;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Power.Luciferpower;
import wxmod.Power.Luciferpower2;
import wxmod.Relic.SSS;

public class Lucifer extends CustomCard{
	
	public static final String ID = "Lucifer";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION ;
	public static final String IMG_PATH = "img/cards/Lucifer.png";
	private static final int COST = 1;

	
	
	public Lucifer() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.POWER, AbstractCardEnum.DMC,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		

		this.baseMagicNumber = this.magicNumber = 5;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		SSS.WeaponPonit = 0;
		AbstractDungeon.actionManager.addToBottom(new RemoveWeaponpowerAction(p, p));
		if(!p.hasPower("Devilpower")&&!p.hasPower("Flex1")) {
			AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new InflameEffect(p), 1.0F));
			AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_FIRE"));
			AbstractDungeon.actionManager.addToBottom(new VFXAction(new FireBurstParticleEffect(p.drawX, p.drawY), 0.05F));
			p.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_L.png"));
		}
		if(upgraded) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Luciferpower2(p,1), 1));
	    }
		else{
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Luciferpower(p,1), 1));}					    
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Lucifer();
    }
    
    public void upgrade() {
    	
        if (!this.upgraded) {        
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
	
    }

    static {
 	   cardStrings = CardCrawlGame.languagePack.getCardStrings("Lucifer");
 	   NAME = Lucifer.cardStrings.NAME;
 	   DESCRIPTION = Lucifer.cardStrings.DESCRIPTION;
 	   UPGRADE_DESCRIPTION = Lucifer.cardStrings.UPGRADE_DESCRIPTION;
 	}
	
}
