package grp5.cdio.solitairesolver.Model;

public class PossibleCardId {
    public enum Ids {
        HJERTER,
        RUDER,
        SPAR,
        KLØR
    }

    public static int convertIdToPoints(String cardValue) {
        switch (cardValue) {
            case "K": return 13;
            case "Q": return 12;
            case "J": return 11;
            case "A": return 1;
            default:
                return Integer.parseInt(cardValue);
        }
    }
}