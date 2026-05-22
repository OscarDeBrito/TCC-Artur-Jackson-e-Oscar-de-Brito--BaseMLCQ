public class Snippet__4583764 {

    private void addLetterAdjust(GlyphMapping wordMapping) {
                int letterSpaceCount = wordMapping.letterSpaceCount;
                int wordLength = wordMapping.getWordLength();
                int taAdjust = textArea.getTextLetterSpaceAdjust();
                for (int i = 0, n = wordLength; i < n; i++) {
                    int j = letterSpaceAdjustIndex + i;
                    if (j > 0) {
                        int k = wordMapping.startIndex + i;
                        MinOptMax adj = (k < letterSpaceAdjustArray.length)
                            ? letterSpaceAdjustArray [ k ] : null;
                        letterSpaceAdjust [ j ] = (adj == null) ? 0 : adj.getOpt();
                    }
                    if (letterSpaceCount > 0) {
                        letterSpaceAdjust [ j ] += taAdjust;
                        letterSpaceCount--;
                    }
                }
                letterSpaceAdjustIndex += wordLength;
            }

}
