package wxmod.Relic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import basemod.abstracts.CustomRelic;
import wxmod.WxMod;
import wxmod.Card.Vfx.Apoint;
import wxmod.Card.Vfx.Bpoint;
import wxmod.Card.Vfx.Cpoint;
import wxmod.Card.Vfx.Dpoint;
import wxmod.Card.Vfx.SSSpoint;
import wxmod.Card.Vfx.SSpoint;
import wxmod.Card.Vfx.Spoint;
import wxmod.Power.SSSpower;
import wxmod.Power.showtime;

 
public class SSS extends CustomRelic
 {
  public static final String ID = "SSS";
  public static int WeaponPonit;
  boolean isSelect = false;
  //boolean D1 = false;
  //boolean C1 = false;
  //boolean B1 = false;
  //boolean A1 = false;
  //boolean S1 = false;
  //boolean SS1 = false;
  //boolean SSS1 = false;
  int x = 0;
  
  
  public SSS()
   {
     super("SSS", new Texture(Gdx.files.internal("img/relics/sss.png")), new Texture(Gdx.files.internal("img/relics/outline/sss.png")), com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier.STARTER, com.megacrit.cardcrawl.relics.AbstractRelic.LandingSound.MAGICAL);
     this.counter = 0;
           }
  
  @Override
  public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
 } 
  @Override
   public void atBattleStart(){
	   flash();
	   WeaponPonit = 0;
	   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SSSpower(AbstractDungeon.player, 0), 0));
	   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new showtime(AbstractDungeon.player,2),2));
	   this.img = new Texture(Gdx.files.internal("img/relics/sss.png"));
	   isSelect = true;
   }
   
   @Override
	public int onAttacked(DamageInfo info, int damageAmount) {
		if (info.owner != null && info.type != DamageType.HP_LOSS && info.type != DamageType.THORNS && damageAmount < 1) {
			if(!AbstractDungeon.player.hasPower("Devilpower")&&!AbstractDungeon.player.hasPower("Flex1")&&!AbstractDungeon.player.hasPower("Royalguardpower")&&!AbstractDungeon.player.hasPower("Royalguardpower2")) {
				AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_B.png"));
				return damageAmount;
			} 
			else {
				if(AbstractDungeon.player.hasPower("Devilpower")||AbstractDungeon.player.hasPower("Flex1")&&!AbstractDungeon.player.hasPower("Royalguardpower")&&!AbstractDungeon.player.hasPower("Royalguardpower2")) {
					AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_DTB.png"));
				}
				return damageAmount;
			}
		}
		else {
				if(AbstractDungeon.player.hasPower("Devilpower")||AbstractDungeon.player.hasPower("Flex1")) {
					AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_DT.png"));
				}
				else if(AbstractDungeon.player.hasPower("Yamatopower")) {
					AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_Y.png"));
				}
				else if((AbstractDungeon.player.hasPower("Rebellionpower"))||(AbstractDungeon.player.hasPower("Rebellionpower2"))) {
					AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_R.png"));
				}
				else if(AbstractDungeon.player.hasPower("Gilgameshpower")) {
					AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_G.png"));
				}
				else if((AbstractDungeon.player.hasPower("Luciferpower"))||(AbstractDungeon.player.hasPower("Luciferpower2"))) {
					AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_L.png"));	
				}
				else if((AbstractDungeon.player.hasPower("Pandorapower"))||(AbstractDungeon.player.hasPower("Pandorapower2"))) {
					AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_P.png"));	
				}
				else if(AbstractDungeon.player.hasPower("CoyoteApower")) {
					AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_C.png"));	
				}
				else if(AbstractDungeon.player.hasPower("EbonyIvorypower")) {
					AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_E&I.png"));	
				}
				else if(AbstractDungeon.player.hasPower("Devilpower")||AbstractDungeon.player.hasPower("Flex1")) {
					AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_DT.png"));
				}
				else{
					AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_md.png"));  
				}
		}
		return damageAmount;
	}
   
	@Override
   public void onVictory() {
	   AbstractDungeon.player.img = ImageMaster.loadImage(WxMod.makePath("char/WxMages/Dante_md.png"));
	   isSelect = false;
	   x = 0;
   }
   
   public int onAttackedMonster(DamageInfo info, int damageAmount)
   {
	  isSelect = true;
	  x += 1;
	  flash();
	  return damageAmount;
     }
   
   @Override
   public void atTurnStart() {
	   if(this.counter != 0) {
		   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new showtime(AbstractDungeon.player,this.counter),this.counter));   
	   }
   }
   
   @Override
	public void onPlayerEndTurn() {
	   x = 0;
	   this.img = new Texture(Gdx.files.internal("img/relics/sss.png"));
    }
   
   @Override
   public void onUseCard(AbstractCard card, UseCardAction action)
   {
     if ((!card.purgeOnUse) && (card.cardID == "Purpleorb") && (this.counter < 4)) {
       this.counter += 1;
       flash();
    }
   }
   
   
   public AbstractRelic makeCopy() {
		return new SSS();
	}
   
	public void update() {
		super.update();
		if (isSelect) {
			if(x == 1) {
				AbstractDungeon.actionManager.addToBottom(new VFXAction(new Dpoint(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 0.50F));
				isSelect = false;
				//D1 = true;
				this.img = new Texture(Gdx.files.internal("img/relics/SSS/D11.png"));
			}
			if(x == 2) {
				AbstractDungeon.actionManager.addToBottom(new VFXAction(new Cpoint(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 0.50F));
				isSelect = false;
				//C1 = true;
				this.img = new Texture(Gdx.files.internal("img/relics/SSS/C11.png"));
			}
			if(x == 3) {
				AbstractDungeon.actionManager.addToBottom(new VFXAction(new Bpoint(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 0.50F));
				isSelect = false;
				//B1 = true;
				this.img = new Texture(Gdx.files.internal("img/relics/SSS/B11.png"));
			}
			if(x == 4) {
				AbstractDungeon.actionManager.addToBottom(new VFXAction(new Apoint(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 0.50F));
				isSelect = false;
				//A1 = true;
				this.img = new Texture(Gdx.files.internal("img/relics/SSS/A11.png"));
			}
			if(x == 5) {
				AbstractDungeon.actionManager.addToBottom(new VFXAction(new Spoint(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 0.50F));
				isSelect = false;
				//S1 = true;
				this.img = new Texture(Gdx.files.internal("img/relics/SSS/S11.png"));
			}
			if(x == 6) {
				AbstractDungeon.actionManager.addToBottom(new VFXAction(new SSpoint(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 0.50F));
				isSelect = false;
				//SS1 = true;
				this.img = new Texture(Gdx.files.internal("img/relics/SSS/SS11.png"));
			}
			if(x > 6) {
				AbstractDungeon.actionManager.addToBottom(new VFXAction(new SSSpoint(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 0.50F));
				isSelect = false;
				//SSS1 = true;
				this.img = new Texture(Gdx.files.internal("img/relics/SSS/SSS11.png"));
			}
		}
		//if(D1) {
			//AbstractDungeon.actionManager.addToBottom(new VFXAction(new D1point(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 0.30F));
			//D1 = false;
		//}
		//if(C1) {
			//AbstractDungeon.actionManager.addToBottom(new VFXAction(new C1point(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 0.30F));
			//C1 = false;
		//}
		//if(B1) {
			//AbstractDungeon.actionManager.addToBottom(new VFXAction(new B1point(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 0.30F));
			//B1 = false;
		//}
		//if(A1) {
			//AbstractDungeon.actionManager.addToBottom(new VFXAction(new A1point(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 0.30F));
			//A1 = false;
		//}
		//if(S1) {
			//AbstractDungeon.actionManager.addToBottom(new VFXAction(new S1point(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 0.30F));
			//S1 = false;
		//}
		//if(SS1) {
			//AbstractDungeon.actionManager.addToBottom(new VFXAction(new SS1point(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 0.30F));
			//SS1 = false;
		//}
		//if(SSS1) {
			//AbstractDungeon.actionManager.addToBottom(new VFXAction(new SSS1point(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 0.30F));
			//SSS1 = false;
		//}
		
	}
   
   }
 