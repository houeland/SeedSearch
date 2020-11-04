package seedsearch;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.SeedHelper;
import com.megacrit.cardcrawl.neow.NeowReward;
import com.megacrit.cardcrawl.potions.*;
import com.megacrit.cardcrawl.relics.*;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Base64;

public class SeedResult {

    private static ArrayList<String> noDamageCards;
    private static ArrayList<String> damageCards;
    private ArrayList<Reward> miscRewards;
    private ArrayList<Reward> shopRewards;
    private ArrayList<Reward> cardRewards;
    private ArrayList<NeowReward> neowRewards;
    private ArrayList<String> events;
    private ArrayList<String> bosses;
    private ArrayList<String> monsters;
    private ArrayList<String> mapPath;
    private ArrayList<String> bossRelics;
    private ArrayList<String> relics;
    private int numElites;
    private int numCombats;
    private long seed;

    static {
        noDamageCards = new ArrayList<>();
        noDamageCards.add(Deflect.ID);
        noDamageCards.add(Backflip.ID);
        noDamageCards.add(Acrobatics.ID);
        noDamageCards.add(Outmaneuver.ID);
        noDamageCards.add(PiercingWail.ID);
        noDamageCards.add(Prepared.ID);
        noDamageCards.add(Accuracy.ID);
        noDamageCards.add(Blur.ID);
        noDamageCards.add(CalculatedGamble.ID);
        noDamageCards.add(Catalyst.ID);
        noDamageCards.add(Concentrate.ID);
        noDamageCards.add(EscapePlan.ID);
        noDamageCards.add(Expertise.ID);
        noDamageCards.add(Footwork.ID);
        noDamageCards.add(LegSweep.ID);
        noDamageCards.add(Reflex.ID);
        noDamageCards.add(Setup.ID);
        noDamageCards.add(Tactician.ID);
        noDamageCards.add(WellLaidPlans.ID);
        noDamageCards.add(AfterImage.ID);
        noDamageCards.add(BulletTime.ID);
        noDamageCards.add(Burst.ID);
        noDamageCards.add(Doppelganger.ID);
        noDamageCards.add(Malaise.ID);
        noDamageCards.add(Nightmare.ID);
        noDamageCards.add(ToolsOfTheTrade.ID);
        noDamageCards.add(WraithForm.ID);


        damageCards = new ArrayList<>();
        damageCards.add("Dagger Spray");
        damageCards.add("Dagger Throw");
        // damageCards.add(DeadlyPoison.ID);
        damageCards.add(FlyingKnee.ID);
        damageCards.add(PoisonedStab.ID);
        damageCards.add(QuickSlash.ID);
        damageCards.add(Slice.ID);
        //damageCards.add(SuckerPunch.ID);
        damageCards.add(AllOutAttack.ID);
        damageCards.add(Backstab.ID);
        // damageCards.add(BouncingFlask.ID);
        //damageCards.add(Choke.ID);
        damageCards.add(CripplingPoison.ID);
        damageCards.add(Dash.ID);
        //damageCards.add(EndlessAgony.ID);
        damageCards.add(Finisher.ID);
        damageCards.add(Flechettes.ID);
        // damageCards.add(MasterfulStab.ID);
        // damageCards.add(NoxiousFumes.ID);
        damageCards.add(Predator.ID);
        damageCards.add(RiddleWithHoles.ID);
        damageCards.add(Skewer.ID);
        damageCards.add(Terror.ID);
        // damageCards.add(Unload.ID);
        // damageCards.add(DieDieDie.ID);
        damageCards.add(CorpseExplosion.ID);
    }

    public SeedResult(long seed) {
        this.seed = seed;
        this.miscRewards = new ArrayList<>();
        this.shopRewards = new ArrayList<>();
        this.cardRewards = new ArrayList<>();
        this.neowRewards= new ArrayList<>();
        this.events = new ArrayList<>();
        this.bosses = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.mapPath = new ArrayList<>();
        this.bossRelics = new ArrayList<>();
        this.relics = new ArrayList<>();
    }

    public void addCardReward(int floor, ArrayList<AbstractCard> cards) {
        Reward reward = Reward.makeCardReward(floor, cards);
        cardRewards.add(reward);
    }

    public void addCardReward(Reward reward) {
        cardRewards.add(reward);
    }

    public void addAllCardRewards(ArrayList<Reward> rewards) {
        cardRewards.addAll(rewards);
    }

    public void addMiscReward(Reward reward) {
        miscRewards.add(reward);
    }

    public void addShopReward(Reward reward) {
        shopRewards.add(reward);
    }

    public void addNeowRewards(ArrayList<NeowReward> neowRewards) {
        this.neowRewards = neowRewards;
    }

    public void registerCombat(String monsterName) {
        numCombats += 1;
        monsters.add(monsterName);
    }

    public void registerEliteCombat(String monsterName) {
        numElites += 1;
        registerCombat(monsterName);
    }

    public void registerBossCombat(String monsterName) {
        bosses.add(monsterName);
        registerCombat(monsterName);
    }

    public void registerEvent(String eventName) {
        events.add(eventName);
    }

    public void addBossReward(ArrayList<String> bossRelics) {
        this.bossRelics.addAll(bossRelics);
    }

    public void addToMapPath(String mapSymbol) {
        mapPath.add(mapSymbol);
    }

    public void updateRelics() {
        relics = new ArrayList<>();
        for (AbstractRelic relic : AbstractDungeon.player.relics) {
            relics.add(relic.relicId);
        }
    }

