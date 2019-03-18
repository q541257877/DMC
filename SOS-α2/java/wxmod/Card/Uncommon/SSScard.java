package wxmod.Card.Uncommon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Power.showtime;
import wxmod.Relic.SSS;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;


public class SSScard extends CustomCard{
	public static final String ID = "SSScard";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION ;
	public static final String IMG_PATH = "img/cards/SSScard.png";
	private static final int COST = 1;
	
	public SSScard() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.UNCOMMON, 
				AbstractCard.CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = 0;
		this.exhaust = true;
	}
	
   public void applyPowers()
   {int count = 0;
     super.applyPowers(); 
    for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
       if (c.type == AbstractCard.CardType.ATTACK) {
         count++;
         this.baseMagicNumber = count;
      }
     }
   }
	
	
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		for(int i = 0;i < this.baseMagicNumber; i++) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p, 1), 1));
			SSS.WeaponPonit += 1;
		}
		if(upgraded) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p, 1), 1));
			SSS.WeaponPonit += 1;
		}
	}
	
	public AbstractCard makeCopy() {
		return new SSScard();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.rawDescription = UPGRADE_DESCRIPTION;
		    initializeDescription();
		    this.upgradeBaseCost(0);
		}
	}

    static {
 	   cardStrings = CardCrawlGame.languagePack.getCardStrings("SSScard");
 	   NAME = SSScard.cardStrings.NAME;
 	   DESCRIPTION = SSScard.cardStrings.DESCRIPTION;
 	   UPGRADE_DESCRIPTION = SSScard.cardStrings.UPGRADE_DESCRIPTION;
 	}
	

}