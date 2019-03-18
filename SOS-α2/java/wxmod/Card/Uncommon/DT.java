package wxmod.Card.Uncommon;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import basemod.abstracts.CustomCard;
import wxmod.WxMod;
import wxmod.Actions.GetPowerAmtAction;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Power.Flex1;

public class DT extends CustomCard{
	
	public static final String ID = "DT";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/DT.png";
	private static final int COST = 1;
	private int power;

	
	
	public DT() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DMC,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		this.exhaust = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.utility.SFXAction("THUNDERCLAP", 0.05F));
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new com.megacrit.cardcrawl.vfx.combat.LightningEffect(p.drawX, p.drawY), 0.05F));
		p.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_DT.png"));
		if(p.hasPower("showtime")) {
			this.power = p.getPower("showtime").amount * 2;
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.power), this.power));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Flex1(p, this.power), this.power));	      
		}
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new DT();
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
	        this.upgradeBaseCost(0);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("DT");
        NAME = DT.cardStrings.NAME;
        DESCRIPTION = DT.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = DT.cardStrings.EXTENDED_DESCRIPTION;
    }
	
}
