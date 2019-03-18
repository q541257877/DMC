/*    */ package wxmod.Actions;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ 
/*    */ public class PowerFromDeckToHandAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
/*    */ {
/* 15 */   @SuppressWarnings("unused")
private static final UIStrings uiStrings = com.megacrit.cardcrawl.core.CardCrawlGame.languagePack.getUIString("DeckToHandAction");
/* 16 */   public static final String TEXT = "选择一张能力牌放入你的手牌";
/*    */   private AbstractPlayer p;
/*    */   
/*    */   public PowerFromDeckToHandAction(int amount)
/*    */   {
/* 21 */     this.p = AbstractDungeon.player;
/* 22 */     setValues(this.p, AbstractDungeon.player, amount);
/* 23 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 24 */     this.duration = Settings.ACTION_DUR_MED;
/*    */   }
/*    */   
/*    */   public void update() {
/*    */     CardGroup tmp;
/* 29 */     if (this.duration == Settings.ACTION_DUR_MED) {
/* 30 */       tmp = new CardGroup(com.megacrit.cardcrawl.cards.CardGroup.CardGroupType.UNSPECIFIED);
/* 31 */       for (AbstractCard c : this.p.drawPile.group) {
/* 32 */         if (c.type == com.megacrit.cardcrawl.cards.AbstractCard.CardType.POWER) {
/* 33 */           tmp.addToRandomSpot(c);
					c.setCostForTurn(-9);
/*    */         }
/*    */       }
/*    */       
/* 37 */       if (tmp.size() == 0) {
/* 38 */         this.isDone = true;
/* 39 */         return; }
/* 40 */       if (tmp.size() == 1) {
/* 41 */         AbstractCard card = tmp.getTopCard();
/*    */         card.setCostForTurn(-9);
/* 43 */         if (this.p.hand.size() == 10) {
/* 44 */           this.p.drawPile.moveToDiscardPile(card);
/* 45 */           this.p.createHandIsFullDialog();
/*    */         } else {
	               card.setCostForTurn(-9);
/* 47 */           card.unhover();
/* 48 */           card.lighten(true);
/* 49 */           card.setAngle(0.0F);
/* 50 */           card.drawScale = 0.12F;
/* 51 */           card.targetDrawScale = 0.75F;
/* 52 */           card.current_x = CardGroup.DRAW_PILE_X;
/* 53 */           card.current_y = CardGroup.DRAW_PILE_Y;
/* 54 */           this.p.drawPile.removeCard(card);
/* 55 */           AbstractDungeon.player.hand.addToTop(card);
/* 56 */           AbstractDungeon.player.hand.refreshHandLayout();
/* 57 */           AbstractDungeon.player.hand.applyPowers();
/*    */         }
/* 59 */         this.isDone = true;
/* 60 */         return;
/*    */       }
/*    */       
/* 63 */       AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT, false);
/* 64 */       tickDuration();
/* 65 */       return;
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 70 */     if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
/* 71 */       for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
/* 72 */         c.unhover();
/*    */         
/* 74 */         if (this.p.hand.size() == 10) {
/* 75 */           this.p.drawPile.moveToDiscardPile(c);
/* 76 */           this.p.createHandIsFullDialog();
/*    */         } else {
/* 78 */           this.p.drawPile.removeCard(c);
/* 79 */           this.p.hand.addToTop(c);
				   c.setCostForTurn(-9);
/*    */         }
/* 81 */         this.p.hand.refreshHandLayout();
/* 82 */         this.p.hand.applyPowers();
/*    */       }
/* 84 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/* 85 */       this.p.hand.refreshHandLayout();
/*    */     }
/*    */     
/* 88 */     tickDuration();
/*    */   }
/*    */ }


