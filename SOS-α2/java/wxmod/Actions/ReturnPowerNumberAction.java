/*    */ package wxmod.Actions;
/*    */ 
/*    */ public class ReturnPowerNumberAction
/*    */ {
/*    */   private static int amount;
/*    */   
/*    */   public static int ReturnPowerNumber(String POWER_ID) {
/* 10 */     if (com.megacrit.cardcrawl.dungeons.AbstractDungeon.player.hasPower(POWER_ID)) {
/* 11 */       amount = com.megacrit.cardcrawl.dungeons.AbstractDungeon.player.getPower(POWER_ID).amount;
/*    */     } else {
/* 13 */       amount = 0;
/*    */     }
/*    */     
/* 16 */     return amount;
/*    */   }
/*    */ }


/* Location:              F:\SlayTheSpire\mods\kiritoMod_build0503_Beta.jar!\kirito\Action\ReturnPowerNumberAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */