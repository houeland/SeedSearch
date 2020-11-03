import java.nio.file.*;
import java.io.*;
import java.util.ArrayList;
import com.badlogic.gdx.math.RandomXS128;

class Random {
    public RandomXS128 random;

    public Random(Long seed) {
        this.random = new RandomXS128(seed);
    }

    public int random(int range) {
        return this.random.nextInt(range + 1);
    }

    public int random(int start, int end) {
        return start + this.random.nextInt(end - start + 1);
    }

    public boolean randomBoolean(float chance) {
        return this.random.nextFloat() < chance;
    }
}

class CardGroup {
    public ArrayList<String> group = new ArrayList<String>();

    private CardGroup() {}

    public static CardGroup constructCommon() {
        CardGroup g = new CardGroup();
        g.group.add("Cloak And Dagger");
        g.group.add("Underhanded Strike");
        g.group.add("Deadly Poison");
        g.group.add("Dagger Spray");
        g.group.add("Bane");
        g.group.add("Blade Dance");
        g.group.add("Deflect");
        g.group.add("Dagger Throw");
        g.group.add("Poisoned Stab");
        g.group.add("Acrobatics");
        g.group.add("Quick Slash");
        g.group.add("Slice");
        g.group.add("Backflip");
        g.group.add("Outmaneuver");
        g.group.add("Prepared");
        g.group.add("PiercingWail");
        g.group.add("Sucker Punch");
        g.group.add("Dodge and Roll");
        g.group.add("Flying Knee");
        return g;
    }

    public static CardGroup constructUncommon() {
        CardGroup g = new CardGroup();
        g.group.add("Crippling Poison");
        g.group.add("Leg Sweep");
        g.group.add("Catalyst");
        g.group.add("Tactician");
        g.group.add("Expertise");
        g.group.add("Choke");
        g.group.add("Caltrops");
        g.group.add("Blur");
        g.group.add("Setup");
        g.group.add("Endless Agony");
        g.group.add("Riddle With Holes");
        g.group.add("Skewer");
        g.group.add("Calculated Gamble");
        g.group.add("Escape Plan");
        g.group.add("Finisher");
        g.group.add("Well Laid Plans");
        g.group.add("Terror");
        g.group.add("Heel Hook");
        g.group.add("Noxious Fumes");
        g.group.add("Infinite Blades");
        g.group.add("Reflex");
        g.group.add("Eviscerate");
        g.group.add("Dash");
        g.group.add("Backstab");
        g.group.add("Bouncing Flask");
        g.group.add("Concentrate");
        g.group.add("Flechettes");
        g.group.add("Masterful Stab");
        g.group.add("Accuracy");
        g.group.add("Footwork");
        g.group.add("Distraction");
        g.group.add("All Out Attack");
        g.group.add("Predator");
        return g;
    }

    public static CardGroup constructRare() {
        CardGroup g = new CardGroup();
        g.group.add("Grand Finale");
        g.group.add("A Thousand Cuts");
        g.group.add("Glass Knife");
        g.group.add("Storm of Steel");
        g.group.add("Bullet Time");
        g.group.add("After Image");
        g.group.add("Unload");
        g.group.add("Night Terror");
        g.group.add("Tools of the Trade");
        g.group.add("Wraith Form v2");
        g.group.add("Burst");
        g.group.add("Doppelganger");
        g.group.add("Envenom");
        g.group.add("Adrenaline");
        g.group.add("Die Die Die");
        g.group.add("Phantasmal Killer");
        g.group.add("Malaise");
        g.group.add("Corpse Explosion");
        g.group.add("Venomology");
        return g;
    }

    public static CardGroup constructColorless() {
        CardGroup g = new CardGroup();
        g.group.add("Dark Shackles");
        g.group.add("Sadistic Nature");
        g.group.add("PanicButton");
        g.group.add("Trip");
        g.group.add("Dramatic Entrance");
        g.group.add("Impatience");
        g.group.add("The Bomb");
        g.group.add("Blind");
        g.group.add("Bandage Up");
        g.group.add("Secret Technique");
        g.group.add("Deep Breath");
        g.group.add("Violence");
        g.group.add("Panache");
        g.group.add("Secret Weapon");
        g.group.add("Apotheosis");
        g.group.add("Mayhem");
        g.group.add("HandOfGreed");
        g.group.add("Flash of Steel");
        g.group.add("Forethought");
        g.group.add("Enlightenment");
        g.group.add("Purity");
        g.group.add("Panacea");
        g.group.add("Transmutation");
        g.group.add("Chrysalis");
        g.group.add("Discovery");
        g.group.add("Finesse");
        g.group.add("Magnetism");
        g.group.add("Master of Strategy");
        g.group.add("Good Instincts");
        g.group.add("Swift Strike");
        g.group.add("Jack Of All Trades");
        g.group.add("Metamorphosis");
        g.group.add("Mind Blast");
        g.group.add("Thinking Ahead");
        g.group.add("Madness");
        return g;
    }

