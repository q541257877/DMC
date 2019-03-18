package wxmod;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.Keyword;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.nio.charset.StandardCharsets;
import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.OnCardUseSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.patches.com.megacrit.cardcrawl.helpers.CardLibrary.GreenCardsPatch;
import basemod.patches.com.megacrit.cardcrawl.helpers.CardLibrary.RedCardsPatch;
import wxmod.Card.Basic.*;
import wxmod.Card.Common.*;
import wxmod.Card.Rare.*;
import wxmod.Card.Special.*;
import wxmod.Card.Uncommon.*;
import wxmod.Characters.Dante;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Patches.CharacterEnum;

import wxmod.Relic.*;

import static basemod.DevConsole.logger;

@SpireInitializer
public class WxMod implements PostInitializeSubscriber,EditCharactersSubscriber,EditCardsSubscriber,EditRelicsSubscriber,EditStringsSubscriber,OnCardUseSubscriber,EditKeywordsSubscriber{

	
    private static final String FRUITY_MOD_ASSETS_FOLDER = "img";
    private static final Color DMC = CardHelper.getColor(220, 20, 60);
    public static final Color SPARDA = CardHelper.getColor(220, 20, 60);

    // Cards
    public static final String DEFEND_GOLD = "cards/defend_gold.png";
    public static final String STRIKE_GOLD = "cards/strike_gold.png";
    public static final String FIRE_BALL = "cards/fire_ball.png";

    // card backgrounds
    private static final String ATTACK_GOLD = "512/bg_attack_gold.png";
    private static final String SKILL_GOLD = "512/bg_attack_gold.png";
    private static final String POWER_GOLD = "512/bg_attack_gold.png";
    private static final String ENERGY_ORB_GOLD = "512/card_gold_orb.png";

    private static final String ATTACK_GOLD_PORTRAIT = "1024/bg_attack_gold.png";
    private static final String SKILL_GOLD_PORTRAIT = "1024/bg_attack_gold.png";
    private static final String POWER_GOLD_PORTRAIT = "1024/bg_attack_gold.png";
    private static final String ENERGY_ORB_GOLD_PORTRAIT = "1024/card_gold_orb.png";

    // assets
    private static final String MAGES_BUTTON = "charSelect/MagesButton.png";
    private static final String MAGES_PORTRAIT = "charSelect/MagesPortrait.jpg";
    public static final String MAGES_SHOULDER_1 = "char/WxMages/shoulder.png";
    public static final String MAGES_SHOULDER_2 = "char/WxMages/shoulder2.png";
    public static final String MAGES_CORPSE = "char/WxMages/corpse.png";
    public static final String MAGES_SKELETON_ATLAS = "char/WxMages/skeleton.atlas";
    public static final String MAGES_SKELETON_JSON = "char/WxMages/skeleton.json";
    public static final String MAGES_MD = "char/WxMages/Dante_md.png";

    public static void initialize() {
        logger.info("========================= 初始化Mod所有数据 =========================");
        new WxMod();
        logger.info("=========================== 初始化Mod成功 ===========================");
    }

    public static final String makePath(String resource) {
        return FRUITY_MOD_ASSETS_FOLDER + "/" + resource;
    }

    public WxMod(){
        
    	logger.info("============================ 监听初始化事件 ============================");
        
        BaseMod.subscribe(this);

        
        logger.info("========================================================================");
    	    	


        logger.info("======================== 正在注入新卡片相关信息 ========================");
        BaseMod.addColor(AbstractCardEnum.DMC,
                DMC, DMC, DMC, DMC, DMC, DMC, DMC,
                makePath(ATTACK_GOLD), makePath(SKILL_GOLD),
                makePath(POWER_GOLD), makePath(ENERGY_ORB_GOLD),
                makePath(ATTACK_GOLD_PORTRAIT), makePath(SKILL_GOLD_PORTRAIT),
                makePath(POWER_GOLD_PORTRAIT), makePath(ENERGY_ORB_GOLD_PORTRAIT));
        logger.info("===========================注入新卡片相关信息成功========================");    
    }

    private static String loadJson(final String jsonPath) {
        return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
    }


