package wxmod.Card.Special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.localization.CardStrings;
import basemod.abstracts.CustomCard;


public class Airhike2 extends CustomCard{
	public static final String ID = "Airhike2";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Airhike.png";
	private static final int COST = 0;

	
	public Airhike2() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.SPECIAL, 
				AbstractCard.CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = 1;
		this.exhaust = true;
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
		return new Airhike2();
	 }
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
		}
	}	
	
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Airhike2");
        NAME = Airhike2.cardStrings.NAME;
        DESCRIPTION = Airhike2.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Airhike2.cardStrings.EXTENDED_DESCRIPTION;
    }	
}