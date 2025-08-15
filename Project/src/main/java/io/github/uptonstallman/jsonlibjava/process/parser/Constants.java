package io.github.uptonstallman.jsonlibjava.process.parser;

import io.github.uptonstallman.jsonlibjava.process.log.ConsoleLog;
import io.github.uptonstallman.jsonlibjava.process.log.Log;

import java.util.regex.Matcher;

/**
 * The type Constants.
 */
public class Constants {

    /**
     * The Quotes.
     */
    static int quotes                  = 34   ;  // "
    /**
     * The Back slash.
     */
    static int backSlash               = 92   ;  // \

    /**
     * The Left brace.
     */
    static int leftBrace               = 123  ;  // {
    /**
     * The Right brace.
     */
    static int rightBrace              = 125  ;  // }
    /**
     * The Left bracket.
     */
    static int leftBracket             = 91   ;  // [
    /**
     * The Right bracket.
     */
    static int rightBracket            = 93   ;  // ]

    /**
     * The Colon.
     */
    static int colon                   = 58   ;  // :
    /**
     * The Comma.
     */
    static int comma                   = 44   ;  // ,

    /**
     * The Minus.
     */
    static int minus                   = 45   ;  // -
    /**
     * The Zero.
     */
    static int zero                    = 48   ;  // 0
    /**
     * The One.
     */
    static int one                     = 49   ;  // 1
    /**
     * The Two.
     */
    static int two                     = 50   ;  // 2
    /**
     * The Three.
     */
    static int three                   = 51   ;  // 3
    /**
     * The Four.
     */
    static int four                    = 52   ;  // 4
    /**
     * The Five.
     */
    static int five                    = 53   ;  // 5
    /**
     * The Six.
     */
    static int six                     = 54   ;  // 6
    /**
     * The Seven.
     */
    static int seven                   = 55   ;  // 7
    /**
     * The Eight.
     */
    static int eight                   = 56   ;  // 8
    /**
     * The Nine.
     */
    static int nine                    = 57   ;  // 9
    /**
     * The Point.
     */
    static int point                   = 46   ;  // .

    /**
     * The T.
     */
    static int t                       = 116  ;  // t
    /**
     * The R.
     */
    static int r                       = 114  ;  // r
    /**
     * The U.
     */
    static int u                       = 117  ;  // u
    /**
     * The E.
     */
    static int e                       = 101  ;  // e

    /**
     * The F.
     */
    static int f                       = 102  ;  // f
    /**
     * The A.
     */
    static int a                       = 97   ;  // a
    /**
     * The L.
     */
    static int l                       = 108  ;  // l
    /**
     * The S.
     */
    static int s                       = 115  ;  // s
    // e

    /**
     * The N.
     */
    static int n                       = 110  ;  // n
    // u
    // l
    // l

    /**
     * The Space.
     */
    int SPACE                   = 32   ;  //
    /**
     * The Tab.
     */
    int TAB                     = 9    ;  //
    /**
     * The Cr.
     */
    int CR                      = 13   ;  //
    /**
     * The Lf.
     */
    int LF                      = 10   ;  //

    /**
     * The Log.
     */
    final Log log = new ConsoleLog();

    /**
     * Debug matcher.
     *
     * @param matcher the matcher
     * @param isValue the is value
     */
    void debugMatcher(Matcher matcher, boolean isValue) {
        log.debug("\t" + matcher);
        log.debug("\tStart index: " + matcher.start());
        log.debug("\tEnd index: " + matcher.end());
        log.debug("\tFound: " + matcher.group());

        for (int i = 0; i < matcher.groupCount(); i++) {
            log.debug("group " + i + " :" + matcher.start(i));
        }

        if (isValue) {
            log.debug("quotes " + matcher.start("quotes"));
            log.debug("brackets " + matcher.start("brackets"));
            log.debug("braces " + matcher.start("braces"));
            log.debug("number " + matcher.start("number"));
            log.debug("tr " + matcher.start("tr")); // true
            log.debug("fa " + matcher.start("fa")); // false
            log.debug("nu " + matcher.start("nu")); // null
        }
    }

    /**
     * Clear between quotes.
     *
     * @param charArray the char array
     */
    public static void clearBetweenQuotes(char[] charArray) {
        int containerStart = -1;
        int containerEnd = -1;
        for (int i = 0; i < charArray.length; i++) {

            if (charArray[i] == quotes) {

                boolean jump = false;

                try {
                    if (charArray[i - 1] == backSlash && charArray[i - 2] != backSlash) {
                        jump = true;
                    }
                } catch (Exception ignore) { /* array out of bounds */ }

                if (!jump) {
                    if (containerStart == -1) {
                        containerStart = i;

                    } else {
                        containerEnd = i;
                        for (int ii = containerStart + 1; ii <= containerEnd - 1; ii++) {
                            charArray[ii] = 0;
                        }
                        containerStart = -1;
                        containerEnd = -1;
                    }
                }

            }
        }
    }

    /**
     * Clear between braces and brackets.
     *
     * @param charArray the char array
     */
    public static void clearBetweenBracesAndBrackets(char[] charArray) {
        int containerStart = -1;
        int containerEnd = -1;
        int nivel = 0;
        for (int i = 0; i < charArray.length; i++) {

            if (charArray[i] == leftBrace || charArray[i] == leftBracket) {
                if (nivel == 1)
                    containerStart = i;
                nivel++;

            } else if (charArray[i] == rightBrace || charArray[i] == rightBracket) {
                if (nivel == 2) {
                    containerEnd = i;
                    for (int ii = containerStart + 1; ii <= containerEnd - 1; ii++) {
                        charArray[ii] = 0;
                    }
                    containerStart = -1;
                    containerEnd = -1;
                    nivel = 1;
                    continue;
                }
                nivel--;

            }

        }
    }


}
