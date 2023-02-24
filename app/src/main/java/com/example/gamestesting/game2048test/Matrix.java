package com.example.gamestesting.game2048test;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Matrix {

    public static final int EMPTY = 0;

    public static final int N = 4;

    private final int[][] numbers;

    private final Random random;

    private final List<Spot> emptySpots;

    private final Set<Spot> mergeSpots;

    private Spot newSpot;

    public Matrix() {
        random = new Random();
        numbers = new int[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                numbers[i][j] = EMPTY;
            }
        }

        emptySpots = new ArrayList<>();
        mergeSpots = new HashSet<>();

        newSpot = new Spot(0, 0);
        for (int i = 0; i < 5; ++i) {
            spawn(2);
        }
    }

    public boolean generate() {
        int v = random.nextInt(100);
        if (v <= 79) {
            spawn(2);
            return true;
        } else if (v <= 95) {
            spawn(4);
            return true;
        }
        return false;
    }

    public Matrix(Matrix copy) {
        random = copy.random;
        numbers = new int[N][N];
        for (int r = 0; r < N; ++r) {
            System.arraycopy(copy.numbers[r], 0, numbers[r], 0, N);
        }
        emptySpots = new ArrayList<>();
        emptySpots.addAll(copy.emptySpots);

        mergeSpots = new HashSet<>();
        mergeSpots.addAll(copy.mergeSpots);

        newSpot = new Spot(copy.newSpot.r, copy.newSpot.c);
    }

    public int getSpot(int r, int c) {
        return numbers[r][c];
    }

    private void spawn(int n) {
        collectEmptySpots();
        if (!emptySpots.isEmpty()) {
            int i = random.nextInt(emptySpots.size());
            newSpot = emptySpots.get(i);
            numbers[newSpot.r][newSpot.c] = n;
        }
    }

    private void collectEmptySpots() {
        emptySpots.clear();
        for (int x = 0; x < N; ++x) {
            for (int y = 0; y < N; ++y) {
                if (numbers[x][y] == EMPTY) {
                    emptySpots.add(new Spot(x, y));
                }
            }
        }
    }

    public boolean isStuck() {
        Log.e("2048", "Hello");
        Matrix copy = new Matrix(this);
        int score = 0;
        for (Direction d : Direction.values()) {
            score += copy.swipe(d);
        }
        copy.collectEmptySpots();
        Log.e("2048", "Hello end()");
        return score == 0 && copy.emptySpots.isEmpty();
    }

    public int mergeLeft(int row) {
        int idx = 0;
        int score = 0;
        boolean merged;
        for (int i = 0; i < N; ++i) {
            if (numbers[row][i] != EMPTY) {
                // Find the farthest to the right of i
                int farthest = -1;
                for (int j = i + 1; j < N; ++j) {
                    if (numbers[row][j] != EMPTY) {
                        farthest = j;
                        break;
                    }
                }

                merged = false;
                if (farthest != -1) {
                    // Ok merge
                    if (numbers[row][i] == numbers[row][farthest]) {
                        numbers[row][i] += numbers[row][farthest];
                        score += numbers[row][i];
                        numbers[row][farthest] = EMPTY;
                        merged = true;
                    }
                }
                numbers[row][idx] = numbers[row][i];
                if (merged) {
                    mergeSpots.add(new Spot(row, idx));
                }
                if (idx != i) {
                    numbers[row][i] = EMPTY;
                }
                idx++;
            }
        }
        return score;
    }

    public int mergeRight(int row) {
        int idx = N - 1;
        int score = 0;
        boolean merged;
        for (int i = N - 1; i >= 0; --i) {
            if (numbers[row][i] != EMPTY) {
                int farthest = -1;
                for (int j = i - 1; j >= 0; --j) {
                    if (numbers[row][j] != EMPTY) {
                        farthest = j;
                        break;
                    }
                }

                merged = false;
                if (farthest != -1) {
                    // Ok merge
                    if (numbers[row][i] == numbers[row][farthest]) {
                        numbers[row][i] += numbers[row][farthest];
                        score += numbers[row][i];
                        numbers[row][farthest] = EMPTY;
                        merged = true;
                    }
                }
                numbers[row][idx] = numbers[row][i];
                if (merged) {
                    mergeSpots.add(new Spot(row, idx));
                }
                if (idx != i) {
                    numbers[row][i] = EMPTY;
                }
                idx--;
            }
        }
        return score;
    }

    public int mergeUp(int column) {
        int idx = 0;
        int score = 0;
        boolean merged;
        for (int i = 0; i < N; ++i) {
            if (numbers[i][column] != EMPTY) {
                // Find the farthest to the right of i
                int farthest = -1;
                for (int j = i + 1; j < N; ++j) {
                    if (numbers[j][column] != EMPTY) {
                        farthest = j;
                        break;
                    }
                }
                merged = false;
                if (farthest != -1) {
                    // Ok merge
                    if (numbers[i][column] == numbers[farthest][column]) {
                        numbers[i][column] += numbers[farthest][column];
                        score = numbers[i][column];
                        numbers[farthest][column] = EMPTY;
                        merged = true;
                    }
                }
                numbers[idx][column] = numbers[i][column];
                if (merged) {
                    mergeSpots.add(new Spot(idx, column));
                }
                if (idx != i) {
                    numbers[i][column] = EMPTY;
                }
                idx++;
            }
        }
        return score;
    }

    public int mergeDown(int column) {
        int idx = N - 1;
        int score = 0;
        boolean merged;
        for (int i = N - 1; i >= 0; --i) {
            if (numbers[i][column] != EMPTY) {

                int farthest = -1;
                for (int j = i - 1; j >= 0; --j) {
                    if (numbers[j][column] != EMPTY) {
                        farthest = j;
                        break;
                    }
                }

                merged = false;
                if (farthest != -1) {
                    if (numbers[i][column] == numbers[farthest][column]) {
                        numbers[i][column] += numbers[farthest][column];
                        score = numbers[i][column];
                        numbers[farthest][column] = EMPTY;
                        merged = true;
                    }
                }
                numbers[idx][column] = numbers[i][column];
                if (merged) {
                    mergeSpots.add(new Spot(idx, column));
                }
                if (idx != i) {
                    numbers[i][column] = EMPTY;
                }
                idx--;
            }
        }
        return score;
    }


    public boolean isMergeSpot(int r, int c) {
        return mergeSpots.contains(new Spot(r, c));
    }



    public int swipe(Direction dir) {
        int totalScore = 0;
        mergeSpots.clear();
        if (dir == Direction.DOWN || dir == Direction.UP) {
            for (int i = 0; i < N; ++i) {
                totalScore += (dir == Direction.DOWN) ? mergeDown(i) : mergeUp(i);
            }
        } else {
            for (int i = 0; i < N; ++i) {
                totalScore += (dir == Direction.LEFT) ? mergeLeft(i) : mergeRight(i);
            }
        }
        return totalScore;
    }

    public static class Spot {
        public final int r;
        public final int c;

        public Spot(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Spot)) return false;

            Spot spot = (Spot) o;

            if (c != spot.c) return false;
            if (r != spot.r) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = r;
            result = 31 * result + c;
            return result;
        }
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int r = 0; r < N; ++r) {
            for (int c = 0; c < N; ++c) {
                builder.append("|");
                builder.append(numbers[r][c]);
                builder.append("|");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