    public static CardGroup constructCurse() {
        CardGroup g = new CardGroup();
        g.group.add("Regret");
        g.group.add("Writhe");
        g.group.add("Decay");
        g.group.add("Pain");
        g.group.add("Parasite");
        g.group.add("Doubt");
        g.group.add("Injury");
        g.group.add("Clumsy");
        g.group.add("Normality");
        g.group.add("Shame");
        return g;
    }

    public String getRandomCardName(Random rng) {
        return this.group.get(rng.random(this.group.size() - 1));
    }

    public String getRandomCardName__true() {
        return this.group.get(AbstractDungeon.cardRng.random(this.group.size() - 1));
    }
}

abstract class AbstractCard {
    public static enum CardRarity {
        BASIC,
        SPECIAL,
        COMMON,
        UNCOMMON,
        RARE,
        CURSE;
    }
}

abstract class AbstractPotion {
    public static enum PotionRarity {
        PLACEHOLDER,
        COMMON,
        UNCOMMON,
        RARE;
    }

    // WARNING: MANUALLY ENTERED, COULD HAVE TYPOS!
    public static PotionRarity rarityForPotionName(String name) {
        switch(name) {
            case "Poison Potion": return PotionRarity.COMMON;
            case "CunningPotion": return PotionRarity.UNCOMMON;
            case "GhostInAJar": return PotionRarity.RARE;
            case "Block Potion": return PotionRarity.COMMON;
            case "Dexterity Potion": return PotionRarity.COMMON;
            case "Energy Potion": return PotionRarity.COMMON;
            case "Explosive Potion": return PotionRarity.COMMON;
            case "Fire Potion": return PotionRarity.COMMON;
            case "Strength Potion": return PotionRarity.COMMON;
            case "Swift Potion": return PotionRarity.COMMON;
            case "Weak Potion": return PotionRarity.COMMON;
            case "FearPotion": return PotionRarity.COMMON;
            case "AttackPotion": return PotionRarity.COMMON;
            case "SkillPotion": return PotionRarity.COMMON;
            case "PowerPotion": return PotionRarity.COMMON;
            case "ColorlessPotion": return PotionRarity.COMMON;
            case "SteroidPotion": return PotionRarity.COMMON;
            case "SpeedPotion": return PotionRarity.COMMON;
            case "BlessingOfTheForge": return PotionRarity.COMMON;
            case "Regen Potion": return PotionRarity.UNCOMMON;
            case "Ancient Potion": return PotionRarity.UNCOMMON;
            case "LiquidBronze": return PotionRarity.UNCOMMON;
            case "GamblersBrew": return PotionRarity.UNCOMMON;
            case "EssenceOfSteel": return PotionRarity.UNCOMMON;
            case "DuplicationPotion": return PotionRarity.UNCOMMON;
            case "DistilledChaos": return PotionRarity.UNCOMMON;
            case "LiquidMemories": return PotionRarity.UNCOMMON;
            case "CultistPotion": return PotionRarity.RARE;
            case "Fruit Juice": return PotionRarity.RARE;
            case "SneckoOil": return PotionRarity.RARE;
            case "FairyPotion": return PotionRarity.RARE;
            case "SmokeBomb": return PotionRarity.RARE;
            case "EntropicBrew": return PotionRarity.RARE;
            default: throw new RuntimeException("potion not known: " + name);
        }
    }
}

abstract class PotionHelper {
    private static ArrayList<String> potions = new ArrayList<String>();
    final public static int POTION_COMMON_CHANCE = 65;
    final public static int POTION_UNCOMMON_CHANCE = 25;

    public static void initialize() {
        potions.clear();
        potions = PotionHelper.getPotions();
    }

