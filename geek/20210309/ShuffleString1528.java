class Solution {
    public String restoreString(String s, int[] indices) {
        char[] chs = s.toCharArray();
        int n = indices.length;
        int p = 0;
        while (p < n) {
            if (indices[p] == p) {
                p++;
                continue;
            }
            int q = indices[p];
            indices[p] = indices[q];
            indices[q] = q;
            char c = chs[p];
            chs[p] = chs[q];
            chs[q] = c;
        }
        return new String(chs);
    }
}
