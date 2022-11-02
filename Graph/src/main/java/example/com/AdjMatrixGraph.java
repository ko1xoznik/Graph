package example.com;

import java.util.*;

//Граф, основанный на матрице
public class AdjMatrixGraph implements Graph {

    private boolean[][] adjMatrix;
    private int vCount = 0;
    private int eCount = 0;

    public AdjMatrixGraph(int vertexCount) {
        this.adjMatrix = new boolean[vertexCount][vertexCount];
        this.vCount = vertexCount;
    }

    //Отрисовка в консоли в виде таблицы (если False - связи между вершинами нет, иначе связь есть)
    @Override
    public boolean[][] getTable() {
        return this.adjMatrix;
    }

    //Количество вершин
    @Override
    public int vertexCount() {
        return this.vCount;
    }

    //Количество ребер
    @Override
    public int edgeCount() {
        return this.eCount;
    }

    //Добавить ребро
    @Override
    public void addAdge(int v1, int v2) {
        int maxV = Math.max(v1, v2);
        if (maxV >= vertexCount()) {
            adjMatrix = Arrays.copyOf(this.adjMatrix, maxV + 1);
            for(int i = 0; i <= maxV; ++i) {
                adjMatrix[i] = i < vCount ? Arrays.copyOf(adjMatrix[i], maxV + 1) : new boolean[maxV + 1];
            }
            vCount = maxV + 1;
        }
        if (!adjMatrix[v1][v2]) {
            adjMatrix[v1][v2] = true;
            ++eCount;
            if (!(this instanceof DiGraph)) {
                adjMatrix[v2][v1] = true;
            }
        }
    }

    //Добавить вершину
    @Override
    public void addVertex() {
        adjMatrix = Arrays.copyOf(adjMatrix, adjMatrix.length + 1);
        for(int i = 0; i < this.adjMatrix.length; ++i) {
            adjMatrix[i] = i < vCount ? Arrays.copyOf(adjMatrix[i], adjMatrix.length) : new boolean[adjMatrix.length];
        }
        vCount = adjMatrix.length;
    }

    //Удалить ребро
    @Override
    public void removeAdge(int v1, int v2) {
        if (adjMatrix[v1][v2]) {
            adjMatrix[v1][v2] = false;
            --eCount;
            if (!(this instanceof DiGraph)) {
                adjMatrix[v2][v1] = false;
            }
        }
    }

    //Рандомный граф
    @Override
    public void randomGraph(int RVCount, int connectionProb) {
        eCount = 0;
        vCount = RVCount;
        adjMatrix = new boolean[vCount][vCount];

        for(int i = 0; i < vCount; ++i) {
            int k;
            int num;
            if (!(this instanceof DiGraph)) {
                for(k = i + 1; k < vCount; ++k) {
                    num = (int)(Math.random() * 101.0D);
                    if (num <= connectionProb && i != k) {
                        adjMatrix[i][k] = true;
                        adjMatrix[k][i] = true;
                        ++eCount;
                    }
                }
            } else {
                for(k = 0; k < vCount; ++k) {
                    num = (int)(Math.random() * 101.0D);
                    if (num <= connectionProb && i != k) {
                        adjMatrix[i][k] = true;
                        ++eCount;
                    }
                }
            }
        }

    }

    //Обход всех связей элемента
    @Override
    public Iterable<Integer> adjacencies(int v) {
        return new Iterable<Integer>() {
            Integer nextAdj = null;

            @Override
            public Iterator<Integer> iterator() {
                for (int i = 0; i < vCount; i++) {
                    if (adjMatrix[v][i]) {
                        nextAdj = i;
                        break;
                    }
                }
                return new Iterator<Integer>() {

                    @Override
                    public boolean hasNext() {
                        return nextAdj != null;
                    }

                    @Override
                    public Integer next() {
                        Integer result = nextAdj;
                        nextAdj = null;
                        for (int i = result + 1; i < vCount; i++) {
                            if (adjMatrix[v][i]) {
                                nextAdj = i;
                                break;
                            }
                        }
                        return result;
                    }
                };
            }
        };
    }

    //Есть ли связь между вершинами v1 и v2
    @Override
    public boolean isAdj(int v1, int v2) {
        return this.adjMatrix[v1][v2];
    }
}
