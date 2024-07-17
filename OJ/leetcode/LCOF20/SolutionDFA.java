import java.util.HashMap;
import java.util.Map;

class SolutionDFA {
    enum State {
        INITIALIZATION,
        NUMBER_SIGN,
        INT,
        DOT_WITHOUT_INT,
        DOT_WITH_LEFT_INT,
        FRACTION,
        EXP,
        EXP_SIGN,
        EXP_INT,
        TAIL_BLANK
    }

    enum CharType {
        SPACE,
        SIGN,
        INT,
        DOT,
        EXP,
        ILLEGAL
    }
    public boolean isNumber(String s) {
        Map<State, Map<CharType, State>> transitionMap = new HashMap<>();
        // 0.INITIALIZATION
        Map<CharType, State> initializationState = new HashMap<>() {{
            put(CharType.SPACE, State.INITIALIZATION);
            put(CharType.INT, State.INT);
            put(CharType.SIGN, State.NUMBER_SIGN);
            put(CharType.DOT, State.DOT_WITHOUT_INT);
        }};
        transitionMap.put(State.INITIALIZATION, initializationState);
        // 1.NUMBER_SIGN
        Map<CharType, State> numberSignState = new HashMap<>() {{
            put(CharType.INT, State.INT);
            put(CharType.DOT, State.DOT_WITHOUT_INT);
        }};
        transitionMap.put(State.NUMBER_SIGN, numberSignState);
        // 2.INT
        Map<CharType, State> intState = new HashMap<>() {{
            put(CharType.INT, State.INT);
            put(CharType.DOT, State.DOT_WITH_LEFT_INT);
            put(CharType.EXP, State.EXP);
            put(CharType.SPACE, State.TAIL_BLANK);
        }};
        transitionMap.put(State.INT, intState);
        // 3.DOT_WITHOUT_INT
        Map<CharType, State> dotWithoutIntState = new HashMap<>() {{
            put(CharType.INT, State.FRACTION);
        }};
        transitionMap.put(State.DOT_WITHOUT_INT, dotWithoutIntState);
        // 4.DOT_WITH_LEFT_INT
        Map<CharType, State> dotWithLeftIntState = new HashMap<>() {{
            put(CharType.INT, State.FRACTION);
            put(CharType.EXP, State.EXP);
            put(CharType.SPACE, State.TAIL_BLANK);
        }};
        transitionMap.put(State.DOT_WITH_LEFT_INT, dotWithLeftIntState);
        // 5.FRACTION
        Map<CharType, State> fractionState = new HashMap<>() {{
            put(CharType.INT, State.FRACTION);
            put(CharType.EXP, State.EXP);
            put(CharType.SPACE, State.TAIL_BLANK);
        }};
        transitionMap.put(State.FRACTION, fractionState);
        // 6.EXP
        Map<CharType, State> expState = new HashMap<>() {{
            put(CharType.SIGN, State.EXP_SIGN);
            put(CharType.INT, State.EXP_INT);
        }};
        transitionMap.put(State.EXP, expState);
        // 7.EXP_SIGN
        Map<CharType, State> expSignState = new HashMap<>() {{
            put(CharType.INT, State.EXP_INT);
        }};
        transitionMap.put(State.EXP_SIGN, expSignState);
        // 8.EXP_INT
        Map<CharType, State> expIntState = new HashMap<>() {{
            put(CharType.INT, State.EXP_INT);
            put(CharType.SPACE, State.TAIL_BLANK);
        }};
        transitionMap.put(State.EXP_INT, expIntState);
        // 9.TAIL_BLANK
        Map<CharType, State> tailBlankState = new HashMap<>() {{
            put(CharType.SPACE, State.TAIL_BLANK);
        }};
        transitionMap.put(State.TAIL_BLANK, tailBlankState);

        // Start state
        State currentState = State.INITIALIZATION;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            Map<CharType, State> transfer = transitionMap.get(currentState);
            CharType currentCharType = toCharType(s.charAt(i));
            if (!transfer.containsKey(currentCharType)) {
                return false;
            }
            currentState = transfer.get(currentCharType);
        }
        return currentState == State.INT || currentState == State.DOT_WITH_LEFT_INT || currentState == State.FRACTION
             || currentState == State.EXP_INT || currentState == State.TAIL_BLANK;
    }

    private CharType toCharType(char cur) {
        if (cur == ' ') {
            return CharType.SPACE;
        } else if ('0' <= cur && cur <= '9') {
            return CharType.INT;
        } else if (cur == '+' || cur == '-') {
            return CharType.SIGN;
        } else if (cur == '.') {
            return CharType.DOT;
        } else if (cur == 'e' || cur == 'E') {
            return CharType.EXP;
        } else {
            return CharType.ILLEGAL;
        }
    }

    public static void main(String[] args) {
        SolutionDFA sol = new SolutionDFA();
        // String s = "12e+5";
        String s = "12e+5.4";
        // String s = "-1E-16";
        // String s = "0e";
        // String s = "+-.";
        // String s = " -.";
        System.out.println("test: " + sol.isNumber(s));
    }
}