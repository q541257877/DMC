 package wxmod.Card.Uncommon;
 
 import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import basemod.abstracts.CustomCard;
import basemod.helpers.TooltipInfo;
import wxmod.Patches.AbstractCardEnum;
 import wxmod.Power.Honeycombofirepower;

 
 public class Honeycombofire extends CustomCard {
	public static final String ID = "Honeycombofire";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION ;
	public static final String IMG_PATH = "img/cards/fire_ball.png";
	private static final int COST = 0;
	private List<TooltipInfo> tips;
   
   public Honeycombofire() {
	   super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
			AbstractCard.CardType.SKILL, AbstractCardEnum.DMC,
     		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
	   this.exhaust = true;
	   this.tips = new ArrayList<TooltipInfo>();
	   this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
   }
   
   public void use(AbstractPlayer p, AbstractMonster m)
   {
	   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Honeycombofirepower(p,1), 1));
   }
   
 
   public AbstractCard makeCopy()
   {
     return new Honeycombofire();
   }
   
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
    }
   
   public void upgrade()
   {
     if (!this.upgraded) {
       upgradeName();
       this.rawDescription = UPGRADE_DESCRIPTION;
       initializeDescription();
       this.exhaust = false;
     }
   }

   static {
	   cardStrings = CardCrawlGame.languagePack.getCardStrings("Honeycombofire");
	   NAME = Honeycombofire.cardStrings.NAME;
	   DESCRIPTION = Honeycombofire.cardStrings.DESCRIPTION;
	   UPGRADE_DESCRIPTION = Honeycombofire.cardStrings.UPGRADE_DESCRIPTION;
	   EXTENDED_DESCRIPTION = Honeycombofire.cardStrings.EXTENDED_DESCRIPTION;
	}
 }


