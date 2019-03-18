 package wxmod.Card.Special;
 
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import basemod.abstracts.CustomCard;
import wxmod.WxMod;
import wxmod.Actions.GetPowerAmtAction;
import wxmod.Actions.ReturnPowerNumberAction;
import wxmod.Power.showtime;
 
 public class Risingdragon extends CustomCard
 {
   public static final String ID = "Risingdragon";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Risingdragon.png"; 
   private static final int COST = 2;
   private int power;
   
   public Risingdragon()
   {
	   super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
			   AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.SPECIAL, 
				AbstractCard.CardTarget.ENEMY);
	   this.baseMagicNumber = this.magicNumber = 1;
	   this.baseDamage = 0;
	   this.exhaust = true;
	   this.isEthereal = true;
	   }
   

   
   public void use(AbstractPlayer p, AbstractMonster m)
    {   
	   if(p.hasPower("showtime")) {
	      this.power = p.getPower("showtime").amount;
	      this.baseDamage = this.power  * 18 + ReturnPowerNumberAction.ReturnPowerNumber("Strength") * 10;  
	   if (p.hasPower("Gilgameshpower")) {
	       this.baseDamage = this.baseDamage *2;  
	         }
	   this.damage = this.baseDamage;
	   AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
	   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p, - this.power), - this.power));
	   AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.utility.SFXAction("THUNDERCLAP", 0.05F));
	   AbstractDungeon.actionManager.addToBottom(new VFXAction(new com.megacrit.cardcrawl.vfx.combat.LightningEffect(p.drawX, p.drawY), 0.05F));
	   p.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_DTG.png"));
       }
     }
   
   
 
   public AbstractCard makeCopy()
   {
     return new Risingdragon();
   }
   
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(!p.hasPower("Devilpower") && !p.hasPower("Flex1")) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		}
		else if(GetPowerAmtAction.PowerAmt(p, "showtime") <1) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[1];
		}
		return canUse;
	}
   
   public void upgrade()
   {
     if (!this.upgraded) {
       upgradeName();
     }
   }	

static {
	cardStrings = CardCrawlGame.languagePack.getCardStrings("Risingdragon");
	NAME = Risingdragon.cardStrings.NAME;
	DESCRIPTION = Risingdragon.cardStrings.DESCRIPTION;
	EXTENDED_DESCRIPTION = Risingdragon.cardStrings.EXTENDED_DESCRIPTION;
}
 }