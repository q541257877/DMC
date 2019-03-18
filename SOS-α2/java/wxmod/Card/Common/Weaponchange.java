package wxmod.Card.Common;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.localization.CardStrings;
import basemod.abstracts.CustomCard;
import wxmod.Actions.PowerFromDeckToHandAction;
import wxmod.Patches.AbstractCardEnum;


public class Weaponchange extends CustomCard{
	public static final String ID = "Weaponchange";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION ;
	public static final String IMG_PATH = "img/cards/fire_ball.png";
	private static final int COST = 1;

	
	public Weaponchange() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.COMMON, 
				AbstractCard.CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = 1;
		this.exhaust = true;
  }
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new PowerFromDeckToHandAction(1));
		}
   
   public boolean canUse(AbstractPlayer p, AbstractMonster m)
   {
     boolean canUse = super.canUse(p, m);
      if (!canUse) {return false;}    
       boolean hasPower = false;
     for (AbstractCard c : p.drawPile.group) {
       if (c.type == AbstractCard.CardType.POWER) {
    	   hasPower = true;
     }
    }
    
    if (!hasPower) {
      this.cantUseMessage = EXTENDED_DESCRIPTION[0];
      canUse = false;
      }
     return canUse;   
     }
	
	public AbstractCard makeCopy() {
		return new Weaponchange();
	 }
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(0);
			this.isInnate = true;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
		}
	}

	static {
		cardStrings = CardCrawlGame.languagePack.getCardStrings("Weaponchange");
		NAME = Weaponchange.cardStrings.NAME;
		DESCRIPTION = Weaponchange.cardStrings.DESCRIPTION;
		UPGRADE_DESCRIPTION = Weaponchange.cardStrings.UPGRADE_DESCRIPTION;
		EXTENDED_DESCRIPTION = Weaponchange.cardStrings.EXTENDED_DESCRIPTION;
	}	
}