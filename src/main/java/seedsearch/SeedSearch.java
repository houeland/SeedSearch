package seedsearch;

import java.io.*;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.lang.System.exit;

public class SeedSearch {

    public static boolean loadingEnabled = true;
    public static SearchSettings settings;
    private static final Logger logger = LogManager.getLogger(SeedSearch.class.getName());

    public static void search() {
        loadingEnabled = false;
        settings = SearchSettings.loadSettings();
        SeedRunner runner = new SeedRunner(settings);
        ArrayList<Long> foundSeeds = new ArrayList<>();
        System.out.println("Starting search...");

        ArrayList<Long> seeds_to_explore = new ArrayList<Long>();
        try {
            File file = new File("interesting-seeds-dump.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null) {
                seeds_to_explore.add(Long.parseLong(line));
            }
            System.out.println(String.format("Read %d seeds from dump", seeds_to_explore.size()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("could not read seeds from dump");
        }

        int processed_seeds = 0;
        long[] hardcoded_seeds = new long[]{211401998960L,272842589005L,404809993290L,521370285977L,592377225976L,758532933949L};
        // for (long seed : seeds_to_explore) {
        for (long seed : hardcoded_seeds) {
            if (processed_seeds % 1000 == 0) {
                System.out.println(String.format("Doing seed: %d -> %d", processed_seeds, seed));
            }
            processed_seeds += 1;
            int count_ok = 0;
            int count_bad = 0;
            for (int neow = 0; neow <= 3; neow += 1) {
                for (int swapStrategy = 0; swapStrategy <= 1; swapStrategy += 1) {
                    if (runner.runSeed(seed, neow, swapStrategy == 0 ? false : true)) {
                        count_ok += 1;
                    } else {
                        count_bad += 1;
                    }
                }
            }
            if (count_ok >= 6) {
                for (int neow = 0; neow <= 3; neow += 1) {
                    for (int swapStrategy = 0; swapStrategy <= 1; swapStrategy += 1) {
                        if (!runner.runSeed(seed, neow, swapStrategy == 0 ? false : true)) {
                            System.out.println(String.format("neow=%d swapStrategy=%d", neow, swapStrategy));
                            runner.getSeedResult().printSeedStats();
                        }
                    }
                }
            }
            if (count_ok >= 8) {
                System.out.println(String.format("Cool seed: %d", seed));
                runner.getSeedResult().printSeedStats();
                foundSeeds.add(seed);
            }
        }
        System.out.println(String.format("%d seeds found: ", foundSeeds.size()));
        System.out.println(foundSeeds);

        if (SeedSearch.settings.exitAfterSearch) {
            exit(0);
        } else {
            System.out.println("Search complete. Manually close this program when finished.");
        }
    }

}