    @Override
    public void receiveEditKeywords() {
    	
    	//BaseMod.addKeyword(new String[]{"表演时间"}, "回合结束时给予层数相同的格挡并清零，最高7层");
    	//BaseMod.addKeyword(new String[]{"近战系"}, "近战系攻击卡牌，受剑圣风格强化");
    	//BaseMod.addKeyword(new String[]{"枪械系"}, "枪械系攻击卡牌，受枪神风格强化");
    	//BaseMod.addKeyword(new String[]{"风格得分系统"}, "遗物：风格得分系统Ver.1/Ver.2,能在累积ShowTime层数后获得各种特殊强化");
    	//BaseMod.addKeyword(new String[]{"剑气"}, "对所有敌人造成3次伤害，攻击力由ShowTime层数决定");
    	//BaseMod.addKeyword(new String[]{"固定伤害"}, "造成的伤害不受力量或状态影响");
    	//BaseMod.addKeyword(new String[]{"真·升龙拳"}, "对1名敌人造成巨额伤害，攻击力受ShowTime层数和力量决定");
    	//BaseMod.addKeyword(new String[]{"临时力量"}, "效果同力量，回合结束后失去");
    	//BaseMod.addKeyword(new String[]{"剑圣风格"}, "加强近战系攻击卡牌并让近战武器获得特殊效果");
    	//BaseMod.addKeyword(new String[]{"枪神风格"}, "加强枪械系攻击卡牌并让枪械武器获得特殊效果");
    	//BaseMod.addKeyword(new String[]{"暴风雨"}, "随机对敌人造成多段固定伤害，段数由ShowTime层数决定");
    	//BaseMod.addKeyword(new String[]{"魔人状态"}, "每3次攻击回复3点HP，每触发一次风格得分系统获得7点力量，结束时减少18点力量");
    	//BaseMod.addKeyword(new String[]{"完美防御"}, "概率免疫攻击，被攻击会积攒怒气");
    	//BaseMod.addKeyword(new String[]{"怒气释放"}, "消耗怒气，根据怒气值或护甲值造成伤害");
    	//BaseMod.addKeyword(new String[]{"一闪"}, "消耗怒气，根据怒气值或护甲值造成强大的伤害");
    	//BaseMod.addKeyword(new String[]{"魔人铠甲"}, "释放怒气，根据怒气值获得护甲");
    	//BaseMod.addKeyword(new String[]{"怒气"}, "每回合开始获得与其层数相同的ShowTime和能量,回合结束-1,强化怒气相关技能");
    	//BaseMod.addKeyword(new String[]{"风格状态"}, "剑圣风格、枪神风格、完美防御（皇家护卫风格）、空中之星（骗术师风格）");
    	//BaseMod.addKeyword(new String[]{"风格"}, "该卡属于风格卡牌，可获得相应 风格状态 强化");
    	//BaseMod.addKeyword(new String[]{"空中之星"}, "可延长风格状态及表演时间、怒气的持续时间");
    	//BaseMod.addKeyword(new String[]{"阎魔刀"}, "相关攻击卡牌可造成无视格挡的伤害");
    	//BaseMod.addKeyword(new String[]{"入鞘"}, "消耗次元裂缝，造成伤害并回复能量");
    	//BaseMod.addKeyword(new String[]{"无尽剑"}, "在身上可对敌人造成反伤，附着在敌人身上则可造成持续伤害，最高15层");
    	//BaseMod.addKeyword(new String[]{"次元裂缝"}, "可使用入鞘引发其特殊效果，回合结束-1");
    	//BaseMod.addKeyword(new String[]{"真实伤害"}, "无视格挡对敌方造成伤害");
    	//BaseMod.addKeyword(new String[]{"武器"}, "同时只能存在一种，每种武器都有自己不同的触发限制次数和触发层数设置");
    	//BaseMod.addKeyword(new String[]{"近战武器"}, "叛逆大剑、吉尔伽美什、路西法、阎魔刀");
    	//BaseMod.addKeyword(new String[]{"枪械武器"}, "黑檀木&白象牙、潘多拉、野狼-A");
    	//BaseMod.addKeyword(new String[]{"灾厄"}, "使用强力的潘多拉攻击时需要消耗的能量,每有1层在使用枪械系攻击卡时就有10%几率让ShowTime+1,最高9层");
    	//BaseMod.addKeyword(new String[]{"眩晕"}, "让敌人无法行动，不可叠加");
    	//BaseMod.addKeyword(new String[]{"魔人化"}, "拥有魔人状态或瞬时魔化加成中");
    	//BaseMod.addKeyword(new String[]{"ST","ST"}, "ShowTime，表演时间");
    	
    	
    	logger.info("========================== 正在注入新的关键字 ==========================");
         String keywordsPath = null;
         switch (Settings.language) {
             case ZHT: {
                 keywordsPath = "localization/zhs/WxKeywords.json";
                 break;
             }
             case ZHS: {
            	 keywordsPath = "localization/zhs/WxKeywords.json";
                 break;
             }
             default: {
            	 //keywordsPath = "localization/zhs/WxKeywords.json";
                 keywordsPath = "localization/eng/WxKeywords.json";
                 break;
             }
         }
         final Gson gson = new Gson();
         final Keywords keywords = (Keywords)gson.fromJson(loadJson(keywordsPath), Keywords.class);
         for (final Keyword key : keywords.keywords) {
             logger.info("读取关键字：" + key.NAMES[0]);
             BaseMod.addKeyword(key.NAMES, key.DESCRIPTION);
         }
         logger.info("===========================注入新的关键字成功=============================");
    }
    
