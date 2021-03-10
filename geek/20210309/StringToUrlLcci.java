class Solution {
    public String replaceSpaces(String S, int length) {
        char[] chs = S.toCharArray();
        int n = length;
        for (int i = 0; i < length; i++) {
            if (chs[i] == ' ') {
                n += 2;
            }
        }
        int j = length - 1;
        int k = n - 1;
        while (j >= 0) {
            if (chs[j] == ' ') {
                chs[k--] = '0';
                chs[k--] = '2';
                chs[k--] = '%';
            } else {
                chs[k--] = chs[j];
            }
            j--;
        }
        return new String(chs, 0, n);
    }
}