    private static ArrayList<String> getPotions() {
        ArrayList<String> retVal = new ArrayList<String>();
        retVal.add("Poison Potion");
        retVal.add("CunningPotion");
        retVal.add("GhostInAJar");
        retVal.add("Block Potion");
        retVal.add("Dexterity Potion");
        retVal.add("Energy Potion");
        retVal.add("Explosive Potion");
        retVal.add("Fire Potion");
        retVal.add("Strength Potion");
        retVal.add("Swift Potion");
        retVal.add("Weak Potion");
        retVal.add("FearPotion");
        retVal.add("AttackPotion");
        retVal.add("SkillPotion");
        retVal.add("PowerPotion");
        retVal.add("ColorlessPotion");
        retVal.add("SteroidPotion");
        retVal.add("SpeedPotion");
        retVal.add("BlessingOfTheForge");
        retVal.add("Regen Potion");
        retVal.add("Ancient Potion");
        retVal.add("LiquidBronze");
        retVal.add("GamblersBrew");
        retVal.add("EssenceOfSteel");
        retVal.add("DuplicationPotion");
        retVal.add("DistilledChaos");
        retVal.add("LiquidMemories");
        retVal.add("CultistPotion");
        retVal.add("Fruit Juice");
        retVal.add("SneckoOil");
        retVal.add("FairyPotion");
        retVal.add("SmokeBomb");
        retVal.add("EntropicBrew");
        return retVal;
    }

    public static String getRandomPotionName() {
        return potions.get(AbstractDungeon.potionRng.random(potions.size() - 1));
    }
}

abstract class AbstractRoom {    
    public static int blizzardPotionMod = 0;
    final private static int BLIZZARD_POTION_MOD_AMT = 10;
    public ArrayList<String> rewards = new ArrayList<String>();
    final public static int rareCardChance = 3;
    final public static int uncommonCardChance = 37;

    public static boolean addPotionToRewards_V2_found_useful() {
        if (AbstractDungeon.potionRng.random(0, 99) < 40 + blizzardPotionMod) {
            String pot = AbstractDungeon.returnRandomPotion();
            if (FastSearch.usefulPotion(pot)) return true;
            blizzardPotionMod -= 10;
        } else {
            blizzardPotionMod += 10;
        }
        return false;
    }

    public static AbstractCard.CardRarity getCardRarity(int roll) {
        return getCardRarity(roll, true);
    }

    public static AbstractCard.CardRarity getCardRarity(int roll, boolean enableRelicsAlteringCardChances) {
        if (roll < rareCardChance) {
            return AbstractCard.CardRarity.RARE;
        }
        if (roll < rareCardChance + uncommonCardChance) {
            return AbstractCard.CardRarity.UNCOMMON;
        }
        return AbstractCard.CardRarity.COMMON;
    }
}

abstract class AbstractDungeon {
    public static Random potionRng;
    public static Random cardRng;
    final public static int cardBlizzStartOffset = 5;
    public static int cardBlizzRandomizer = 5;
    final public static int cardBlizzGrowth = 1;
    final public static int cardBlizzMaxOffset = -40;
    final public static CardGroup colorlessCardPool = CardGroup.constructColorless();
    final public static CardGroup commonCardPool = CardGroup.constructCommon();
    final public static CardGroup uncommonCardPool = CardGroup.constructUncommon();
    final public static CardGroup rareCardPool = CardGroup.constructRare();
    final public static CardGroup curseCardPool = CardGroup.constructCurse();
    final protected static float cardUpgradedChance = 0.0f;

    public static String returnRandomPotion() {
        return AbstractDungeon.returnRandomPotion_boolean(false);
    }

    public static String returnRandomPotion_boolean(boolean limited) {
        int roll = potionRng.random(0, 99);
        if (roll < PotionHelper.POTION_COMMON_CHANCE) {
            return AbstractDungeon.returnRandomPotion_rarity_limited(AbstractPotion.PotionRarity.COMMON, limited);
        }
        if (roll < PotionHelper.POTION_UNCOMMON_CHANCE + PotionHelper.POTION_COMMON_CHANCE) {
            return AbstractDungeon.returnRandomPotion_rarity_limited(AbstractPotion.PotionRarity.UNCOMMON, limited);
        }
        return AbstractDungeon.returnRandomPotion_rarity_limited(AbstractPotion.PotionRarity.RARE, limited);
    }

    public static String returnRandomPotion_rarity_limited(AbstractPotion.PotionRarity rarity, boolean limited) {
        if (limited) throw new RuntimeException("random potion with limited set is not handled");
        String tempName = PotionHelper.getRandomPotionName();
        while (AbstractPotion.rarityForPotionName(tempName) != rarity) {
            tempName = PotionHelper.getRandomPotionName();
            if (tempName == "Fruit Juice") continue;
        }
        return tempName;
    }

    public static String getCard(AbstractCard.CardRarity rarity) {
        switch (rarity) {
            case RARE: return rareCardPool.getRandomCardName__true();
            case UNCOMMON: return uncommonCardPool.getRandomCardName__true();
            case COMMON: return commonCardPool.getRandomCardName__true();
            case CURSE: return curseCardPool.getRandomCardName__true();
            default: throw new RuntimeException("unknown rarity");
        }
    }

