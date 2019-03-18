package wxmod.Card.Rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import wxmod.WxMod;
import wxmod.Actions.GetPowerAmtAction;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Power.Devilpower;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;


public class Demonreform extends CustomCard{
	public static final String ID = "Demonreform";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Demonreform.png";
	private static final int COST = 3;

	
	public Demonreform() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.RARE, 
				AbstractCard.CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = 3;
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Devilpower(p, this.magicNumber), this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.utility.SFXAction("THUNDERCLAP", 0.05F));
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new com.megacrit.cardcrawl.vfx.combat.LightningEffect(p.drawX, p.drawY), 0.05F));
		p.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_DT.png"));
	}
	
	
	public AbstractCard makeCopy() {
		return new Demonreform();
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(GetPowerAmtAction.PowerAmt(p, "Devilpower") >= 1) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		}
		else if(GetPowerAmtAction.PowerAmt(p, "Flex1") >= 1) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		}
		return canUse;
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(1);
		}
	}
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Demonreform");
        NAME = Demonreform.cardStrings.NAME;
        DESCRIPTION = Demonreform.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Demonreform.cardStrings.EXTENDED_DESCRIPTION;
    }
	
	

}