    class Keywords
    {
        Keyword[] keywords;
    }
    
    @Override
    public void receiveEditCharacters() {
        logger.info("======================== 正在注入但丁的信息 ========================");
        logger.info("add " + CharacterEnum.DANTE.toString());
        BaseMod.addCharacter(
        		(AbstractPlayer)new Dante("Dante"),
        		makePath(MAGES_BUTTON), 
        		makePath(MAGES_PORTRAIT), 
        		CharacterEnum.DANTE);

        logger.info("============================注入但丁成功================================");
    }

    @Override
    public void receivePostInitialize() {
    }

    @Override
    public void receiveEditCards() {
        logger.info("========================= 正在加载新的卡牌内容 =========================");
        RedCardsPatch.Postfix();
        GreenCardsPatch.Postfix();
        //基础卡牌
        BaseMod.addCard(new Attack_a());
        BaseMod.addCard(new Attack_b());
        BaseMod.addCard(new Defend_Dante());
        BaseMod.addCard(new Provocation());
        BaseMod.addCard(new Redorb());
        BaseMod.addCard(new Shoot());
        //普通卡牌（白卡）
        BaseMod.addCard(new Airhike());
        BaseMod.addCard(new BeastUppercut());
        BaseMod.addCard(new Chargeshoot());
        BaseMod.addCard(new Chargeshoot2());
        BaseMod.addCard(new Dash1());
        BaseMod.addCard(new Dash2());
        BaseMod.addCard(new Doubleshoot());
        BaseMod.addCard(new EbonyIvory());
        BaseMod.addCard(new Fullhouse());
        BaseMod.addCard(new GilgameshcomboA());
        BaseMod.addCard(new Hightime());
        BaseMod.addCard(new Kick13());
        BaseMod.addCard(new LucifercomboA());
        BaseMod.addCard(new LucifercomboB());
        BaseMod.addCard(new LucifercomboC());
        BaseMod.addCard(new Millionkick());
        BaseMod.addCard(new Millionstab());
        BaseMod.addCard(new Rebellion());
        BaseMod.addCard(new Straight());
        BaseMod.addCard(new VitalstarS());
        BaseMod.addCard(new Weaponchange());
        BaseMod.addCard(new Weaponupgrade());
        //稀有卡牌（金卡）
        BaseMod.addCard(new Airtrick());
        BaseMod.addCard(new Blueorb());
        BaseMod.addCard(new Darkslayer());
        BaseMod.addCard(new Demonreform());
        BaseMod.addCard(new DevilstarL());
        BaseMod.addCard(new Enemystep());
        BaseMod.addCard(new Goldorb());
        BaseMod.addCard(new Purpleorb());
        BaseMod.addCard(new Royalguard());
        BaseMod.addCard(new Splesh());
        BaseMod.addCard(new Stylechange());
        BaseMod.addCard(new Swordcombo());
        BaseMod.addCard(new Trickster());
        BaseMod.addCard(new VitalstarL());
        BaseMod.addCard(new Weaponreturn());
        //罕见卡牌（蓝卡）
        BaseMod.addCard(new CoyoteA());
        BaseMod.addCard(new DevilstarS());
        BaseMod.addCard(new DT());
        BaseMod.addCard(new Ecstasy());
        BaseMod.addCard(new Gilgamesh());
        BaseMod.addCard(new Greenorb());
        BaseMod.addCard(new Gunslinger());
        BaseMod.addCard(new Gunstinger());
        BaseMod.addCard(new Holywater());
        BaseMod.addCard(new Honeycombofire());
        BaseMod.addCard(new Lucifer());
        BaseMod.addCard(new LucifercomboD());
        BaseMod.addCard(new Pandora());
        BaseMod.addCard(new Royaldefend());
        BaseMod.addCard(new Shock());
        BaseMod.addCard(new SSScard());
        BaseMod.addCard(new Stinger());
        BaseMod.addCard(new Swordmaster());
        BaseMod.addCard(new Unrisingdragon());
        BaseMod.addCard(new VitalstarM());
        BaseMod.addCard(new Whiteorb());
        //特殊卡牌（灰卡）
        BaseMod.addCard(new Airhike2());
        BaseMod.addCard(new Climax());
        BaseMod.addCard(new Dreadnaught());
        BaseMod.addCard(new Fireworks());
        BaseMod.addCard(new PF013());
        BaseMod.addCard(new PF262());
        BaseMod.addCard(new PF398());
        BaseMod.addCard(new PF594());
        BaseMod.addCard(new PF666());
        BaseMod.addCard(new PINUP());
        BaseMod.addCard(new Rainstorm());
        BaseMod.addCard(new Rebellionsword());
        BaseMod.addCard(new Release());
        BaseMod.addCard(new Risingdragon());
        BaseMod.addCard(new Royalrelease());
        BaseMod.addCard(new Sheathing());
        BaseMod.addCard(new Skystar());
        BaseMod.addCard(new Slashdimension1());
        BaseMod.addCard(new Slashdimension2());
        BaseMod.addCard(new Yamato());
        //基础卡牌解锁
        UnlockTracker.unlockCard("Attack_a");
        UnlockTracker.unlockCard("Attack_b");
        UnlockTracker.unlockCard("Defend_Dante");
        UnlockTracker.unlockCard("Provocation");
        UnlockTracker.unlockCard("Redorb"); 
        UnlockTracker.unlockCard("Shoot");
        //普通卡牌解锁（白卡）
        UnlockTracker.unlockCard("Airhike");
        UnlockTracker.unlockCard("BeastUppercut");
        UnlockTracker.unlockCard("Chargeshoot");
        UnlockTracker.unlockCard("Chargeshoot2");
        UnlockTracker.unlockCard("Dash1");
        UnlockTracker.unlockCard("Dash2");
        UnlockTracker.unlockCard("Doubleshoot");
        UnlockTracker.unlockCard("EbonyIvory");
        UnlockTracker.unlockCard("Fullhouse");
        UnlockTracker.unlockCard("GilgameshcomboA");
        UnlockTracker.unlockCard("Hightime");
        UnlockTracker.unlockCard("Kick13");        
        UnlockTracker.unlockCard("LucifercomboA");
        UnlockTracker.unlockCard("LucifercomboB");
        UnlockTracker.unlockCard("LucifercomboC");
        UnlockTracker.unlockCard("Millionkick");
        UnlockTracker.unlockCard("Millionstab");
        UnlockTracker.unlockCard("Rebellion"); 
        UnlockTracker.unlockCard("Straight");
        UnlockTracker.unlockCard("VitalstarS"); 
        UnlockTracker.unlockCard("Weaponchange"); 
        UnlockTracker.unlockCard("Weaponupgrade"); 
        //稀有卡牌解锁（金卡）
        UnlockTracker.unlockCard("Airtrick");
        UnlockTracker.unlockCard("Blueorb"); 
        UnlockTracker.unlockCard("Darkslayer");
        UnlockTracker.unlockCard("Demonreform");
        UnlockTracker.unlockCard("DevilstarL"); 
        UnlockTracker.unlockCard("Enemystep");
        UnlockTracker.unlockCard("Goldorb");
        UnlockTracker.unlockCard("Purpleorb"); 
        UnlockTracker.unlockCard("Royalguard");
        UnlockTracker.unlockCard("Splesh");
        UnlockTracker.unlockCard("Stylechange");
        UnlockTracker.unlockCard("Swordcombo");
        UnlockTracker.unlockCard("Trickster");
        UnlockTracker.unlockCard("VitalstarL"); 
        UnlockTracker.unlockCard("Weaponreturn");
        //罕见卡牌解锁（蓝卡）
        UnlockTracker.unlockCard("CoyoteA");
        UnlockTracker.unlockCard("DevilstarS"); 
        UnlockTracker.unlockCard("DT");
        UnlockTracker.unlockCard("Ecstasy");
        UnlockTracker.unlockCard("Gilgamesh");
        UnlockTracker.unlockCard("Greenorb");
        UnlockTracker.unlockCard("Gunslinger");
        UnlockTracker.unlockCard("Gunstinger");
        UnlockTracker.unlockCard("Holywater"); 
        UnlockTracker.unlockCard("Honeycombofire"); 
        UnlockTracker.unlockCard("Lucifer");
        UnlockTracker.unlockCard("LucifercomboD");
        UnlockTracker.unlockCard("Pandora");
        UnlockTracker.unlockCard("Royaldefend");
        UnlockTracker.unlockCard("Shock");
        UnlockTracker.unlockCard("SSScard"); 
        UnlockTracker.unlockCard("Stinger");
        UnlockTracker.unlockCard("Swordmaster");
        UnlockTracker.unlockCard("Unrisingdragon"); 
        UnlockTracker.unlockCard("VitalstarM"); 
        UnlockTracker.unlockCard("Whiteorb");
        //特殊卡牌解锁（灰卡）
        UnlockTracker.unlockCard("Airhike2");
        UnlockTracker.unlockCard("Climax");
        UnlockTracker.unlockCard("Dreadnaught");
        UnlockTracker.unlockCard("Fireworks");
        UnlockTracker.unlockCard("PF013");
        UnlockTracker.unlockCard("PF262");
        UnlockTracker.unlockCard("PF398");
        UnlockTracker.unlockCard("PF594");
        UnlockTracker.unlockCard("PF666");
        UnlockTracker.unlockCard("PINUP");
        UnlockTracker.unlockCard("Rainstorm");
        UnlockTracker.unlockCard("Rebellionsword");
        UnlockTracker.unlockCard("Release");
        UnlockTracker.unlockCard("Risingdragon");
        UnlockTracker.unlockCard("Royalrelease");
        UnlockTracker.unlockCard("Sheathing");
        UnlockTracker.unlockCard("Skystar");
        UnlockTracker.unlockCard("Slashdimension1");
        UnlockTracker.unlockCard("Slashdimension2");
        UnlockTracker.unlockCard("Yamato");
        
        logger.info("=========================加载新的卡牌内容成功===============================");
    }