    public boolean testFinalFilters(SearchSettings settings) {
        return true;
    }

    public int cardStrength(AbstractCard card) {
        if (noDamageCards.contains(card.cardID)) {
            return 0;
        } else if (damageCards.contains(card.cardID)) {
            return 1;
        } else {
            return 10;
        }
    }

    public boolean noDamage() {
        int count = 0;
        for (Reward reward : miscRewards) {
            if (reward.floor < 6) {
                for (AbstractCard card : reward.cards) {
                    count += cardStrength(card);
                }
            }
        }
        for (Reward reward : cardRewards) {
            if (reward.floor < 6) {
                int best = 0;
                for (AbstractCard card : reward.cards) {
                    int v = cardStrength(card);
                    if (v > best) best = v;
                }
                count += best;
            }
        }
        if(count > 3) {
            return false;
        } else {
            return true;
        }
    }

    public boolean testAct1Filters(SearchSettings settings) {
        if (2>1) return true;
        if (!relics.containsAll(settings.requiredAct1Relics)) {
            return false;
        }
        ArrayList<String> allCards = getAllCardIds();
        if (!allCards.containsAll(settings.requiredAct1Cards)) {
            return false;
        }

        // if (monsters.size() < 5) {
        //     return false;
        // }

        for (Reward reward : miscRewards) {
            if (reward.floor > 5) {
                break;
            }
            for (AbstractPotion potion : reward.potions) {
                if (!(potion.ID.equals(DexterityPotion.POTION_ID) || potion.ID.equals(SkillPotion.POTION_ID) || potion.ID.equals(SpeedPotion.POTION_ID) || potion.ID.equals(EnergyPotion.POTION_ID) || potion.ID.equals(GamblersBrew.POTION_ID) || potion.ID.equals(SwiftPotion.POTION_ID) || potion.ID.equals(ColorlessPotion.POTION_ID))) {
                    return false;
                }
            }
        }

        if (!(relics.get(0).equals(EternalFeather.ID) || relics.get(0).equals(BlackStar.ID) || relics.get(0).equals(WristBlade.ID) || relics.get(0).equals(HoveringKite.ID) || relics.get(0).equals(CallingBell.ID) || relics.get(0).equals(EmptyCage.ID) || relics.get(0).equals(SnakeRing.ID) || relics.get(0).equals(BustedCrown.ID) || relics.get(0).equals(CoffeeDripper.ID) || relics.get(0).equals(CursedKey.ID) || relics.get(0).equals(Ectoplasm.ID) || relics.get(0).equals(BustedCrown.ID) || relics.get(0).equals(FusionHammer.ID) || relics.get(0).equals(PhilosopherStone.ID) || relics.get(0).equals(RunicDome.ID) || relics.get(0).equals(SacredBark.ID) || relics.get(0).equals(SlaversCollar.ID) || relics.get(0).equals(Sozu.ID) || relics.get(0).equals(VelvetChoker.ID))) {
            return false;
        }

        return noDamage();
    }

    private ArrayList<String> getAllCardIds() {
        ArrayList<String> allCards = new ArrayList<>();
        for (Reward reward : cardRewards) {
            for (AbstractCard card : reward.cards) {
                allCards.add(card.cardID);
            }
        }
        for (Reward reward : miscRewards) {
            for (AbstractCard card : reward.cards) {
                allCards.add(card.cardID);
            }
        }
        for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
            allCards.add(card.cardID);
        }
        return allCards;
    }

    private static String removeTextFormatting(String text) {
        text = text.replaceAll("~|@(\\S+)~|@", "$1");
        return text.replaceAll("#.|NL", "");
    }

    public void printSeedStats() {
        ArrayList<String> shopRelics = new ArrayList<>();
        ArrayList<String> shopCards = new ArrayList<>();
        for (Reward shopReward : shopRewards) {
            shopRelics.addAll(shopReward.relics);
            for (AbstractCard card : shopReward.cards) {
                shopCards.add(card.name);
            }
        }

        System.out.println(MessageFormat.format("Seed: {0} ({1})", SeedHelper.getString(seed), seed));
        System.out.println("Neow Options:");
        for(NeowReward reward : neowRewards) {
            System.out.println(removeTextFormatting(reward.optionLabel));
        }
        System.out.println(MessageFormat.format("{0} combats ({1} elite(s)):", numCombats, numElites));
        System.out.println(monsters);
        System.out.println("Bosses:");
        System.out.println(bosses);
        System.out.println(MessageFormat.format("{0} relics:", relics.size()));
        System.out.println(relics);
        System.out.println("Shop relics:");
        System.out.println(shopRelics);
        System.out.println("Shop cards:");
        System.out.println(shopCards);
        System.out.println("Boss relics:");
        System.out.println(bossRelics);
        System.out.println("Events:");
        System.out.println(events);
        System.out.println("Map path:");
        System.out.println(mapPath);
        System.out.println("Potions:");
        for (Reward reward : miscRewards) {
            if (reward.potions.size() > 0) {
                System.out.println(String.format("Floor %d: %s", reward.floor, reward.potions));
            }
        }
        System.out.println("Card choices:");
        for (Reward reward : cardRewards) {
            if (reward.cards.size() > 0) {
                System.out.println(String.format("Floor %d: %s", reward.floor, reward.cards));
            }
        }
        System.out.println("Other cards:");
        for (Reward reward : miscRewards) {
            if (reward.cards.size() > 0) {
                System.out.println(String.format("Floor %d: %s", reward.floor, reward.cards));
            }
        }
        System.out.println("#####################################");
    }
}
