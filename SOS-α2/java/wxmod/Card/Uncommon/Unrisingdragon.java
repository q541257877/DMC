 package wxmod.Card.Uncommon;
 
 import java.util.ArrayList;
 import java.util.List;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import basemod.abstracts.CustomCard;
 import basemod.helpers.TooltipInfo;
 import wxmod.Actions.GetPowerAmtAction;
 import wxmod.Actions.ReturnPowerNumberAction;
 import wxmod.Patches.AbstractCardEnum;
 
 public class Unrisingdragon extends CustomCard
 {
   public static final String ID = "Unrisingdragon";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Unrisingdragon.png"; 
   private static final int COST = 2;
   private int power;
   private List<TooltipInfo> tips;
   
   public Unrisingdragon()
   {
	   super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.UNCOMMON, 
				AbstractCard.CardTarget.ENEMY);
	   this.tips = new ArrayList<TooltipInfo>();
	   this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
	   }
   

   
   public void use(AbstractPlayer p, AbstractMonster m)
   {	if(p.hasPower("showtime")) {
	    this.power = p.getPower("showtime").amount;
	    if((this.power >= 2)&&(p.hasPower("Gilgameshpower"))) {
		   this.baseDamage = this.power  * 6 + ReturnPowerNumberAction.ReturnPowerNumber("Strength") * 5;  
	       this.damage = this.baseDamage *2;
	       AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "StrengthPower", 2));
	       }
	    else{
	           this.damage = this.power  * 6;
	          }
        }
	    AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));	  
        }
   
 
   public AbstractCard makeCopy()
   {
     return new Unrisingdragon();
   }
   
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
    }
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(GetPowerAmtAction.PowerAmt(p, "showtime") <1) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[2];
		}
		return canUse;
	}
   
   public void upgrade()
   {
     if (!this.upgraded) {
       upgradeName();
      this.upgradeBaseCost(1);
     }
   }

   static {
   		cardStrings = CardCrawlGame.languagePack.getCardStrings("Unrisingdragon");
   		NAME = Unrisingdragon.cardStrings.NAME;
   		DESCRIPTION = Unrisingdragon.cardStrings.DESCRIPTION;
   		EXTENDED_DESCRIPTION = Unrisingdragon.cardStrings.EXTENDED_DESCRIPTION;
   }
 }