    @Override
    public void receiveEditRelics() {
        logger.info("========================= 正在加载新的遗物内容 =========================");
        BaseMod.addRelicToCustomPool((AbstractRelic)new SSS(), AbstractCardEnum.DMC);
        BaseMod.addRelicToCustomPool((AbstractRelic)new Combocheck(), AbstractCardEnum.DMC); 
        logger.info("==========================加载新的遗物内容成功===========================");
    }

    
    
    @Override
    public void receiveEditStrings() {
    	//遗物
        //logger.info("========================= 正在加载遗物文本信息 =========================");
        //final String relicStrings = Gdx.files.internal("localization/zhs/WxRelics.json").readString(String.valueOf(StandardCharsets.UTF_8));
        //BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        //jsonString = Gdx.files.internal(LocalizedHelper.GetLangPackDir() + "WxRelics.json").readString(String.valueOf(StandardCharsets.UTF_8));
        //BaseMod.loadCustomStrings(RelicStrings.class, jsonString);
        //logger.info("==========================加载遗物文本信息成功===========================");
        logger.info("正在加载对应语言文本信息");
        String card;
        String relic;
        String power;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            logger.info("简体中文");
            card = "localization/zhs/WxCards.json";
            relic = "localization/zhs/WxRelics.json";
            power = "localization/zhs/WxPower.json";
        }
        else if (Settings.language == Settings.GameLanguage.ZHT) {
            logger.info("繁体中文");
            card = "localization/zhs/WxCards.json";
            relic = "localization/zhs/WxRelics.json";
            power = "localization/zhs/WxPower.json";
        }
        else {
            //card = "localization/zhs/WxCards.json";
            //relic = "localization/zhs/WxRelics.json";
            //power = "localization/zhs/WxPower.json";
            logger.info("英文");
            card = "localization/eng/WxCards.json";
            relic = "localization/eng/WxRelics.json";
            power = "localization/eng/WxPower.json";
        }
        final String relicStrings = Gdx.files.internal(relic).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        final String cardStrings = Gdx.files.internal(card).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        final String powerStrings = Gdx.files.internal(power).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
        logger.info("语言文本加载成功");
    }

	@Override
	public void receiveCardUsed(AbstractCard arg0) {
		
	}


}

