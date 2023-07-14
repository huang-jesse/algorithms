import java.util.HashMap;
import java.util.Map;

class SolutionDFA {
    public enum State {
        START_BLANK,
        NUMBER_SIGN,
        INT,
        DOT_WITHOUT_INT,
        DOT_LEFT_INT,
        FRACTION,
        EXP,
        EXP_SIGN,
        EXP_INT,
        END_BLANK
    }
    public enum CharType {
        BLANK,
        SIGN,
        INT,
        DOT,
        EXP,
        ILLEGAL
    }
    public boolean isNumber(String s) {
        Map<State, Map<CharType, State>> transferMap = new HashMap<>();
        // 0. State START_BLANK
        Map<CharType, State> stateStartBlank = new HashMap<>() {{
            put(CharType.BLANK, State.START_BLANK);
            put(CharType.SIGN, State.NUMBER_SIGN);
            put(CharType.INT, State.INT);
            put(CharType.DOT, State.DOT_WITHOUT_INT);
        }};
        transferMap.put(State.START_BLANK, stateStartBlank);
        // 1. State NUMBER_SIGN
        Map<CharType, State> stateNumberSign = new HashMap<>() {{
            put(CharType.INT, State.INT);
            put(CharType.DOT, State.DOT_WITHOUT_INT);
        }};
        transferMap.put(State.NUMBER_SIGN, stateNumberSign);
        // 2. State INT
        Map<CharType, State> stateInt = new HashMap<>() {{
            put(CharType.INT, State.INT);
            put(CharType.DOT, State.DOT_LEFT_INT);
            put(CharType.EXP, State.EXP);
            put(CharType.BLANK, State.END_BLANK);
        }};
        transferMap.put(State.INT, stateInt);
        // 3. State DOT_WITHOUT_INT
        Map<CharType, State> stateDotWithoutInt = new HashMap<>() {{
            put(CharType.INT, State.FRACTION);
        }};
        transferMap.put(State.DOT_WITHOUT_INT, stateDotWithoutInt);
        // 4. State DOT_LEFT_INT
        Map<CharType, State> stateDotLeftInt = new HashMap<>() {{
            put(CharType.INT, State.FRACTION);
            put(CharType.EXP, State.EXP);
            put(CharType.BLANK, State.END_BLANK);

        }};
        transferMap.put(State.DOT_LEFT_INT, stateDotLeftInt);
        // 5. State FRACTION
        Map<CharType, State> stateFraction = new HashMap<>() {{
            put(CharType.INT, State.FRACTION);
            put(CharType.EXP, State.EXP);
            put(CharType.BLANK, State.END_BLANK);
        }};
        transferMap.put(State.FRACTION, stateFraction);
        // 6. State EXP
        Map<CharType, State> stateExp = new HashMap<>() {{
            put(CharType.SIGN, State.EXP_SIGN);
            put(CharType.INT, State.EXP_INT);
        }};
        transferMap.put(State.EXP, stateExp);
        // 7. State EXP_SIGN
        Map<CharType, State> stateExpSign = new HashMap<>() {{
            put(CharType.INT, State.EXP_INT);
        }};
        transferMap.put(State.EXP_SIGN, stateExpSign);
        // 8. State EXP_INT
        Map<CharType, State> stateExpInt = new HashMap<>() {{
            put(CharType.INT, State.EXP_INT);
            put(CharType.BLANK, State.END_BLANK);
        }};
        transferMap.put(State.EXP_INT, stateExpInt);
        // 9. State END_BLANK
        Map<CharType, State> stateEndBlank = new HashMap<>() {{
            put(CharType.BLANK, State.END_BLANK);
        }};
        transferMap.put(State.END_BLANK, stateEndBlank);

        // Transfer
        State currentState = State.START_BLANK;
        for (char cur : s.toCharArray()) {
            Map<CharType, State> transfer = transferMap.get(currentState);
            CharType currentType = toCharType(cur);
            if (!transfer.containsKey(currentType)) {
                return false;
            }
            currentState = transfer.get(currentType);
        }
        return isLegalState(currentState);
    }

    private CharType toCharType(char cur) {
        CharType currentType;
        if (cur == ' ') {
            currentType = CharType.BLANK;
        } else if (cur == '+' || cur == '-') {
            currentType = CharType.SIGN;
        } else if ('0' <= cur && cur <= '9') {
            currentType = CharType.INT;
        } else if (cur == '.') {
            currentType = CharType.DOT;
        } else if (cur == 'e' || cur == 'E') {
            currentType = CharType.EXP;
        } else {
            currentType = CharType.ILLEGAL;
        }
        return currentType;
    }

    private boolean isLegalState(State state) {
        return state == State.INT || state == State.DOT_LEFT_INT || state == State.FRACTION
            || state == State.EXP_INT || state == State.END_BLANK;
    }

    public static void main(String[] args) {
        SolutionDFA sol = new SolutionDFA();
        String s = ".20";
        // String s = "12e+5";
        // String s = "12e+5.4";
        // String s = "-1E-16";
        // String s = "0e";
        // String s = "+-.";
        // String s = " -.";
        System.out.println("test: " + sol.isNumber(s));
    }
}