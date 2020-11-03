import java.nio.file.*;
import java.io.*;
import java.util.ArrayList;

class DumpSeeds {
    public static void main(String[] args) throws Exception {
        long start_range = 0L;
        ArrayList<Long> all_the_seeds = new ArrayList<Long>();
        while (true) {
            long next_start_range = start_range + 1000_000_000L;
            long end_range = next_start_range - 1;
            String filename = "seeds-found/" + start_range + "_to_" + end_range + ".seed_range_result";
            if (Files.exists(Paths.get(filename))) {
                SeedRangeResult result = SeedRangeResult.loadProgressFromFile(filename);
                System.out.println("Already computed: " + result);
                for (long s : result.interesting_seeds) {
                    all_the_seeds.add(s);
                }
            } else {
                break;
            }
            start_range = next_start_range;
        }
        System.out.println("all the seeds: " + all_the_seeds.size());
        BufferedWriter writer = new BufferedWriter(new FileWriter("interesting-seeds-dump.txt"));
        for (long s : all_the_seeds) {
            writer.append(String.format("%d%n", s));
        }
        writer.close();
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
