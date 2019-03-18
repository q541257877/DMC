package wxmod.Card.Common;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.localization.CardStrings;
import basemod.abstracts.CustomCard;
import wxmod.Patches.AbstractCardEnum;


public class Airhike extends CustomCard{
	public static final String ID = "Airhike";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Airhike.png";
	private static final int COST = 1;
	
	
	public Airhike() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.COMMON, 
				AbstractCard.CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = 1;
  }
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.PutOnDeckAction(p, p, 1, false));
		com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new wxmod.Actions.SkillFromDeckToHandAction(1));
   }
   
   public boolean canUse(AbstractPlayer p, AbstractMonster m)
   {
     boolean canUse = super.canUse(p, m);
      if (!canUse) {return false;}    
       boolean hasSkill = false;
     for (AbstractCard c : p.discardPile.group) {
       if (c.type == AbstractCard.CardType.SKILL) {
        hasSkill = true;
     }
    }
    
    if (!hasSkill) {
      this.cantUseMessage = EXTENDED_DESCRIPTION[0];
      canUse = false;
      }
     return canUse;   
     }
	
	public AbstractCard makeCopy() {
		return new Airhike();
	 }
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(0);
		}
	}	
	
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Airhike");
        NAME = Airhike.cardStrings.NAME;
        DESCRIPTION = Airhike.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Airhike.cardStrings.EXTENDED_DESCRIPTION;
    }
}