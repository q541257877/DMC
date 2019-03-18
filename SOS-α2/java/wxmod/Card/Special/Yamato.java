package wxmod.Card.Special;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
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
import wxmod.Actions.ReturnRandomNumberAction;
import wxmod.Power.Yamatopower;
import wxmod.Relic.SSS;

public class Yamato extends CustomCard{
	
	public static final String ID = "Yamato";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Yamato.png";
	private static final int COST = 2;
	private int number;

	
	
	public Yamato() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.POWER, AbstractCard.CardColor.COLORLESS,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.SELF);
		


	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		SSS.WeaponPonit = 0;
		AbstractDungeon.actionManager.addToBottom(new RemoveWeaponpowerAction(p, p));
		if(!p.hasPower("Devilpower")&&!p.hasPower("Flex1")) {
			AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new InflameEffect(p), 1.0F));
			AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_FIRE"));
			AbstractDungeon.actionManager.addToBottom(new VFXAction(new FireBurstParticleEffect(p.drawX, p.drawY), 0.05F));
			p.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_Y.png"));
		}
		 this.number = ReturnRandomNumberAction.ReturnRandomNumber();
		   if (this.number <= 5.0D ) { 
			   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Slashdimension1(), 1));
		   }
		   else {
			   AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Slashdimension2(), 1));
		   } 
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Yamatopower(p,1), 1));					    
		AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.animations.VFXAction(p, new com.megacrit.cardcrawl.vfx.combat.InflameEffect(p), 1.0F));	
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Yamato();
    }
    
    public void upgrade() {
    	
        if (!this.upgraded) {
            initializeDescription();
            this.upgradeName();
            
        }
	
    }	

    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Yamato");
    	NAME = Yamato.cardStrings.NAME;
    	DESCRIPTION = Yamato.cardStrings.DESCRIPTION;
    }
	
}
