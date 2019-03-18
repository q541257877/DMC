/*    */ package wxmod.Actions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ 
/*    */ public class AirhikeAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
/*    */ {
/*    */   private AbstractPlayer p;
/* 13 */   private static final UIStrings uiStrings = com.megacrit.cardcrawl.core.CardCrawlGame.languagePack.getUIString("SetupAction");
/* 14 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   
/*    */   public AirhikeAction() {
/* 17 */     this.p = AbstractDungeon.player;
/* 18 */     this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST;
/* 19 */     this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.CARD_MANIPULATION;
/*    */   }
/*    */   
/*    */   public void update() {
/*    */     AbstractCard c;
/* 24 */     if (this.duration == com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST) {
/* 25 */       if (this.p.hand.isEmpty()) {
/* 26 */         this.isDone = true;
/* 27 */         return; }
/* 28 */       if (this.p.hand.size() == 1) {
/* 29 */         c = this.p.hand.getTopCard();
/* 33 */         this.p.hand.moveToDeck(c, false);
/* 34 */         AbstractDungeon.player.hand.refreshHandLayout();
/* 35 */         this.isDone = true;
/* 36 */         return;
/*    */       }
/* 38 */       AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false);
/* 39 */       tickDuration();
/* 40 */       return;
/*    */     }
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 46 */     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
/* 47 */       for (AbstractCard c1 : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
/* 51 */         this.p.hand.moveToDeck(c1, false);
/*    */       }
/* 53 */       AbstractDungeon.player.hand.refreshHandLayout();
/* 54 */       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
/*    */     }
/*    */     
/* 57 */     tickDuration();
/*    */   }
/*    */ }

