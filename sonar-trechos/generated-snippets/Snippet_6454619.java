public class Snippet__6454619 {

    static void decompress(char[] array1, int start1, char[] array2, int start2, int length) {
    		for (int i = 0; i < length; ++i) {
    			helpers.putCharInArrayByIndex(array2, start2 + i, helpers.byteToCharUnsigned(helpers.getByteFromArrayByIndex(array1, start1 + i)));
    		}
    	}

}
