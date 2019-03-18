package wxmod.Relic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import wxmod.Card.Common.Weaponchange;

 
public class Combocheck extends CustomRelic
 {
  public static final String ID = "Combocheck";
  
  
  public Combocheck()
   {
     super("Combocheck", new Texture(Gdx.files.internal("img/relics/sss.png")), new Texture(Gdx.files.internal("img/relics/outline/sss.png")), RelicTier.STARTER, LandingSound.MAGICAL);
     this.counter = 0;
           }
  
  @Override
  public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
 } 

   
  
  @Override
  public void atTurnStart() {
	   this.counter = 0;
	   flash();
   }
  
  @Override
  public void onVictory() {
	  this.counter = 0;
  }
   
  @Override
   public int onAttackedMonster(DamageInfo info, int damageAmount)
   {
     if ((info.owner != null) && (damageAmount > 0))
     {
     	 if(this.counter >= 10) {
       AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
       return 1;
    	 }
     }
     return damageAmount;
     }
   
  @Override
   public void onUseCard(AbstractCard card, UseCardAction action)
   {
	  
	 if ((!card.purgeOnUse) && (card.type == AbstractCard.CardType.ATTACK)) {
       this.counter += 1;
    }
	 if ((!card.purgeOnUse) && (card.type == AbstractCard.CardType.ATTACK) && (this.counter == 10)) {
    	 AbstractCard c = new Weaponchange();                                                          
	     AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
	     flash();
 }
     if ((!card.purgeOnUse) && (card.type == AbstractCard.CardType.POWER)) {
    	 if(card.cardID =="Rebellion") {
    		 if(!AbstractDungeon.player.hasPower("Rebellionpower")&&!AbstractDungeon.player.hasPower("Rebellionpower2")) {
             this.counter = 0;
             flash();
             }
    	 }
    	 if(card.cardID =="EbonyIvory") {
    		 if(!AbstractDungeon.player.hasPower("EbonyIvorypower")) {
                 this.counter = 0;
                 flash();
    		 }
    	 }
    	 if(card.cardID =="Yamato") {
    		 if(!AbstractDungeon.player.hasPower("Yamatopower")) {
                 this.counter = 0;
                 flash();
    		 }
    	 }
    	 if(card.cardID =="CoyoteA") {
    		 if(!AbstractDungeon.player.hasPower("CoyoteApower")) {
                 this.counter = 0;
                 flash();
    		 }
    	 }
    	 if(card.cardID =="Gilgamesh") {
    		 if(!AbstractDungeon.player.hasPower("Gilgameshpower")) {
                 this.counter = 0;
                 flash();
    		 }
    	 }
    	 if(card.cardID =="Lucifer") {
    		 if(!AbstractDungeon.player.hasPower("Luciferpower")&&!AbstractDungeon.player.hasPower("Luciferpower2")) {
                 this.counter = 0;
                 flash();
    		 }
    	 }
    	 if(card.cardID =="Pandora") {
    		 if(!AbstractDungeon.player.hasPower("Pandorapower")&&!AbstractDungeon.player.hasPower("Pandorapower2")) {
                 this.counter = 0;
                 flash();
    		 }
    	 }
       }	 
     }
   
   
   public AbstractRelic makeCopy() {
		return new Combocheck();
	}
   
   
	
   
   }
 