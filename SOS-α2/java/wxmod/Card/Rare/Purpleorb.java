package wxmod.Card.Rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import wxmod.Card.Vfx.slashGold;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Power.showtime;
import wxmod.Relic.SSS;


public class Purpleorb extends CustomCard{
	public static final String ID = "Purpleorb";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Purpleorb.png";
	private static final int COST = 1;
	
	public Purpleorb() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.RARE, 
				AbstractCard.CardTarget.SELF);
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.gold >=150) {
			p.gold -= 150;
			for(int i = 0;i < 150; i++) {
			    AbstractDungeon.effectsQueue.add(new slashGold(p));
			}
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p,4), 4));
			SSS.WeaponPonit +=4;
		}
	}
	
	public AbstractCard makeCopy() {
		return new Purpleorb();
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(p.gold <150) {
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
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Purpleorb");
        NAME = Purpleorb.cardStrings.NAME;
        DESCRIPTION = Purpleorb.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Purpleorb.cardStrings.EXTENDED_DESCRIPTION;
    }
	

}