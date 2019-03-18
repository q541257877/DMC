package wxmod.Card.Special;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import wxmod.Actions.GetPowerAmtAction;
import wxmod.Actions.RemoveAngrypowerAction;

public class Dreadnaught extends CustomCard{
	
	public static final String ID = "Dreadnaught";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Dreadnaught.png";
	private static final int COST = 0;
	private static final int BLOCK_AMT = 0;
	private int power;
	
	
	public Dreadnaught() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.SELF);
		this.exhaust = true;
		this.baseBlock = BLOCK_AMT;
	}

	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(AbstractDungeon.player.hasPower("Angrypower")) {
			this.power = (AbstractDungeon.player.getPower("Angrypower").amount);
			if (this.power >0) {
				this.block = this.power * 9;
				AbstractDungeon.actionManager.addToBottom(new RemoveAngrypowerAction(p, p));
				 AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
				}
			}
		}	



public AbstractCard makeCopy() {
    return new Dreadnaught();
}

public boolean canUse(AbstractPlayer p, AbstractMonster m) {
	boolean canUse = super.canUse(p, m);
	if(!canUse) return false;
	if(GetPowerAmtAction.PowerAmt(p, "Angrypower") <1) {
		canUse = false;
		this.cantUseMessage = EXTENDED_DESCRIPTION[0];
	}
	return canUse;
}

public void upgrade() {
    if (!this.upgraded) {
        this.upgradeName();
  }
 }	

static {
	cardStrings = CardCrawlGame.languagePack.getCardStrings("Dreadnaught");
    NAME = Dreadnaught.cardStrings.NAME;
    DESCRIPTION = Dreadnaught.cardStrings.DESCRIPTION;
    EXTENDED_DESCRIPTION = Dreadnaught.cardStrings.EXTENDED_DESCRIPTION;
}
}