    public static AbstractCard.CardRarity rollRarity(Random rng) {
        int roll = cardRng.random(99);
        roll += cardBlizzRandomizer;
        return AbstractRoom.getCardRarity(roll);
    }

    public static AbstractCard.CardRarity rollRarity() {
        return AbstractDungeon.rollRarity(cardRng);
    }

    final private static String[] retVal_static = new String[3];
    public static boolean getRewardCards_V2_found_useful() {
        final int numCards = 3;
        String[] retVal = retVal_static;
        for (int i = 0; i < numCards; ++i) {
            AbstractCard.CardRarity rarity = AbstractDungeon.rollRarity();
            switch (rarity) {
                case RARE: {
                    cardBlizzRandomizer = cardBlizzStartOffset;
                    break;
                }
                case UNCOMMON: {
                    break;
                }
                case COMMON: {
                    if ((cardBlizzRandomizer -= cardBlizzGrowth) > cardBlizzMaxOffset) break;
                    cardBlizzRandomizer = cardBlizzMaxOffset;
                    break;
                }
                default: {
                    throw new RuntimeException("rolled unknown rarity");
                }
            }
            String card = null;
            boolean containsDupe = true;
            block7: while (containsDupe) {
                containsDupe = false;
                card = AbstractDungeon.getCard(rarity);
                for (int j = 0; j < i; j += 1) {
                    if (!retVal[j].equals(card)) continue;
                    containsDupe = true;
                    continue block7;
                }
            }
            retVal[i] = card;
        }
        for (String c : retVal) {
            if (!AbstractDungeon.rareCardPool.group.contains(c) && cardRng.randomBoolean(cardUpgradedChance)) {
                throw new RuntimeException("Oops, can't handle upgraded rewards!");
            }
            if (FastSearch.usefulCard(c)) return true;
        }
        return false;
    }
}

class FastSearch {
    // MANUALLY CONFIGURED! Some of these are not necessarily enough to survive
    public static boolean usefulPotion(String name) {
        switch(name) {
            case "Poison Potion": return true;
            case "CunningPotion": return true;
            case "GhostInAJar": return false;
            case "Block Potion": return false;
            case "Dexterity Potion": return false;
            case "Energy Potion": return true;
            case "Explosive Potion": return true;
            case "Fire Potion": return true;
            case "Strength Potion": return true;
            case "Swift Potion": return true;
            case "Weak Potion": return false;
            case "FearPotion": return true;
            case "AttackPotion": return true;
            case "SkillPotion": return true;
            case "PowerPotion": return true;
            case "ColorlessPotion": return true;
            case "SteroidPotion": return true;
            case "SpeedPotion": return true;
            case "BlessingOfTheForge": return true;
            case "Regen Potion": return false;
            case "Ancient Potion": return true;
            case "LiquidBronze": return true;
            case "GamblersBrew": return true;
            case "EssenceOfSteel": return false;
            case "DuplicationPotion": return true;
            case "DistilledChaos": return true;
            case "LiquidMemories": return true;
            case "CultistPotion": return true;
            case "Fruit Juice": return false;
            case "SneckoOil": return true;
            case "FairyPotion": return false;
            case "SmokeBomb": return true;
            case "EntropicBrew": return true;
            default: throw new RuntimeException("potion not known: " + name);
        }
    }

