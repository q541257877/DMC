package wxmod.Power;
import com.megacrit.cardcrawl.actions.common.SetMoveAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
 
 public class XuanyunPower extends com.megacrit.cardcrawl.powers.AbstractPower
 {
   public static final String POWER_ID = "XuanyunPower";
		   private static final PowerStrings powerStrings;
		   public static final String NAME;
		   public static final String[] DESCRIPTIONS;
   
   private byte moveByte;
   private AbstractMonster.Intent moveIntent;
   
   public XuanyunPower(AbstractCreature owner)
   {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = -1;
     updateDescription();
     
     this.type = AbstractPower.PowerType.DEBUFF;
     this.img = ImageMaster.loadImage("img/powers/mabi.png");
     
     this.moveByte = 1;
     this.moveIntent = AbstractMonster.Intent.UNKNOWN;
     
     if ((owner instanceof AbstractMonster)) {
    	 AbstractMonster m = (AbstractMonster)owner;
       
    	 this.moveByte = Byte.valueOf(m.nextMove).byteValue();
    	 this.moveIntent = AbstractMonster.Intent.valueOf(m.intent.name());
       
       m.setMove((byte)Byte.MAX_VALUE, AbstractMonster.Intent.STUN);
       m.createIntent();
       AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, (byte)Byte.MAX_VALUE, AbstractMonster.Intent.STUN));
     }
   }
   
   public void updateDescription()
   {
     this.description = DESCRIPTIONS[0];
   }
   
   public void atEndOfRound()
   {
	   AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
     
     if ((this.owner instanceof AbstractMonster)) {
    	 AbstractMonster m = (AbstractMonster)this.owner;
       
    	 m.setMove(this.moveByte, this.moveIntent);
    	 m.createIntent();
    	 AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, this.moveByte, this.moveIntent));
     }
   }

	static {
		powerStrings = CardCrawlGame.languagePack.getPowerStrings("XuanyunPower");
		NAME = XuanyunPower.powerStrings.NAME;
		DESCRIPTIONS = XuanyunPower.powerStrings.DESCRIPTIONS;
	}
   

 }
