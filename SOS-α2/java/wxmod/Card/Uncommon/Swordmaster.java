 package wxmod.Card.Uncommon;
 
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import basemod.abstracts.CustomCard;
 import wxmod.Patches.AbstractCardEnum;
 import wxmod.Power.Swordmasterpower;
import wxmod.Relic.SSS;

 
 public class Swordmaster extends CustomCard
 {
   public static final String ID = "Swordmaster";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Swordmaster.png";
	private static final int COST = 2;

   
   public Swordmaster() {
     super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
     		AbstractCard.CardType.SKILL, AbstractCardEnum.DMC,
     		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
     this.baseMagicNumber = this.magicNumber = 3;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
	   SSS.WeaponPonit = 0;
	   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Swordmasterpower(p,this.magicNumber), this.magicNumber));
   }
   
 
   public AbstractCard makeCopy()
   {
     return new Swordmaster();
   }
   
   public void upgrade()
   {
     if (!this.upgraded) {
       upgradeName();
       this.upgradeBaseCost(1);
     }
   }

   static {
   	cardStrings = CardCrawlGame.languagePack.getCardStrings("Swordmaster");
   	NAME = Swordmaster.cardStrings.NAME;
   	DESCRIPTION = Swordmaster.cardStrings.DESCRIPTION;
   }
 }

