/*     */ package wxmod.Actions;
/*     */ 
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ArmamentsAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
/*     */ {
/*  14 */   private static final UIStrings uiStrings = com.megacrit.cardcrawl.core.CardCrawlGame.languagePack.getUIString("ArmamentsAction");
/*  15 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   private AbstractPlayer p;
/*  18 */   @SuppressWarnings({ "unchecked", "rawtypes" })
			private ArrayList<AbstractCard> cannotUpgrade = new ArrayList();
/*  19 */   private boolean upgraded = false;
/*     */   
/*     */   public ArmamentsAction(boolean armamentsPlus) {
/*  22 */     this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.CARD_MANIPULATION;
/*  23 */     this.p = AbstractDungeon.player;
/*  24 */     this.duration = Settings.ACTION_DUR_FAST;
/*  25 */     this.upgraded = armamentsPlus;
/*     */   }
/*     */   
/*     */   public void update()
/*     */   {
/*  30 */     if (this.duration == Settings.ACTION_DUR_FAST)
/*     */     {
/*     */ 
/*  33 */       if (this.upgraded) {
/*  34 */         for (AbstractCard c : this.p.hand.group) {
/*  35 */           if (c.canUpgrade()||c.type.equals(AbstractCard.CardType.POWER)) {
/*  36 */             c.upgrade();
/*  37 */             c.superFlash();
/*     */           }
/*     */         }
/*  40 */         this.isDone = true;
/*  41 */         return;
/*     */       }
/*     */       
/*     */ 
/*  45 */       for (AbstractCard c : this.p.hand.group) {
/*  46 */         if (!c.canUpgrade()) {
/*  47 */           this.cannotUpgrade.add(c);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  52 */       if (this.cannotUpgrade.size() == this.p.hand.group.size()) {
/*  53 */         this.isDone = true;
/*  54 */         return;
/*     */       }
/*     */       
/*  57 */       if (this.p.hand.group.size() - this.cannotUpgrade.size() == 1) {
/*  58 */         for (AbstractCard c : this.p.hand.group) {
/*  59 */           if (c.canUpgrade()||c.type.equals(AbstractCard.CardType.POWER)) {
/*  60 */             c.upgrade();
/*  61 */             this.isDone = true;
/*  62 */             return;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*  69 */       this.p.hand.group.removeAll(this.cannotUpgrade);
/*     */       
/*  71 */       if (this.p.hand.group.size() >= 1) {
/*  72 */         AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false, false, true);
/*  73 */         tickDuration();
/*  74 */         return; }
/*     */     }
/*     */     
/*     */ 
/*  83 */     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
/*  84 */       for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
/*  85 */         c.upgrade();
/*  86 */         this.p.hand.addToTop(c);
/*     */       }
/*     */       
/*  89 */       returnCards();
/*  90 */       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
/*  91 */       AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
/*  92 */       this.isDone = true;
/*     */     }
/*     */     
/*  95 */     tickDuration();
/*     */   }
/*     */   
/*     */   private void returnCards() {
/*  99 */     for (AbstractCard c : this.cannotUpgrade) {
/* 100 */       this.p.hand.addToTop(c);
/*     */     }
/* 102 */     this.p.hand.refreshHandLayout();
/*     */   }
/*     */ }



