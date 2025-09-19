package practice;

import java.util.function.Predicate;

import model.Candidate;

public class CandidateValidator implements Predicate<Candidate> {
    private static final int MIN_AGE_EXCLUSIVE = 35;
    private static final int MIN_YEARS_IN_UA = 10;
    private static final String REQUIRED_NAT = "Ukrainian";

    public boolean test(Candidate c) {
        if (c == null) {
            return false;
        }
        if (c.getAge() <= MIN_AGE_EXCLUSIVE) {
            return false;
        }
        if (c.isAllowedToVote()) {
            return true;
        }
        String nat = c.getNationality();
        if (nat == null || !REQUIRED_NAT.equalsIgnoreCase(nat.trim())) {
            return false;

        }
        return yearsInUkraine(c.getPeriodsInUkr()) >= MIN_YEARS_IN_UA;
    }

    private int yearsInUkraine(String period) {
        if (period == null) {
            return 0;
        }
        String[] parts = period.split("-");
        if (parts.length != 2) {
            return 0;
        }
        try {
            int start = Integer.parseInt(parts[0].trim());
            int end = Integer.parseInt(parts[1].trim());
            return Math.max(0, end - start);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}