    // MANUALLY CONFIGURED! Some of these are not actually enough to survive
    public static boolean usefulCard(String name) {
        switch(name) {
            case "Cloak And Dagger": return true;
            case "Underhanded Strike": return true;
            case "Deadly Poison": return true;
            case "Dagger Spray": return true;
            case "Bane": return true;
            case "Blade Dance": return true;
            case "Deflect": return false;
            case "Dagger Throw": return true;
            case "Poisoned Stab": return true;
            case "Acrobatics": return true;
            case "Quick Slash": return true;
            case "Slice": return true;
            case "Backflip": return true;
            case "Outmaneuver": return false;
            case "Prepared": return false;
            case "PiercingWail": return false;
            case "Sucker Punch": return true;
            case "Dodge and Roll": return false;
            case "Flying Knee": return true;
            case "Crippling Poison": return true;
            case "Leg Sweep": return false;
            case "Catalyst": return false;
            case "Tactician": return false;
            case "Expertise": return true;
            case "Choke": return true;
            case "Caltrops": return true;
            case "Blur": return false;
            case "Setup": return false;
            case "Endless Agony": return true;
            case "Riddle With Holes": return true;
            case "Skewer": return true;
            case "Calculated Gamble": return true;
            case "Escape Plan": return false;
            case "Finisher": return true;
            case "Well Laid Plans": return false;
            case "Terror": return true;
            case "Heel Hook": return true;
            case "Noxious Fumes": return true;
            case "Infinite Blades": return true;
            case "Reflex": return false;
            case "Eviscerate": return true;
            case "Dash": return true;
            case "Backstab": return true;
            case "Bouncing Flask": return true;
            case "Concentrate": return false;
            case "Flechettes": return true;
            case "Masterful Stab": return true;
            case "Accuracy": return false;
            case "Footwork": return false;
            case "Distraction": return true;
            case "All Out Attack": return true;
            case "Predator": return true;
            case "Grand Finale": return true;
            case "A Thousand Cuts": return true;
            case "Glass Knife": return true;
            case "Storm of Steel": return true;
            case "Bullet Time": return false;
            case "After Image": return false;
            case "Unload": return true;
            case "Night Terror": return true;
            case "Tools of the Trade": return true;
            case "Wraith Form v2": return false;
            case "Burst": return false;
            case "Doppelganger": return true;
            case "Envenom": return true;
            case "Adrenaline": return true;
            case "Die Die Die": return true;
            case "Phantasmal Killer": return true;
            case "Malaise": return false;
            case "Corpse Explosion": return true;
            case "Venomology": return true;
            default: throw new RuntimeException("card not known: " + name);
        }
    } 

    public static boolean checkPotions(Long seed) {
        AbstractDungeon.potionRng.random.setSeed(seed);
        AbstractRoom.blizzardPotionMod = 0;
        // WARNING: CURRENTLY ONLY CHECKING FIRST 3 REWARDS
        for (int i = 0; i < 3; i += 1) {
            if (AbstractRoom.addPotionToRewards_V2_found_useful()) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkCards(Long seed) {
        AbstractDungeon.cardRng.random.setSeed(seed);
        AbstractDungeon.cardBlizzRandomizer = 5;
        // WARNING: CURRENTLY ONLY CHECKING FIRST 3 REWARDS
        for (int i = 0; i < 3; i += 1) {
            if (AbstractDungeon.getRewardCards_V2_found_useful()) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Long> return_seeds_for_inclusive_range(long min_seed, long max_seed) {
        ArrayList<Long> found = new ArrayList<Long>();
        for (long seed = min_seed; seed <= max_seed; seed += 1) {
            if (checkCards(seed) && checkPotions(seed)) {
                found.add(seed);
            }
        }
        return found;
    }

    public static void main(String[] args) throws Exception {
        PotionHelper.initialize();
        AbstractDungeon.potionRng = new Random(12345L);
        AbstractDungeon.cardRng = new Random(12345L);
        long start_range = 0L;
        while (true) {
            long next_start_range = start_range + 1000_000_000L;
            long end_range = next_start_range - 1;
            String filename = "seeds-found/" + start_range + "_to_" + end_range + ".seed_range_result";
            if (Files.exists(Paths.get(filename))) {
                SeedRangeResult result = SeedRangeResult.loadProgressFromFile(filename);
                System.out.println("Already computed: " + result);
            } else {
                ArrayList<Long> seeds = return_seeds_for_inclusive_range(start_range, end_range);
                SeedRangeResult result = new SeedRangeResult(start_range, end_range, seeds);
                SeedRangeResult.writeProgressToFile(result, filename);
                System.out.println("Newly computed: " + result);
            }
            start_range = next_start_range;
        }
    }
}

class SeedRangeResult implements Serializable {
    final public long min_seed_inclusive;
    final public long max_seed_inclusive;
    final public ArrayList<Long> interesting_seeds;
    SeedRangeResult(long min_seed_inclusive, long max_seed_inclusive, ArrayList<Long> interesting_seeds) {
        this.min_seed_inclusive = min_seed_inclusive;
        this.max_seed_inclusive = max_seed_inclusive;
        this.interesting_seeds = interesting_seeds;
    }

    public static void writeProgressToFile(SeedRangeResult result, String filename) {
        try {
            if (Files.exists(Paths.get(filename))) {
                throw new RuntimeException("File already exists!");
            }
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("could not save SeedRangeResult");
        }
    }

    public static SeedRangeResult loadProgressFromFile(String filename) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object o = objectInputStream.readObject();
            objectInputStream.close();
            return (SeedRangeResult)o;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("could not save SeedRangeResult");
        }
    }

    // public String toString() {
    //     return "[" + min_seed_inclusive + "," + max_seed_inclusive + "]: " + interesting_seeds;
    // }

    public String toString() {
        return "[" + min_seed_inclusive + "," + max_seed_inclusive + "]: " + interesting_seeds.size();
    }
}
