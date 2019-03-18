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


public class DevilstarL extends CustomCard{
	public static final String ID = "DevilstarL";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Devilstar.png";
	private static final int COST = 1;
	
	public DevilstarL() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.RARE, 
				AbstractCard.CardTarget.SELF);
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.gold >=60) {
			p.gold -= 60;
			for(int i = 0;i < 60; i++) {
				AbstractDungeon.effectsQueue.add(new slashGold(p));
			}
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p,6), 6));	
			SSS.WeaponPonit += 6;
		}
	}
	
	public AbstractCard makeCopy() {
		return new DevilstarL();
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(p.gold <60) {
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
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("DevilstarL");
        NAME = DevilstarL.cardStrings.NAME;
        DESCRIPTION = DevilstarL.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = DevilstarL.cardStrings.EXTENDED_DESCRIPTION;
    }
	

}