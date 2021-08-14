import java.io.*;
import java.math.BigInteger;
import java.util.*;

class InputReader {
    private boolean finished = false;

    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    public static InputReader getInputReader(boolean readFromTextFile, String filepath) throws FileNotFoundException {
        return ((readFromTextFile) ? new InputReader(new FileInputStream(filepath))
                : new InputReader(System.in));
    }

    public int read() {
        if (numChars == -1) {
            throw new InputMismatchException();
        }
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0) {
                return -1;
            }
        }
        return buf[curChar++];
    }

    public int peek() {
        if (numChars == -1) {
            return -1;
        }
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                return -1;
            }
            if (numChars <= 0) {
                return -1;
            }
        }
        return buf[curChar];
    }

    public int nextInt() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9') {
                throw new InputMismatchException();
            }
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public long nextLong() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9') {
                throw new InputMismatchException();
            }
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public String nextString() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        StringBuilder res = new StringBuilder();
        do {
            if (Character.isValidCodePoint(c)) {
                res.appendCodePoint(c);
            }
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }

    public boolean isSpaceChar(int c) {
        if (filter != null) {
            return filter.isSpaceChar(c);
        }
        return isWhitespace(c);
    }

    public static boolean isWhitespace(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    private String readLine0() {
        StringBuilder buf = new StringBuilder();
        int c = read();
        while (c != '\n' && c != -1) {
            if (c != '\r') {
                buf.appendCodePoint(c);
            }
            c = read();
        }
        return buf.toString();
    }

    public String readLine() {
        String s = readLine0();
        while (s.trim().length() == 0) {
            s = readLine0();
        }
        return s;
    }

    public String readLine(boolean ignoreEmptyLines) {
        if (ignoreEmptyLines) {
            return readLine();
        } else {
            return readLine0();
        }
    }

    public BigInteger readBigInteger() {
        try {
            return new BigInteger(nextString());
        } catch (NumberFormatException e) {
            throw new InputMismatchException();
        }
    }

    public char nextCharacter() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        return (char) c;
    }

    public double nextDouble() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        double res = 0;
        while (!isSpaceChar(c) && c != '.') {
            if (c == 'e' || c == 'E') {
                return res * Math.pow(10, nextInt());
            }
            if (c < '0' || c > '9') {
                throw new InputMismatchException();
            }
            res *= 10;
            res += c - '0';
            c = read();
        }
        if (c == '.') {
            c = read();
            double m = 1;
            while (!isSpaceChar(c)) {
                if (c == 'e' || c == 'E') {
                    return res * Math.pow(10, nextInt());
                }
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                m /= 10;
                res += (c - '0') * m;
                c = read();
            }
        }
        return res * sgn;
    }

    public boolean isExhausted() {
        int value;
        while (isSpaceChar(value = peek()) && value != -1) {
            read();
        }
        return value == -1;
    }

    public String next() {
        return nextString();
    }

    public SpaceCharFilter getFilter() {
        return filter;
    }

    public void setFilter(SpaceCharFilter filter) {
        this.filter = filter;
    }

    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);
    }

    public int[] nextIntArray(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; ++i) array[i] = nextInt();
        return array;
    }

    public int[] nextSortedIntArray(int n) {
        int array[] = nextIntArray(n);
        Arrays.sort(array);
        return array;
    }

    public int[] nextSumIntArray(int n) {
        int[] array = new int[n];
        array[0] = nextInt();
        for (int i = 1; i < n; ++i) array[i] = array[i - 1] + nextInt();
        return array;
    }

    public long[] nextLongArray(int n) {
        long[] array = new long[n];
        for (int i = 0; i < n; ++i) array[i] = nextLong();
        return array;
    }

    public long[] nextSumLongArray(int n) {
        long[] array = new long[n];
        array[0] = nextInt();
        for (int i = 1; i < n; ++i) array[i] = array[i - 1] + nextInt();
        return array;
    }

    public long[] nextSortedLongArray(int n) {
        long array[] = nextLongArray(n);
        Arrays.sort(array);
        return array;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        Solver solver = new Solver();
        solver.solve();
    }
}

class Pair<T,U> {
    T first;
    U second;
    Pair(T first,U second) {
        this.first = first;
        this.second = second;
    }
}

class QueueComparator implements Comparator<Pair> {
    public int compare(Pair p1, Pair p2) {
        return (int) (Long.parseLong(p2.first.toString()) - Long.parseLong(p1.first.toString()));
    }
}
class Solver {
    public void solve() throws FileNotFoundException {
        boolean readFromLocal = false;
        readFromLocal = true;
        String filepath = "src/com/kickStart/ts2_input.txt";
        InputReader inputReader = InputReader.getInputReader(readFromLocal,filepath);
        int t = inputReader.nextInt();
        System.out.println(t);

        long[][] matrix = new long[300][300];
        for (int test = 1; test <= t; test++) {
            int r, c;
            r = inputReader.nextInt();
            c = inputReader.nextInt();
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    matrix[i][j] = inputReader.nextLong();
                }
            }
            String result = minCubes(matrix,r,c);
            System.out.println("Case #"+test+": "+result);
        }
    }

    public String minCubes(long[][] matrix, int r, int c) {
        int [][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
        PriorityQueue<Pair<Long,Pair<Integer,Integer>>> priorityQueue = new PriorityQueue<>(900,new QueueComparator());
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                priorityQueue.add(new Pair<>(matrix[i][j],new Pair<>(i,j)));
            }
        }
        boolean [][]vis = new boolean[305][305];
        long res = 0;
        while (!priorityQueue.isEmpty()) {
            Pair<Long, Pair<Integer, Integer>> p = priorityQueue.remove();
            int currRow = p.second.first;
            int currCol = p.second.second;
            if (matrix[currRow][currCol]!= p.first.longValue() || vis[currRow][currCol]){
                continue;
            }
            vis[currRow][currCol] = true;
            for(int index = 0;index<4;index++) {
                int x = currRow + dir[index][0];
                int y = currCol  + dir[index][1];
                if(isInvalid(x,y,r,c)){
                    continue;
                }
                long abs = Math.abs(matrix[currRow][currCol] - matrix[x][y]);
                if(abs>1) {
                    res +=(abs-1);
                    matrix[x][y]+=(abs-1);
                }
                priorityQueue.add(new Pair(matrix[x][y],new Pair(x,y)));
            }
        }
        return ""+ res;
    }

    private boolean isInvalid(long x, long y, int r, int c) {
        return ( x<0 || y<0 || x>=r || y>=c );
    }

    private Pair<Integer, Integer> findMaxHeightIndex(long[][] matrix, long r, long c) {
        long maxHeight = matrix[0][0];
        Pair<Integer,Integer> pair = new Pair<Integer,Integer>(0, 0);
        for(int row = 0;row<r;row++){
            for(int col = 0;col<c;col++) {
                if(maxHeight<matrix[row][col]) {
                    pair.first = row;
                    pair.second = col;
                    maxHeight = matrix[row][col];
                }
            }
        }
        System.out.println("Change In master");
        return pair;
    }
}
