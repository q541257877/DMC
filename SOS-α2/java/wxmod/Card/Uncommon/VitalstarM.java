package wxmod.Card.Uncommon;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import wxmod.Card.Vfx.slashGold;
import wxmod.Patches.AbstractCardEnum;


public class VitalstarM extends CustomCard{
	public static final String ID = "VitalstarM";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Vitalstar.png";
	private static final int COST = 1;
	
	public VitalstarM() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.UNCOMMON, 
				AbstractCard.CardTarget.SELF);
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.gold >=30) {
		p.gold -= 30;
		 for(int i = 0;i < 30; i++) {
			    AbstractDungeon.effectsQueue.add(new slashGold(p));
			    }
		 AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, 10));
		}
	}
	
	public AbstractCard makeCopy() {
		return new VitalstarM();
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(p.gold <30) {
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
	   		cardStrings = CardCrawlGame.languagePack.getCardStrings("VitalstarM");
	   		NAME = VitalstarM.cardStrings.NAME;
	   		DESCRIPTION = VitalstarM.cardStrings.DESCRIPTION;
	   		EXTENDED_DESCRIPTION = VitalstarM.cardStrings.EXTENDED_DESCRIPTION;
	   }
	

}